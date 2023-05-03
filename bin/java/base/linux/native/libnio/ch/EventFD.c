/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

 #include <sys/eventfd.h>

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"
#include "nio.h"
#include "nio_util.h"

#include "sun_nio_ch_EventFD.h"

/**
 * @since Java 2
 * @author Logan Abernathy
 * @edited 30/4/2023
*/

JNIEXPORT jint JNICALL
Java_sun_nio_ch_EventFD_eventfd0(JNIEnv *env, jclass klazz)
{
    int efd = eventfd((uint64_t)0, 0);
    if (efd == -1) {
        JNU_ThrowIOExceptionWithLastError(env, "eventfd failed");
        return IOS_THROWN;
    }
    return efd;
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_EventFD_set0(JNIEnv *env, jclass klazz, jint efd)
{
    uint64_t one = 1ULL;
    return convertReturnVal(env, write(efd, (void*)&one, sizeof(uint64_t)),
        JNI_FALSE);
}
