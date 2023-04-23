/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#ifndef JDK_UTIL_H
#define JDK_UTIL_H

#include "jni.h"
#include "jvm.h"
#include "jdk_util_md.h"

#ifdef __cplusplus
extern "C" {
#endif

/*-------------------------------------------------------
 * Exported interfaces for both JDK and JVM to use
 *-------------------------------------------------------
 */

/*
 * Export the platform dependent path canonicalization so that
 * the VM can find it when loading system classes.
 * This function is also used by the instrumentation agent.
 */
JNIEXPORT int
JDK_Canonicalize(const char *orig, char *out, int len);

#ifdef __cplusplus
} /* extern "C" */
#endif /* __cplusplus */

#endif /* JDK_UTIL_H */
