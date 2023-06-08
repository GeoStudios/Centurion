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

#include <fcntl.h>
#include <limits.h>

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"
#include "io_util.h"

#include "jvm.h"

#include "java_io_FileInputStream.h"

#include "io_util_md.h"

/*******************************************************************/
/*  BEGIN JNI ********* BEGIN JNI *********** BEGIN JNI ************/
/*******************************************************************/

jfieldID fis_fd; /* id for jobject 'fd' in java.io.FileInputStream */

/**************************************************************
 * static methods to store field ID's in initializers
 */

JNIEXPORT void JNICALL
Java_java_io_FileInputStream_initIDs(JNIEnv *env, jclass fdClass) {
    fis_fd = (*env)->GetFieldID(env, fdClass, "fd", "Ljava/io/FileDescriptor;");
}

/**************************************************************
 * Input stream
 */

JNIEXPORT void JNICALL
Java_java_io_FileInputStream_open0(JNIEnv *env, jobject this, jstring path) {
    fileOpen(env, this, path, fis_fd, O_RDONLY);
}

JNIEXPORT jint JNICALL
Java_java_io_FileInputStream_read0(JNIEnv *env, jobject this) {
    return readSingle(env, this, fis_fd);
}

JNIEXPORT jint JNICALL
Java_java_io_FileInputStream_readBytes(JNIEnv *env, jobject this,
        jbyteArray bytes, jint off, jint len) {
    return readBytes(env, this, bytes, off, len, fis_fd);
}

JNIEXPORT jlong JNICALL
Java_java_io_FileInputStream_length0(JNIEnv *env, jobject this) {

    FD fd;
    jlong length = jlong_zero;

    fd = getFD(env, this, fis_fd);
    if (fd == -1) {
        JNU_ThrowIOException(env, "Stream Closed");
        return -1;
    }
    if ((length = IO_GetLength(fd)) == -1) {
        JNU_ThrowIOExceptionWithLastError(env, "GetLength failed");
    }
    return length;
}

JNIEXPORT jlong JNICALL
Java_java_io_FileInputStream_position0(JNIEnv *env, jobject this) {
    FD fd;
    jlong ret;

    fd = getFD(env, this, fis_fd);
    if (fd == -1) {
        JNU_ThrowIOException(env, "Stream Closed");
        return -1;
    }
    if ((ret = IO_Lseek(fd, 0L, SEEK_CUR)) == -1) {
        JNU_ThrowIOExceptionWithLastError(env, "Seek failed");
    }
    return ret;
}

JNIEXPORT jlong JNICALL
Java_java_io_FileInputStream_skip0(JNIEnv *env, jobject this, jlong toSkip) {
    jlong cur = jlong_zero;
    jlong end = jlong_zero;
    FD fd = getFD(env, this, fis_fd);
    if (fd == -1) {
        JNU_ThrowIOException (env, "Stream Closed");
        return 0;
    }
    if ((cur = IO_Lseek(fd, (jlong)0, (jint)SEEK_CUR)) == -1) {
        JNU_ThrowIOExceptionWithLastError(env, "Seek error");
    } else if ((end = IO_Lseek(fd, toSkip, (jint)SEEK_CUR)) == -1) {
        JNU_ThrowIOExceptionWithLastError(env, "Seek error");
    }
    return (end - cur);
}

JNIEXPORT jint JNICALL
Java_java_io_FileInputStream_available0(JNIEnv *env, jobject this) {
    jlong ret;
    FD fd = getFD(env, this, fis_fd);
    if (fd == -1) {
        JNU_ThrowIOException (env, "Stream Closed");
        return 0;
    }
    if (IO_Available(fd, &ret)) {
        if (ret > INT_MAX) {
            ret = (jlong) INT_MAX;
        } else if (ret < 0) {
            ret = 0;
        }
        return jlong_to_jint(ret);
    }
    JNU_ThrowIOExceptionWithLastError(env, NULL);
    return 0;
}
