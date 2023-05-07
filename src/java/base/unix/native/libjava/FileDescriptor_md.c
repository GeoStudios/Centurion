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

#include <unistd.h>
#include <fcntl.h>

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"

#include "io_util_md.h"
#include "java_io_FileDescriptor.h"

/*******************************************************************/
/*  BEGIN JNI ********* BEGIN JNI *********** BEGIN JNI ************/
/*******************************************************************/

/* field id for jint 'fd' in java.io.FileDescriptor */
jfieldID IO_fd_fdID;

/* field id for jboolean 'append' in java.io.FileDescriptor */
jfieldID IO_append_fdID;

/**************************************************************
 * static methods to store field ID's in initializers
 */

JNIEXPORT void JNICALL
Java_java_io_FileDescriptor_initIDs(JNIEnv *env, jclass fdClass) {
    CHECK_NULL(IO_fd_fdID = (*env)->GetFieldID(env, fdClass, "fd", "I"));
    CHECK_NULL(IO_append_fdID = (*env)->GetFieldID(env, fdClass, "append", "Z"));
}

/**************************************************************
 * File Descriptor
 */

JNIEXPORT void JNICALL
Java_java_io_FileDescriptor_sync(JNIEnv *env, jobject this) {
    FD fd = THIS_FD(this);
    if (IO_Sync(fd) == -1) {
        JNU_ThrowByName(env, "java/io/SyncFailedException", "sync failed");
    }
}
JNIEXPORT jlong JNICALL
Java_java_io_FileDescriptor_getHandle(JNIEnv *env, jclass fdClass, jint fd) {
    return -1;
}

JNIEXPORT jboolean JNICALL
Java_java_io_FileDescriptor_getAppend(JNIEnv *env, jclass fdClass, jint fd) {
    int flags = fcntl(fd, F_GETFL);
    return ((flags & O_APPEND) == 0) ? JNI_FALSE : JNI_TRUE;
}

// instance method close0 for FileDescriptor
JNIEXPORT void JNICALL
Java_java_io_FileDescriptor_close0(JNIEnv *env, jobject this) {
    fileDescriptorClose(env, this);
}

JNIEXPORT void JNICALL
Java_java_io_FileCleanable_cleanupClose0(JNIEnv *env, jclass fdClass, jint fd, jlong unused) {
    if (fd != -1) {
        if (close(fd) == -1) {
            JNU_ThrowIOExceptionWithLastError(env, "close failed");
        }
    }
}
