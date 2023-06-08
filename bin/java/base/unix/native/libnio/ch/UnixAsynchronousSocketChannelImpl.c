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

#include <sys/types.h>
#include <sys/socket.h>

#include "jni.h"
#include "jni_util.h"
#include "net_util.h"
#include "jlong.h"
#include "sun_nio_ch_UnixAsynchronousSocketChannelImpl.h"
#include "nio_util.h"
#include "nio.h"


JNIEXPORT void JNICALL
Java_sun_nio_ch_UnixAsynchronousSocketChannelImpl_checkConnect(JNIEnv *env,
    jobject this, int fd)
{
    int error = 0;
    socklen_t arglen = sizeof(error);
    int result;

    result = getsockopt(fd, SOL_SOCKET, SO_ERROR, &error, &arglen);
    if (result < 0) {
        JNU_ThrowIOExceptionWithLastError(env, "getsockopt");
    } else {
        if (error)
            handleSocketError(env, error);
    }
}
