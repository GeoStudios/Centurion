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

#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/types.h>
#include <unistd.h>

#include <string.h>
#include <sys/socket.h>

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"
#include "nio.h"
#include "nio_util.h"
#include "java_lang_Integer.h"
#include <assert.h>

/**
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 30/4/2023
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

// AIX
JNIEXPORT jint JNICALL
Java_sun_nio_ch_FileDispatcherImpl_force0(JNIEnv *env, jobject this,
                                          jobject fdo, jboolean md)
{
    jint fd = fdval(env, fdo);
    int result = 0;

    if (md == JNI_FALSE) {
        result = fdatasync(fd);
    } else {
        /* Calling fsync on a file descriptor that is opened only for
         * reading results in an error ("EBADF: The FileDescriptor parameter is
         * not a valid file descriptor open for writing.").
         * However, at this point it is not possibly anymore to read the
         * 'writable' attribute of the corresponding file channel so we have to
         * use 'fcntl'.
         */
        int getfl = fcntl(fd, F_GETFL);
        if (getfl >= 0 && (getfl & O_ACCMODE) == O_RDONLY) {
            return 0;
        }
        result = fsync(fd);
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

    jlong max = (jlong)java_lang_Integer_MAX_VALUE;
    struct sf_parms sf_iobuf;
    jlong result;

    if (position > max)
        return IOS_UNSUPPORTED_CASE;

    if (count > max)
        count = max;

    memset(&sf_iobuf, 0, sizeof(sf_iobuf));
    sf_iobuf.file_descriptor = srcFD;
    sf_iobuf.file_offset = (off_t)position;
    sf_iobuf.file_bytes = count;

    result = send_file(&dstFD, &sf_iobuf, SF_SYNC_CACHE);

    /* AIX send_file() will return 0 when this operation complete successfully,
     * return 1 when partial bytes transferred and return -1 when an error has
     * occurred.
     */
    if (result == -1) {
        if (errno == EWOULDBLOCK)
            return IOS_UNAVAILABLE;
        if ((errno == EINVAL) && ((ssize_t)count >= 0))
            return IOS_UNSUPPORTED_CASE;
        if (errno == EINTR)
            return IOS_INTERRUPTED;
        if (errno == ENOTSOCK)
            return IOS_UNSUPPORTED;
        JNU_ThrowIOExceptionWithLastError(env, "Transfer failed");
        return IOS_THROWN;
    }

    if (sf_iobuf.bytes_sent > 0)
        return (jlong)sf_iobuf.bytes_sent;

    return IOS_UNSUPPORTED_CASE;
}
