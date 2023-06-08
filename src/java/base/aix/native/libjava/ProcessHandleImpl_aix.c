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

#include "ProcessHandleImpl_unix.h"

#include <sys/procfs.h>

/**
 * Implementation of native ProcessHandleImpl functions for AIX.
 * See ProcessHandleImpl_unix.c for more details.
 *
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 30/4/2023
 */

void os_initNative(JNIEnv *env, jclass clazz) {}

jint os_getChildren(JNIEnv *env, jlong jpid, jlongArray jarray,
                    jlongArray jparentArray, jlongArray jstimesArray) {
    return unix_getChildren(env, jpid, jarray, jparentArray, jstimesArray);
}

pid_t os_getParentPidAndTimings(JNIEnv *env, pid_t pid, jlong *total, jlong *start) {
    return unix_getParentPidAndTimings(env, pid, total, start);
}

void os_getCmdlineAndUserInfo(JNIEnv *env, jobject jinfo, pid_t pid) {
    unix_getCmdlineAndUserInfo(env, jinfo, pid);
}
