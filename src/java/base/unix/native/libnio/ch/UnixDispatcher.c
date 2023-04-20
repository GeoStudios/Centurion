/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "nio.h"
#include "nio_util.h"

#include "sun_nio_ch_UnixDispatcher.h"

static int preCloseFD = -1;     /* File descriptor to which we dup other fd's
                                   before closing them for real */

static void closeFileDescriptor(JNIEnv *env, int fd) {
    if (fd != -1) {
        int result = close(fd);
        if (result < 0)
            JNU_ThrowIOExceptionWithLastError(env, "Close failed");
    }
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_UnixDispatcher_init(JNIEnv *env, jclass clazz)
{
    int sp[2];
    if (socketpair(PF_UNIX, SOCK_STREAM, 0, sp) < 0) {
        JNU_ThrowIOExceptionWithLastError(env, "socketpair failed");
        return;
    }
    preCloseFD = sp[0];
    close(sp[1]);
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_UnixDispatcher_close0(JNIEnv *env, jclass clazz, jobject fdo)
{
    jint fd = fdval(env, fdo);
    closeFileDescriptor(env, fd);
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_UnixDispatcher_preClose0(JNIEnv *env, jclass clazz, jobject fdo)
{
    jint fd = fdval(env, fdo);
    if (preCloseFD >= 0) {
        if (dup2(preCloseFD, fd) < 0)
            JNU_ThrowIOExceptionWithLastError(env, "dup2 failed");
    }
}
