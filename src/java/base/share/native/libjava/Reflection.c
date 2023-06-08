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
