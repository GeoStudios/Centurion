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
#include "jni_util.h"
#include "jvm.h"

#include "io_util.h"
#include "io_util_md.h"
#include "java_io_FileOutputStream.h"

#include <fcntl.h>

/*******************************************************************/
/*  BEGIN JNI ********* BEGIN JNI *********** BEGIN JNI ************/
/*******************************************************************/

jfieldID fos_fd; /* id for jobject 'fd' in java.io.FileOutputStream */

/**************************************************************
 * static methods to store field ID's in initializers
 */

JNIEXPORT void JNICALL
Java_java_io_FileOutputStream_initIDs(JNIEnv *env, jclass fosClass) {
    fos_fd = (*env)->GetFieldID(env, fosClass, "fd", "Ljava/io/FileDescriptor;");
}

/**************************************************************
 * Output stream
 */

JNIEXPORT void JNICALL
Java_java_io_FileOutputStream_open0(JNIEnv *env, jobject this,
                                    jstring path, jboolean append) {
    fileOpen(env, this, path, fos_fd,
             O_WRONLY | O_CREAT | (append ? O_APPEND : O_TRUNC));
}

JNIEXPORT void JNICALL
Java_java_io_FileOutputStream_write(JNIEnv *env, jobject this, jint byte, jboolean append) {
    writeSingle(env, this, byte, append, fos_fd);
}

JNIEXPORT void JNICALL
Java_java_io_FileOutputStream_writeBytes(JNIEnv *env,
    jobject this, jbyteArray bytes, jint off, jint len, jboolean append) {
    writeBytes(env, this, bytes, off, len, append, fos_fd);
}

