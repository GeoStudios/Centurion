/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include <unistd.h>
#include "jni_util.h"


JNIEXPORT jlong JNICALL
Java_jdk_internal_misc_VM_getuid(JNIEnv *env, jclass thisclass) {

    return getuid();
}

JNIEXPORT jlong JNICALL
Java_jdk_internal_misc_VM_geteuid(JNIEnv *env, jclass thisclass) {

    return geteuid();
}

JNIEXPORT jlong JNICALL
Java_jdk_internal_misc_VM_getgid(JNIEnv *env, jclass thisclass) {

    return getgid();
}

JNIEXPORT jlong JNICALL
Java_jdk_internal_misc_VM_getegid(JNIEnv *env, jclass thisclass) {

    return getegid();
}
