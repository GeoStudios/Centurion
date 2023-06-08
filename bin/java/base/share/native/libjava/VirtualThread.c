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
