/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include <sys/mount.h>

#include "jni.h"
#include "nio.h"
#include "nio_util.h"
#include "sun_nio_ch_FileDispatcherImpl.h"

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

static jlong
handle(JNIEnv *env, jlong rv, char *msg)
{
    if (rv >= 0)
        return rv;
    if (errno == EINTR)
        return IOS_INTERRUPTED;
    JNU_ThrowIOExceptionWithLastError(env, msg);
    return IOS_THROWN;
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_FileDispatcherImpl_force0(JNIEnv *env, jobject this,
                                          jobject fdo, jboolean md)
{
    jint fd = fdval(env, fdo);
    int result = 0;

    result = fcntl(fd, F_FULLFSYNC);
    if (result == -1) {
        struct statfs fbuf;
        int errno_fcntl = errno;
        if (fstatfs(fd, &fbuf) == 0) {
            if ((fbuf.f_flags & MNT_LOCAL) == 0) {
                /* Try fsync() in case file is not local. */
                result = fsync(fd);
            }
        } else {
            /* fstatfs() failed so restore errno from fcntl(). */
            errno = errno_fcntl;
        }
    }

    return handle(env, result, "Force failed");
}

JNIEXPORT jlong JNICALL
Java_sun_nio_ch_FileDispatcherImpl_transferTo0(JNIEnv *env, jobject this,
                                               jobject srcFDO,
                                               jlong position, jlong count,
                                               jobject dstFDO, jboolean append)
{
    jint srcFD = fdval(env, srcFDO);
    jint dstFD = fdval(env, dstFDO);

    off_t numBytes;
    int result;

    numBytes = count;

    result = sendfile(srcFD, dstFD, position, &numBytes, NULL, 0);

    if (numBytes > 0)
        return numBytes;

    if (result == -1) {
        if (errno == EAGAIN)
            return IOS_UNAVAILABLE;
        if (errno == EOPNOTSUPP || errno == ENOTSOCK || errno == ENOTCONN)
            return IOS_UNSUPPORTED_CASE;
        if ((errno == EINVAL) && ((ssize_t)count >= 0))
            return IOS_UNSUPPORTED_CASE;
        if (errno == EINTR)
            return IOS_INTERRUPTED;
        JNU_ThrowIOExceptionWithLastError(env, "Transfer failed");
        return IOS_THROWN;
    }

    return result;
}
