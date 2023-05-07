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

#include <strings.h>
#include <sys/types.h>
#include <sys/event.h>
#include <sys/time.h>

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"
#include "nio.h"
#include "nio_util.h"

#include "sun_nio_ch_KQueue.h"

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

JNIEXPORT jint JNICALL
Java_sun_nio_ch_KQueue_keventSize(JNIEnv* env, jclass clazz)
{
    return sizeof(struct kevent);
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_KQueue_identOffset(JNIEnv* env, jclass clazz)
{
    return offsetof(struct kevent, ident);
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_KQueue_filterOffset(JNIEnv* env, jclass clazz)
{
    return offsetof(struct kevent, filter);
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_KQueue_flagsOffset(JNIEnv* env, jclass clazz)
{
    return offsetof(struct kevent, flags);
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_KQueue_create(JNIEnv *env, jclass clazz) {
    int kqfd = kqueue();
    if (kqfd < 0) {
        JNU_ThrowIOExceptionWithLastError(env, "kqueue failed");
        return IOS_THROWN;
    }
    return kqfd;
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_KQueue_register(JNIEnv *env, jclass clazz, jint kqfd,
                                jint fd, jint filter, jint flags)

{
    struct kevent changes[1];
    int res;

    EV_SET(&changes[0], fd, filter, flags, 0, 0, 0);
    RESTARTABLE(kevent(kqfd, &changes[0], 1, NULL, 0, NULL), res);
    return (res == -1) ? errno : 0;
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_KQueue_poll(JNIEnv *env, jclass clazz, jint kqfd, jlong address,
                            jint nevents, jlong timeout)
{
    struct kevent *events = jlong_to_ptr(address);
    int res;
    struct timespec ts;
    struct timespec *tsp;

    if (timeout >= 0) {
        ts.tv_sec = timeout / 1000;
        ts.tv_nsec = (timeout % 1000) * 1000000;
        tsp = &ts;
    } else {
        tsp = NULL;
    }

    res = kevent(kqfd, NULL, 0, events, nevents, tsp);
    if (res < 0) {
        if (errno == EINTR) {
            return IOS_INTERRUPTED;
        } else {
            JNU_ThrowIOExceptionWithLastError(env, "kqueue failed");
            return IOS_THROWN;
        }
    }
    return res;
}
