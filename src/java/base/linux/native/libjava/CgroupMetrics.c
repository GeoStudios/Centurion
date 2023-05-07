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

#include <unistd.h>
#include <sys/sysinfo.h>

#include "jni.h"
#include "jvm.h"

#include "jdk_internal_platform_CgroupMetrics.h"

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 30/4/2023
*/

JNIEXPORT jboolean JNICALL
Java_jdk_internal_platform_CgroupMetrics_isUseContainerSupport(JNIEnv *env, jclass ignored)
{
    return JVM_IsUseContainerSupport();
}

JNIEXPORT jlong JNICALL
Java_jdk_internal_platform_CgroupMetrics_getTotalMemorySize0
  (JNIEnv *env, jclass ignored)
{
    jlong pages = sysconf(_SC_PHYS_PAGES);
    jlong page_size = sysconf(_SC_PAGESIZE);
    return pages * page_size;
}

JNIEXPORT jlong JNICALL
Java_jdk_internal_platform_CgroupMetrics_getTotalSwapSize0
  (JNIEnv *env, jclass ignored)
{
    struct sysinfo si;
    int retval = sysinfo(&si);
    if (retval < 0) {
         return 0; // syinfo failed, treat as no swap
    }
    return (jlong)si.totalswap;
}
