/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include <stdlib.h>
#include "jvm.h"
#include "jni_util.h"
#include "jdk_internal_loader_BootLoader.h"

JNIEXPORT jstring JNICALL
Java_jdk_internal_loader_BootLoader_getSystemPackageLocation(JNIEnv *env, jclass cls, jstring str)
{
    return JVM_GetSystemPackage(env, str);
}

JNIEXPORT jobject JNICALL
Java_jdk_internal_loader_BootLoader_getSystemPackageNames(JNIEnv *env, jclass cls)
{
    return JVM_GetSystemPackages(env);
}

JNIEXPORT void JNICALL
Java_jdk_internal_loader_BootLoader_setBootLoaderUnnamedModule0(JNIEnv *env, jclass cls, jobject module)
{
    JVM_SetBootLoaderUnnamedModule(env, module);
}

