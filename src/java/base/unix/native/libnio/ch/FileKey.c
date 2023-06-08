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
#include "nio.h"
#include "nio_util.h"
#include "sun_nio_ch_FileKey.h"

#ifdef _ALLBSD_SOURCE
#define stat64 stat

#define fstat64 fstat
#endif

static jfieldID key_st_dev;    /* id for FileKey.st_dev */
static jfieldID key_st_ino;    /* id for FileKey.st_ino */


JNIEXPORT void JNICALL
Java_sun_nio_ch_FileKey_initIDs(JNIEnv *env, jclass clazz)
{
    CHECK_NULL(key_st_dev = (*env)->GetFieldID(env, clazz, "st_dev", "J"));
    CHECK_NULL(key_st_ino = (*env)->GetFieldID(env, clazz, "st_ino", "J"));
}


JNIEXPORT void JNICALL
Java_sun_nio_ch_FileKey_init(JNIEnv *env, jobject this, jobject fdo)
{
    struct stat64 fbuf;
    int res;

    RESTARTABLE(fstat64(fdval(env, fdo), &fbuf), res);
    if (res < 0) {
        JNU_ThrowIOExceptionWithLastError(env, "fstat64 failed");
    } else {
        (*env)->SetLongField(env, this, key_st_dev, (jlong)fbuf.st_dev);
        (*env)->SetLongField(env, this, key_st_ino, (jlong)fbuf.st_ino);
    }
}
