/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
