/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include <poll.h>

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"
#include "nio.h"
#include "sun_nio_ch_PollSelectorImpl.h"

JNIEXPORT jint JNICALL
Java_sun_nio_ch_PollSelectorImpl_poll(JNIEnv *env, jclass clazz,
                                      jlong address, jint numfds,
                                      jint timeout)
{
    struct pollfd *a;
    int res;

    a = (struct pollfd *) jlong_to_ptr(address);
    res = poll(a, numfds, timeout);
    if (res < 0) {
        if (errno == EINTR) {
            return IOS_INTERRUPTED;
        } else {
            JNU_ThrowIOExceptionWithLastError(env, "poll failed");
            return IOS_THROWN;
        }
    }
    return (jint) res;
}

