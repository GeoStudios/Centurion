/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
