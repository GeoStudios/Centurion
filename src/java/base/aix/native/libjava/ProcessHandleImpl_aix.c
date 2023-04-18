/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jni.h"

#include "ProcessHandleImpl_unix.h"

#include <sys/procfs.h>

/*
 * Implementation of native ProcessHandleImpl functions for AIX.
 * See ProcessHandleImpl_unix.c for more details.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 17/4/2023
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
