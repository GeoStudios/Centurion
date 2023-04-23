/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include <stdio.h>
#include <signal.h>

#include "jni.h"
#include "jvm.h"

#include "java_lang_StackTraceElement.h"

JNIEXPORT void JNICALL Java_java_lang_StackTraceElement_initStackTraceElement
  (JNIEnv *env, jobject dummy, jobject element, jobject stackframeinfo) {
     JVM_InitStackTraceElement(env, element, stackframeinfo);
}

JNIEXPORT void JNICALL Java_java_lang_StackTraceElement_initStackTraceElements
  (JNIEnv *env, jobject dummy, jobjectArray elements, jobject backtrace, jint depth)
{
    JVM_InitStackTraceElementArray(env, elements, backtrace, depth);
}
