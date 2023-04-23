/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"
#include "jvm.h"

#include "jdk_internal_vm_VMSupport.h"

JNIEXPORT jobject JNICALL
Java_jdk_internal_vm_VMSupport_initAgentProperties(JNIEnv *env, jclass cls, jobject props)
{
    return JVM_InitAgentProperties(env, props);
}

JNIEXPORT jstring JNICALL
Java_jdk_internal_vm_VMSupport_getVMTemporaryDirectory(JNIEnv *env, jclass cls)
{
    return JVM_GetTemporaryDirectory(env);
}
