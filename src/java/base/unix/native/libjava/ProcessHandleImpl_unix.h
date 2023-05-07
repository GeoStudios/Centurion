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

#include <sys/types.h>

/*
 * Declaration of ProcessHandleImpl functions common on all Unix platforms.
 * 'unix_' functions have a single implementation in ProcessHandleImpl_unix.c
 * 'os_' prefixed functions have different, os-specific implementations in the
 * various ProcessHandleImpl_{linux,macosx,aix}.c files.
 * See ProcessHandleImpl_unix.c for more details.
 */

/* Field id for jString 'command' in java.lang.ProcessHandleImpl.Info */
extern jfieldID ProcessHandleImpl_Info_commandID;

/* Field id for jString 'commandLine' in java.lang.ProcessHandleImpl.Info */
extern jfieldID ProcessHandleImpl_Info_commandLineID;

/* Field id for jString[] 'arguments' in java.lang.ProcessHandleImpl.Info */
extern jfieldID ProcessHandleImpl_Info_argumentsID;

/* Field id for jlong 'totalTime' in java.lang.ProcessHandleImpl.Info */
extern jfieldID ProcessHandleImpl_Info_totalTimeID;

/* Field id for jlong 'startTime' in java.lang.ProcessHandleImpl.Info */
extern jfieldID ProcessHandleImpl_Info_startTimeID;

/* Field id for jString 'user' in java.lang.ProcessHandleImpl.Info */
extern jfieldID ProcessHandleImpl_Info_userID;

/**
 * Return: -1 is fail;  >=  0 is parent pid
 * 'total' will contain the running time of 'pid' in nanoseconds.
 * 'start' will contain the start time of 'pid' in milliseconds since epoch.
 */
extern pid_t unix_getParentPidAndTimings(JNIEnv *env, pid_t pid,
                                         jlong *total, jlong *start);
extern pid_t os_getParentPidAndTimings(JNIEnv *env, pid_t pid,
                                       jlong *total, jlong *start);

extern void unix_getCmdlineAndUserInfo(JNIEnv *env, jobject jinfo, pid_t pid);
extern void os_getCmdlineAndUserInfo(JNIEnv *env, jobject jinfo, pid_t pid);

extern jint unix_getChildren(JNIEnv *env, jlong jpid, jlongArray array,
                             jlongArray jparentArray, jlongArray jstimesArray);
extern jint os_getChildren(JNIEnv *env, jlong jpid, jlongArray array,
                           jlongArray jparentArray, jlongArray jstimesArray);

extern void unix_getUserInfo(JNIEnv* env, jobject jinfo, uid_t uid);
extern void unix_fillArgArray(JNIEnv *env, jobject jinfo, int nargs, char *cp,
                              char *argsEnd, jstring cmdexe, char *cmdline);

extern void os_initNative(JNIEnv *env, jclass clazz);
