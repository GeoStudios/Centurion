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

#include <stdlib.h>
#include <assert.h>
#include <string.h>

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"
#include "jvm.h"
#include "jdk_internal_loader_RawNativeLibraries.h"

static jfieldID handleID;

static jboolean initIDs(JNIEnv *env)
{
    if (handleID == 0) {
        jclass rnlClz =
            (*env)->FindClass(env, "jdk/internal/loader/RawNativeLibraries$RawNativeLibraryImpl");
        if (rnlClz == 0)
            return JNI_FALSE;
        handleID = (*env)->GetFieldID(env, rnlClz, "handle", "J");
        if (handleID == 0)
            return JNI_FALSE;
    }
    return JNI_TRUE;
}

/*
 * Class:     jdk_internal_loader_RawNativeLibraries
 * Method:    Java_jdk_internal_loader_RawNativeLibraries_load0
 * Signature: (Ljdk/internal/loader/RawNativeLibraries/RawNativeLibraryImpl;Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL
Java_jdk_internal_loader_RawNativeLibraries_load0
  (JNIEnv *env, jclass cls, jobject lib, jstring name)
{
    const char *cname;
    void * handle;

    if (!initIDs(env))
        return JNI_FALSE;

    cname = JNU_GetStringPlatformChars(env, name, 0);
    if (cname == 0)
        return JNI_FALSE;
    handle = JVM_LoadLibrary(cname, JNI_FALSE);
    (*env)->SetLongField(env, lib, handleID, ptr_to_jlong(handle));

    JNU_ReleaseStringPlatformChars(env, name, cname);
    return handle != 0L;
}

/*
 * Class:     jdk_internal_loader_RawNativeLibraries
 * Method:    unload0
 * Signature: (Ljava/lang/String;J)V
 */
JNIEXPORT void JNICALL Java_jdk_internal_loader_RawNativeLibraries_unload0
  (JNIEnv *env, jclass cls, jstring name, jlong address)
{
    void *handle;
    const char *cname;

    if (!initIDs(env))
        return;
    cname = JNU_GetStringPlatformChars(env, name, 0);
    if (cname == NULL) {
        return;
    }
    handle = jlong_to_ptr(address);

    JVM_UnloadLibrary(handle);
    JNU_ReleaseStringPlatformChars(env, name, cname);
}

