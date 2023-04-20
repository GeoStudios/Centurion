/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
