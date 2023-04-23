/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"

#include "java_lang_Shutdown.h"

JNIEXPORT void JNICALL
Java_java_lang_Shutdown_beforeHalt(JNIEnv *env, jclass ignored)
{
    JVM_BeforeHalt();
}

JNIEXPORT void JNICALL
Java_java_lang_Shutdown_halt0(JNIEnv *env, jclass ignored, jint code)
{
    JVM_Halt(code);
}
