/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jni.h"
#include "jni_util.h"

#include <CoreFoundation/CoreFoundation.h>
#include <CoreServices/CoreServices.h>

/**
 * Creates a CF string from the given Java string.
 * If javaString is NULL, NULL is returned.
 * If a memory error occurs, and OutOfMemoryError is thrown and
 * NULL is returned.
 */

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

static CFStringRef toCFString(JNIEnv *env, jstring javaString)
{
    if (javaString == NULL) {
        return NULL;
    } else {
        CFStringRef result = NULL;
        jsize length = (*env)->GetStringLength(env, javaString);
        const jchar *chars = (*env)->GetStringChars(env, javaString, NULL);
        if (chars == NULL) {
            JNU_ThrowOutOfMemoryError(env, "toCFString failed");
            return NULL;
        }
        result = CFStringCreateWithCharacters(NULL, (const UniChar *)chars,
                                              length);
        (*env)->ReleaseStringChars(env, javaString, chars);
        if (result == NULL) {
            JNU_ThrowOutOfMemoryError(env, "toCFString failed");
            return NULL;
        }
        return result;
    }
}

/**
 * Creates a Java string from the given CF string.
 * If cfString is NULL, NULL is returned.
 * If a memory error occurs, and OutOfMemoryError is thrown and
 * NULL is returned.
 */
static jstring toJavaString(JNIEnv *env, CFStringRef cfString)
{
    if (cfString == NULL) {
        return NULL;
    } else {
        jstring javaString = NULL;

        CFIndex length = CFStringGetLength(cfString);
        const UniChar *constchars = CFStringGetCharactersPtr(cfString);
        if (constchars) {
            javaString = (*env)->NewString(env, constchars, length);
        } else {
            UniChar *chars = malloc(length * sizeof(UniChar));
            if (chars == NULL) {
                JNU_ThrowOutOfMemoryError(env, "toJavaString failed");
                return NULL;
            }
            CFStringGetCharacters(cfString, CFRangeMake(0, length), chars);
            javaString = (*env)->NewString(env, chars, length);
            free(chars);
        }
        return javaString;
    }
}

/**
 * Returns the content type corresponding to the supplied file extension.
 * The mapping is determined using Uniform Type Identifiers (UTIs).  If
 * the file extension parameter is NULL, a CFString cannot be created
 * from the file extension parameter, there is no UTI corresponding to
 * the file extension, the UTI cannot supply a MIME type for the file
 * extension, or a Java string cannot be created, then NULL is returned;
 * otherwise the MIME type string is returned.
 */
JNIEXPORT jstring JNICALL
Java_sun_nio_fs_UTIFileTypeDetector_probe0(JNIEnv* env, jobject ftd,
                                           jstring ext)
{
    jstring result = NULL;

    CFStringRef extension = toCFString(env, ext);
    if (extension != NULL) {
        CFStringRef uti =
            UTTypeCreatePreferredIdentifierForTag(kUTTagClassFilenameExtension,
                                                  extension, NULL);
        CFRelease(extension);

        if (uti != NULL) {
            CFStringRef mimeType =
                UTTypeCopyPreferredTagWithClass(uti, kUTTagClassMIMEType);
            CFRelease(uti);

            if (mimeType != NULL) {
                result = toJavaString(env, mimeType);
                CFRelease(mimeType);
            }
        }
    }

    return result;
}
