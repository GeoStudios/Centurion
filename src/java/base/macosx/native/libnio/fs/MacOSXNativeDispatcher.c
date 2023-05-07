/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */


#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"

#include <stdlib.h>
#include <string.h>

#include <CoreFoundation/CoreFoundation.h>

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

JNIEXPORT jcharArray JNICALL
Java_sun_nio_fs_MacOSXNativeDispatcher_normalizepath(JNIEnv* env, jclass this,
                                                     jcharArray path,
                                                     jint form)
{
    jcharArray result = NULL;
    char *chars;
    CFMutableStringRef csref = CFStringCreateMutable(NULL, 0);
    if (csref == NULL) {
        JNU_ThrowOutOfMemoryError(env, "native heap");
        return NULL;
    }
    chars = (char*)(*env)->GetPrimitiveArrayCritical(env, path, 0);
    if (chars != NULL) {
        char chars_buf[(PATH_MAX + 1) * 2];     // utf16 + zero padding
        jsize len = (*env)->GetArrayLength(env, path);
        CFStringAppendCharacters(csref, (const UniChar*)chars, len);
        (*env)->ReleasePrimitiveArrayCritical(env, path, chars, 0);
        CFStringNormalize(csref, form);
        len = CFStringGetLength(csref);
        if (len < PATH_MAX) {
            if (CFStringGetCString(csref, chars_buf, sizeof(chars_buf), kCFStringEncodingUTF16)) {
                result = (*env)->NewCharArray(env, len);
                if (result != NULL) {
                    (*env)->SetCharArrayRegion(env, result, 0, len, (jchar*)&chars_buf);
                }
            }
        } else {
            int ulen = (len + 1) * 2;
            chars = malloc(ulen);
            if (chars == NULL) {
                JNU_ThrowOutOfMemoryError(env, "native heap");
            } else {
                if (CFStringGetCString(csref, chars, ulen, kCFStringEncodingUTF16)) {
                    result = (*env)->NewCharArray(env, len);
                    if (result != NULL) {
                        (*env)->SetCharArrayRegion(env, result, 0, len, (jchar*)chars);
                    }
                }
                free(chars);
            }
        }
    }
    CFRelease(csref);
    return result;
}
