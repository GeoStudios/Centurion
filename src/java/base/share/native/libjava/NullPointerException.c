/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jni.h"
#include "jvm.h"

#include "java_lang_NullPointerException.h"

JNIEXPORT jstring JNICALL
Java_java_lang_NullPointerException_getExtendedNPEMessage(JNIEnv *env, jobject throwable)
{
    return JVM_GetExtendedNPEMessage(env, throwable);
}
