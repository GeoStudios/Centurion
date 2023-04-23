/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jdk_util.h"

#include "jdk_internal_misc_VM.h"

/* Only register the performance-critical methods */
static JNINativeMethod methods[] = {
    {"getNanoTimeAdjustment", "(J)J", (void *)&JVM_GetNanoTimeAdjustment}
};

JNIEXPORT jobject JNICALL
Java_jdk_internal_misc_VM_latestUserDefinedLoader0(JNIEnv *env, jclass cls) {
    return JVM_LatestUserDefinedLoader(env);
}

JNIEXPORT void JNICALL
Java_jdk_internal_misc_VM_initialize(JNIEnv *env, jclass cls) {
    // Registers implementations of native methods described in methods[]
    // above.
    // In particular, registers JVM_GetNanoTimeAdjustment as the implementation
    // of the native VM.getNanoTimeAdjustment - avoiding the cost of
    // introducing a Java_jdk_internal_misc_VM_getNanoTimeAdjustment wrapper
    (*env)->RegisterNatives(env, cls,
                            methods, sizeof(methods)/sizeof(methods[0]));
}

JNIEXPORT jobjectArray JNICALL
Java_jdk_internal_misc_VM_getRuntimeArguments(JNIEnv *env, jclass cls) {
    return JVM_GetVmArguments(env);
}
