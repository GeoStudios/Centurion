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

#include "jvm.h"
#include "jdk_internal_reflect_NativeConstructorAccessorImpl.h"
#include "jdk_internal_reflect_NativeMethodAccessorImpl.h"
#include "jdk_internal_reflect_DirectMethodHandleAccessor_NativeAccessor.h"
#include "jdk_internal_reflect_DirectConstructorHandleAccessor_NativeAccessor.h"

JNIEXPORT jobject JNICALL Java_jdk_internal_reflect_NativeMethodAccessorImpl_invoke0
(JNIEnv *env, jclass unused, jobject m, jobject obj, jobjectArray args)
{
    return JVM_InvokeMethod(env, m, obj, args);
}

JNIEXPORT jobject JNICALL Java_jdk_internal_reflect_NativeConstructorAccessorImpl_newInstance0
(JNIEnv *env, jclass unused, jobject c, jobjectArray args)
{
    return JVM_NewInstanceFromConstructor(env, c, args);
}

JNIEXPORT jobject JNICALL Java_jdk_internal_reflect_DirectMethodHandleAccessor_00024NativeAccessor_invoke0
(JNIEnv *env, jclass unused, jobject m, jobject obj, jobjectArray args)
{
    return JVM_InvokeMethod(env, m, obj, args);
}
JNIEXPORT jobject JNICALL Java_jdk_internal_reflect_DirectConstructorHandleAccessor_00024NativeAccessor_newInstance0
(JNIEnv *env, jclass unused, jobject c, jobjectArray args)
{
    return JVM_NewInstanceFromConstructor(env, c, args);
}
