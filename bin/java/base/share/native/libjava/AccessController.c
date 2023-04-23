/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

/*-
 *      Implementation of class java.security.AccessController
 *
 */

#include <string.h>

#include "jni.h"
#include "jvm.h"
#include "java_security_AccessController.h"

JNIEXPORT jobject JNICALL
Java_java_security_AccessController_getProtectionDomain(
                                                              JNIEnv *env,
                                                              jclass cls,
                                                              jclass caller)
{
    return JVM_GetProtectionDomain(env, caller);
}

JNIEXPORT jobject JNICALL
Java_java_security_AccessController_getStackAccessControlContext(
                                                              JNIEnv *env,
                                                              jobject this)
{
    return JVM_GetStackAccessControlContext(env, this);
}


JNIEXPORT jobject JNICALL
Java_java_security_AccessController_getInheritedAccessControlContext(
                                                              JNIEnv *env,
                                                              jobject this)
{
    return JVM_GetInheritedAccessControlContext(env, this);
}

JNIEXPORT void JNICALL
Java_java_security_AccessController_ensureMaterializedForStackWalk(
                                                              JNIEnv *env,
                                                              jclass cls,
                                                              jobject value)
{
    JVM_EnsureMaterializedForStackWalk(env, value);
}
