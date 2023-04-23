/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
