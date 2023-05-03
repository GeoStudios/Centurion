/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include <sys/sendfile.h>
#include <dlfcn.h>

#include "jni.h"
#include "nio.h"
#include "nio_util.h"
#include "sun_nio_ch_FileDispatcherImpl.h"

/**
 * @since Java 2
 * @author Logan Abernathy
 * @edited 30/4/2023
*/

typedef ssize_t copy_file_range_func(int, loff_t*, int, loff_t*, size_t,
                                     unsigned int);
static copy_file_range_func* my_copy_file_range_func = NULL;

JNIEXPORT void JNICALL
Java_sun_nio_ch_FileDispatcherImpl_init0(JNIEnv *env, jclass klass)
{
    my_copy_file_range_func =
        (copy_file_range_func*) dlsym(RTLD_DEFAULT, "copy_file_range");
}

JNIEXPORT jlong JNICALL
Java_sun_nio_ch_FileDispatcherImpl_transferFrom0(JNIEnv *env, jobject this,
                                              jobject srcFDO, jobject dstFDO,
                                              jlong position, jlong count,
                                              jboolean append)
{
    if (my_copy_file_range_func == NULL)
        return IOS_UNSUPPORTED;
    // copy_file_range fails with EBADF when appending
    if (append == JNI_TRUE)
        return IOS_UNSUPPORTED_CASE;

    jint srcFD = fdval(env, srcFDO);
    jint dstFD = fdval(env, dstFDO);

    off64_t offset = (off64_t)position;
    size_t len = (size_t)count;
    jlong n = my_copy_file_range_func(srcFD, NULL, dstFD, &offset, len, 0);
    if (n < 0) {
        if (errno == EAGAIN)
            return IOS_UNAVAILABLE;
        if (errno == ENOSYS)
            return IOS_UNSUPPORTED_CASE;
        if ((errno == EBADF || errno == EINVAL || errno == EXDEV) &&
            ((ssize_t)count >= 0))
            return IOS_UNSUPPORTED_CASE;
        if (errno == EINTR) {
            return IOS_INTERRUPTED;
        }
        JNU_ThrowIOExceptionWithLastError(env, "Transfer failed");
        return IOS_THROWN;
    }
    return n;
}

JNIEXPORT jlong JNICALL
Java_sun_nio_ch_FileDispatcherImpl_transferTo0(JNIEnv *env, jobject this,
                                            jobject srcFDO,
                                            jlong position, jlong count,
                                            jobject dstFDO, jboolean append)
{
    jint srcFD = fdval(env, srcFDO);
    jint dstFD = fdval(env, dstFDO);

    // copy_file_range fails with EBADF when appending, and sendfile
    // fails with EINVAL
    if (append == JNI_TRUE)
        return IOS_UNSUPPORTED_CASE;

    off64_t offset = (off64_t)position;
    jlong n;
    if (my_copy_file_range_func != NULL) {
        size_t len = (size_t)count;
        n = my_copy_file_range_func(srcFD, &offset, dstFD, NULL, len, 0);
        if (n < 0) {
            switch (errno) {
                case EINTR:
                    return IOS_INTERRUPTED;
                case EINVAL:
                case ENOSYS:
                case EXDEV:
                    // ignore and try sendfile()
                    break;
                default:
                    JNU_ThrowIOExceptionWithLastError(env, "Copy failed");
                    return IOS_THROWN;
            }
        }
        if (n >= 0)
            return n;
    }

    n = sendfile64(dstFD, srcFD, &offset, (size_t)count);
    if (n < 0) {
        if (errno == EAGAIN)
            return IOS_UNAVAILABLE;
        if ((errno == EINVAL) && ((ssize_t)count >= 0))
            return IOS_UNSUPPORTED_CASE;
        if (errno == EINTR) {
            return IOS_INTERRUPTED;
        }
        JNU_ThrowIOExceptionWithLastError(env, "Transfer failed");
        return IOS_THROWN;
    }
    return n;
}
