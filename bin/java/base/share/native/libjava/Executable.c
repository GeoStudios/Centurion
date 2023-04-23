/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jni.h"
#include "jvm.h"
#include "java_lang_reflect_Executable.h"

JNIEXPORT jobject JNICALL
Java_java_lang_reflect_Executable_getParameters0(JNIEnv *env,
                                                 jobject method) {
  return JVM_GetMethodParameters(env, method);
}

JNIEXPORT jbyteArray JNICALL
Java_java_lang_reflect_Executable_getTypeAnnotationBytes0(JNIEnv *env,
                                                          jobject method) {
  return JVM_GetMethodTypeAnnotations(env, method);
}
