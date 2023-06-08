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

#include <signal.h>
#include <stdlib.h>

#include <jni.h>
#include <jvm.h>
#include <jni_util.h>
#include <jlong.h>
#include "jdk_internal_misc_Signal.h"

JNIEXPORT jint JNICALL
Java_jdk_internal_misc_Signal_findSignal0(JNIEnv *env, jclass cls, jstring name)
{
    jint res;
    const char *cname;
    if (name == NULL) {
        JNU_ThrowNullPointerException(env, "name");
        return 0;
    }
    cname = (*env)->GetStringUTFChars(env, name, 0);
    if (cname == NULL) {
        /* out of memory thrown */
        return 0;
    }
    res = JVM_FindSignal(cname);
    (*env)->ReleaseStringUTFChars(env, name, cname);
    return res;
}

JNIEXPORT jlong JNICALL
Java_jdk_internal_misc_Signal_handle0(JNIEnv *env, jclass cls, jint sig, jlong handler)
{
    return ptr_to_jlong(JVM_RegisterSignal(sig, jlong_to_ptr(handler)));
}

JNIEXPORT void JNICALL
Java_jdk_internal_misc_Signal_raise0(JNIEnv *env, jclass cls, jint sig)
{
    JVM_RaiseSignal(sig);
}
