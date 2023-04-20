/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

 #include <sys/types.h>
 #include <sys/uio.h>
 #include <unistd.h>

 #include "jni.h"
 #include "jni_util.h"
 #include "jlong.h"
 #include "nio.h"
 #include "nio_util.h"
 #include "sun_nio_ch_SocketDispatcher.h"

 JNIEXPORT jint JNICALL
 Java_sun_nio_ch_SocketDispatcher_read0(JNIEnv *env, jclass clazz,
                                        jobject fdo, jlong address, jint len)
 {
     jint fd = fdval(env, fdo);
     void *buf = (void *)jlong_to_ptr(address);
     jint n = read(fd, buf, len);
     if ((n == -1) && (errno == ECONNRESET || errno == EPIPE)) {
         JNU_ThrowByName(env, "sun/net/ConnectionResetException", "Connection reset");
         return IOS_THROWN;
     } else {
         return convertReturnVal(env, n, JNI_TRUE);
     }
 }

 JNIEXPORT jlong JNICALL
 Java_sun_nio_ch_SocketDispatcher_readv0(JNIEnv *env, jclass clazz,
                                         jobject fdo, jlong address, jint len)
 {
     jint fd = fdval(env, fdo);
     struct iovec *iov = (struct iovec *)jlong_to_ptr(address);
     jlong n = readv(fd, iov, len);
     if ((n == -1) && (errno == ECONNRESET || errno == EPIPE)) {
         JNU_ThrowByName(env, "sun/net/ConnectionResetException", "Connection reset");
         return IOS_THROWN;
     } else {
         return convertLongReturnVal(env, n, JNI_TRUE);
     }
 }

JNIEXPORT jint JNICALL
Java_sun_nio_ch_SocketDispatcher_write0(JNIEnv *env, jclass clazz,
                                        jobject fdo, jlong address, jint len)
{
    jint fd = fdval(env, fdo);
    void *buf = (void *)jlong_to_ptr(address);

    return convertReturnVal(env, write(fd, buf, len), JNI_FALSE);
}

JNIEXPORT jlong JNICALL
Java_sun_nio_ch_SocketDispatcher_writev0(JNIEnv *env, jclass clazz,
                                         jobject fdo, jlong address, jint len)
{
    jint fd = fdval(env, fdo);
    struct iovec *iov = (struct iovec *)jlong_to_ptr(address);
    return convertLongReturnVal(env, writev(fd, iov, len), JNI_FALSE);
}
