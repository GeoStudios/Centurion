/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jni.h"
#include "jvm.h"
#include "java_lang_VirtualThread.h"

#define THREAD "Ljava/lang/Thread;"
#define VIRTUAL_THREAD  "Ljava/lang/VirtualThread;"

static JNINativeMethod methods[] = {
    { "notifyJvmtiMountBegin",   "(Z)V", (void *)&JVM_VirtualThreadMountBegin },
    { "notifyJvmtiMountEnd",     "(Z)V", (void *)&JVM_VirtualThreadMountEnd },
    { "notifyJvmtiUnmountBegin", "(Z)V", (void *)&JVM_VirtualThreadUnmountBegin },
    { "notifyJvmtiUnmountEnd",   "(Z)V", (void *)&JVM_VirtualThreadUnmountEnd },
    { "notifyJvmtiHideFrames",   "(Z)V", (void *)&JVM_VirtualThreadHideFrames },
};

JNIEXPORT void JNICALL
Java_java_lang_VirtualThread_registerNatives(JNIEnv *env, jclass clazz) {
    (*env)->RegisterNatives(env, clazz, methods, (sizeof(methods)/sizeof(methods[0])));
}
