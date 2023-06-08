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
