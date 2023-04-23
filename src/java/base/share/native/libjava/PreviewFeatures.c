/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jni.h"
#include "jvm.h"

#include "jdk_internal_misc_PreviewFeatures.h"

JNIEXPORT jboolean JNICALL
Java_jdk_internal_misc_PreviewFeatures_isPreviewEnabled(JNIEnv *env, jclass cls) {
    return JVM_IsPreviewEnabled();
}
