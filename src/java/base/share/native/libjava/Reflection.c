/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jni.h"
#include "jvm.h"
#include "jdk_internal_reflect_Reflection.h"

JNIEXPORT jclass JNICALL
Java_jdk_internal_reflect_Reflection_getCallerClass(JNIEnv *env, jclass unused)
{
    return JVM_GetCallerClass(env);
}

JNIEXPORT jint JNICALL
Java_jdk_internal_reflect_Reflection_getClassAccessFlags(JNIEnv *env, jclass unused, jclass cls)
{
    return JVM_GetClassAccessFlags(env, cls);
}

JNIEXPORT jboolean JNICALL
Java_jdk_internal_reflect_Reflection_areNestMates(JNIEnv *env, jclass unused, jclass current, jclass member)
{
  return JVM_AreNestMates(env, current, member);
}
