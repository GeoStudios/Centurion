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

 #include <sys/eventfd.h>

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"
#include "jlong.h"
#include "nio.h"
#include "nio_util.h"

#include "sun_nio_ch_EventFD.h"

/**
 * @since Alpha cdk-1.1
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
