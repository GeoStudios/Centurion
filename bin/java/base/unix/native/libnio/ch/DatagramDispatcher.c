/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include <sys/types.h>
#include <sys/uio.h>
#include <sys/socket.h>
#include <string.h>
#include <limits.h>

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"
#include "nio.h"
#include "nio_util.h"
#include "sun_nio_ch_DatagramDispatcher.h"

JNIEXPORT jint JNICALL
Java_sun_nio_ch_DatagramDispatcher_read0(JNIEnv *env, jclass clazz,
                                         jobject fdo, jlong address, jint len)
{
    jint fd = fdval(env, fdo);
    void *buf = (void *)jlong_to_ptr(address);
    int result = recv(fd, buf, len, 0);
    if (result < 0 && errno == ECONNREFUSED) {
        JNU_ThrowByName(env, JNU_JAVANETPKG "PortUnreachableException", 0);
        return IOS_THROWN;
    }
    return convertReturnVal(env, result, JNI_TRUE);
}


JNIEXPORT jlong JNICALL
Java_sun_nio_ch_DatagramDispatcher_readv0(JNIEnv *env, jclass clazz,
                                          jobject fdo, jlong address, jint len)
{
    jint fd = fdval(env, fdo);
    ssize_t result = 0;
    struct iovec *iov = (struct iovec *)jlong_to_ptr(address);
    struct msghdr m;
    if (len > IOV_MAX) {
        len = IOV_MAX;
    }

    // initialize the message
    memset(&m, 0, sizeof(m));
    m.msg_iov = iov;
    m.msg_iovlen = len;

    result = recvmsg(fd, &m, 0);
    if (result < 0 && errno == ECONNREFUSED) {
        JNU_ThrowByName(env, JNU_JAVANETPKG "PortUnreachableException", 0);
        return IOS_THROWN;
    }
    return convertLongReturnVal(env, (jlong)result, JNI_TRUE);
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_DatagramDispatcher_write0(JNIEnv *env, jclass clazz,
                                          jobject fdo, jlong address, jint len)
{
    jint fd = fdval(env, fdo);
    void *buf = (void *)jlong_to_ptr(address);
    int result = send(fd, buf, len, 0);
    if (result < 0 && errno == ECONNREFUSED) {
        JNU_ThrowByName(env, JNU_JAVANETPKG "PortUnreachableException", 0);
        return IOS_THROWN;
    }
    return convertReturnVal(env, result, JNI_FALSE);
}

JNIEXPORT jlong JNICALL
Java_sun_nio_ch_DatagramDispatcher_writev0(JNIEnv *env, jclass clazz,
                                           jobject fdo, jlong address, jint len)
{
    jint fd = fdval(env, fdo);
    struct iovec *iov = (struct iovec *)jlong_to_ptr(address);
    struct msghdr m;
    ssize_t result = 0;
    if (len > IOV_MAX) {
        len = IOV_MAX;
    }

    // initialize the message
    memset(&m, 0, sizeof(m));
    m.msg_iov = iov;
    m.msg_iovlen = len;

    result = sendmsg(fd, &m, 0);
    if (result < 0 && errno == ECONNREFUSED) {
        JNU_ThrowByName(env, JNU_JAVANETPKG "PortUnreachableException", 0);
        return IOS_THROWN;
    }
    return convertLongReturnVal(env, (jlong)result, JNI_FALSE);
}

JNIEXPORT void JNICALL
Java_sun_nio_ch_DatagramDispatcher_dup0(JNIEnv* env, jclass clazz,
    jobject fdo1, jobject fdo2)
{
    if (dup2(fdval(env, fdo1), fdval(env, fdo2)) < 0) {
        JNU_ThrowIOExceptionWithLastError(env, "dup2 failed");
    }
}
