/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include <unistd.h>
#include <sys/sysinfo.h>

#include "jni.h"
#include "jvm.h"

#include "jdk_internal_platform_CgroupMetrics.h"

/**
 * @since Java 2
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
