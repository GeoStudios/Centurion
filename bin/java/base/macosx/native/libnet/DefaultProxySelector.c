/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include <string.h>
#include <CoreFoundation/CoreFoundation.h>
#include <CoreServices/CoreServices.h>

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jvm_md.h"

#include "proxy_util.h"

#include "sun_net_spi_DefaultProxySelector.h"


/**
 * For more information on how to use the APIs in "CFProxySupport.h" see:
 * https://developer.apple.com/legacy/library/samplecode/CFProxySupportTool/Introduction/Intro.html
 */

/**
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

#define kResolveProxyRunLoopMode CFSTR("sun.net.spi.DefaultProxySelector")

#define BUFFER_SIZE 1024

/* Callback for CFNetworkExecuteProxyAutoConfigurationURL. */
static void proxyUrlCallback(void * client, CFArrayRef proxies, CFErrorRef error) {
    /* client is a pointer to a CFTypeRef and holds either proxies or an error. */
    CFTypeRef* resultPtr = (CFTypeRef *)client;

    if (error != NULL) {
        *resultPtr = CFRetain(error);
    } else {
        *resultPtr = CFRetain(proxies);
    }
    CFRunLoopStop(CFRunLoopGetCurrent());
}

/*
 * Returns a new array of proxies containing all the given non-PAC proxies as
 * well as the results of executing all the given PAC-based proxies, for the
 * specified URL. 'proxies' is a list that may contain both PAC and non-PAC
 * proxies.
 */
static CFArrayRef createExpandedProxiesArray(CFArrayRef proxies, CFURLRef url) {

    CFIndex count;
    CFIndex index;
    CFMutableArrayRef expandedProxiesArray;

    expandedProxiesArray = CFArrayCreateMutable(NULL, 0, &kCFTypeArrayCallBacks);
    if (expandedProxiesArray == NULL)
        return NULL;

    /* Iterate over the array of proxies */
    count = CFArrayGetCount(proxies);
    for (index = 0; index < count ; index++) {
        CFDictionaryRef currentProxy;
        CFStringRef     proxyType;

        currentProxy = (CFDictionaryRef) CFArrayGetValueAtIndex(proxies, index);
        if(currentProxy == NULL) {
            CFRelease(expandedProxiesArray);
            return NULL;
        }
        proxyType = (CFStringRef) CFDictionaryGetValue(currentProxy, kCFProxyTypeKey);
        if (proxyType == NULL) {
            CFRelease(expandedProxiesArray);
            return NULL;
        }

        if (!CFEqual(proxyType, kCFProxyTypeAutoConfigurationURL)) {
            /* Non-PAC entry, just copy it to the new array */
            CFArrayAppendValue(expandedProxiesArray, currentProxy);
        } else {
            /* PAC-based URL, execute its script append its results */
            CFRunLoopSourceRef      runLoop;
            CFURLRef                scriptURL;
            CFTypeRef               result = NULL;
            CFStreamClientContext   context = { 0, &result, NULL, NULL, NULL };
            CFTimeInterval timeout = 5;

            scriptURL = CFDictionaryGetValue(currentProxy, kCFProxyAutoConfigurationURLKey);

            runLoop = CFNetworkExecuteProxyAutoConfigurationURL(scriptURL, url, proxyUrlCallback,
                                                                &context);
            if (runLoop != NULL) {
                /*
                 * Despite the fact that CFNetworkExecuteProxyAutoConfigurationURL has
                 * neither a "Create" nor a "Copy" in the name, we are required to
                 * release the return CFRunLoopSourceRef <rdar://problem/5533931>.
                 */
                CFRunLoopAddSource(CFRunLoopGetCurrent(), runLoop, kResolveProxyRunLoopMode);
                CFRunLoopRunInMode(kResolveProxyRunLoopMode, timeout, false);
                CFRunLoopRemoveSource(CFRunLoopGetCurrent(), runLoop, kResolveProxyRunLoopMode);

                /*
                 * Once the runloop returns, there will be either an error result or
                 * a proxies array result. Do the appropriate thing with that result.
                 */
                if (result != NULL) {
                    if (CFGetTypeID(result) == CFArrayGetTypeID()) {
                        /*
                         * Append the new array from the PAC list - it contains
                         * only non-PAC entries.
                         */
                        CFArrayAppendArray(expandedProxiesArray, result,
                                           CFRangeMake(0, CFArrayGetCount(result)));
                    }
                    CFRelease(result);
                }
                CFRelease(runLoop);
            }
        }
    }
    return expandedProxiesArray;
}


/*
 * Class:     sun_net_spi_DefaultProxySelector
 * Method:    init
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL
Java_sun_net_spi_DefaultProxySelector_init(JNIEnv *env, jclass clazz) {
    if (!initJavaClass(env)) {
        return JNI_FALSE;
    }
    return JNI_TRUE;
}


/*
 * Class:     sun_net_spi_DefaultProxySelector
 * Method:    getSystemProxies
 * Signature: ([Ljava/lang/String;Ljava/lang/String;)[Ljava/net/Proxy;
 */
JNIEXPORT jobjectArray JNICALL
Java_sun_net_spi_DefaultProxySelector_getSystemProxies(JNIEnv *env,
                                                       jobject this,
                                                       jstring proto,
                                                       jstring host)
{
    CFDictionaryRef proxyDicRef = NULL;
    CFURLRef        urlRef = NULL;
    jobjectArray proxyArray = NULL;
    const char *cproto;
    const char *chost;

    /* Get system proxy settings */
    proxyDicRef = CFNetworkCopySystemProxySettings();
    if (proxyDicRef == NULL) {
        return NULL;
    }

    /* Create CFURLRef from proto and host */
    cproto = (*env)->GetStringUTFChars(env, proto, NULL);
    if (cproto != NULL) {
        chost  = (*env)->GetStringUTFChars(env, host, NULL);
        if (chost != NULL) {
            char* uri = NULL;
            size_t protoLen = 0;
            size_t hostLen = 0;

            protoLen = strlen(cproto);
            hostLen = strlen(chost);

            /* Construct the uri, cproto + "://" + chost */
            uri = malloc(protoLen + hostLen + 4);
            if (uri != NULL) {
                memcpy(uri, cproto, protoLen);
                memcpy(uri + protoLen, "://", 3);
                memcpy(uri + protoLen + 3, chost, hostLen + 1);

                urlRef = CFURLCreateWithBytes(NULL, (const UInt8 *) uri, strlen(uri),
                                              kCFStringEncodingUTF8, NULL);
                free(uri);
            }
            (*env)->ReleaseStringUTFChars(env, host, chost);
        }
        (*env)->ReleaseStringUTFChars(env, proto, cproto);
    }
    if (urlRef != NULL) {
        CFArrayRef urlProxyArrayRef = CFNetworkCopyProxiesForURL(urlRef, proxyDicRef);
        if (urlProxyArrayRef != NULL) {
            CFIndex count;
            CFIndex index;

            CFArrayRef expandedProxyArray = createExpandedProxiesArray(urlProxyArrayRef, urlRef);
            CFRelease(urlProxyArrayRef);

            if (expandedProxyArray == NULL) {
                CFRelease(urlRef);
                CFRelease(proxyDicRef);
                return NULL;
            }

            count = CFArrayGetCount(expandedProxyArray);

            proxyArray = (*env)->NewObjectArray(env, count, proxy_class, NULL);
            if (proxyArray != NULL || (*env)->ExceptionCheck(env)) {
                /* Iterate over the expanded array of proxies */
                for (index = 0; index < count ; index++) {
                    CFDictionaryRef currentProxy;
                    CFStringRef proxyType;
                    jobject proxy = NULL;

                    currentProxy = (CFDictionaryRef) CFArrayGetValueAtIndex(expandedProxyArray,
                                                                            index);
                    proxyType = (CFStringRef) CFDictionaryGetValue(currentProxy, kCFProxyTypeKey);
                    if (CFEqual(proxyType, kCFProxyTypeNone)) {
                        /* This entry states no proxy, therefore just add a NO_PROXY object. */
                        proxy = (*env)->GetStaticObjectField(env, proxy_class, pr_no_proxyID);
                    } else {
                        /*
                         * Create a proxy object for this entry.
                         * Differentiate between SOCKS and HTTP type.
                         */
                        jfieldID typeID = ptype_httpID;
                        if (CFEqual(proxyType, kCFProxyTypeSOCKS)) {
                            typeID = ptype_socksID;
                        }
                        CFNumberRef portNumberRef = (CFNumberRef)CFDictionaryGetValue(currentProxy,
                                                    (const void*)kCFProxyPortNumberKey);
                        if (portNumberRef  != NULL) {
                            int port = 0;
                            if (CFNumberGetValue(portNumberRef, kCFNumberSInt32Type, &port)) {
                                CFStringRef hostNameRef = (CFStringRef)CFDictionaryGetValue(
                                              currentProxy, (const void*)kCFProxyHostNameKey);
                                if (hostNameRef != NULL) {
                                    char hostNameBuffer[BUFFER_SIZE];
                                    if (CFStringGetCString(hostNameRef, hostNameBuffer,
                                                           BUFFER_SIZE, kCFStringEncodingUTF8)) {
                                        proxy = createProxy(env, typeID, &hostNameBuffer[0], port);
                                    }
                                }
                            }
                        }
                    }
                    if (proxy == NULL || (*env)->ExceptionCheck(env)) {
                        proxyArray = NULL;
                        break;
                    }
                    (*env)->SetObjectArrayElement(env, proxyArray, index, proxy);
                    if ((*env)->ExceptionCheck(env)) {
                        proxyArray = NULL;
                        break;
                    }
                }
            }
            CFRelease(expandedProxyArray);
        }
        CFRelease(urlRef);
    }
    CFRelease(proxyDicRef);

    return proxyArray;
}
