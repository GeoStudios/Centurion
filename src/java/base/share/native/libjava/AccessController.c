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
