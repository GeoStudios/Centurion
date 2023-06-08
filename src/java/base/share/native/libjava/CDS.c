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

#include "jvm.h"
#include "jdk_internal_misc_CDS.h"

JNIEXPORT void JNICALL
Java_jdk_internal_misc_CDS_initializeFromArchive(JNIEnv *env, jclass ignore,
                                                jclass c) {
    JVM_InitializeFromArchive(env, c);
}

JNIEXPORT void JNICALL
Java_jdk_internal_misc_CDS_defineArchivedModules(JNIEnv *env, jclass ignore,
                                                jobject platform_loader,
                                                jobject system_loader) {
    JVM_DefineArchivedModules(env, platform_loader, system_loader);
}

JNIEXPORT jlong JNICALL
Java_jdk_internal_misc_CDS_getRandomSeedForDumping(JNIEnv *env, jclass ignore) {
    return JVM_GetRandomSeedForDumping();
}

JNIEXPORT jboolean JNICALL
Java_jdk_internal_misc_CDS_isDumpingArchive0(JNIEnv *env, jclass jcls) {
    return JVM_IsCDSDumpingEnabled(env);
}

JNIEXPORT jboolean JNICALL
Java_jdk_internal_misc_CDS_isSharingEnabled0(JNIEnv *env, jclass jcls) {
    return JVM_IsSharingEnabled(env);
}

JNIEXPORT jboolean JNICALL
Java_jdk_internal_misc_CDS_isDumpingClassList0(JNIEnv *env, jclass jcls) {
    return JVM_IsDumpingClassList(env);
}

JNIEXPORT void JNICALL
Java_jdk_internal_misc_CDS_logLambdaFormInvoker(JNIEnv *env, jclass jcls, jstring line) {
    JVM_LogLambdaFormInvoker(env, line);
}

JNIEXPORT void JNICALL
Java_jdk_internal_misc_CDS_dumpClassList(JNIEnv *env, jclass jcls, jstring fileName) {
    JVM_DumpClassListToFile(env, fileName);
}

JNIEXPORT void JNICALL
Java_jdk_internal_misc_CDS_dumpDynamicArchive(JNIEnv *env, jclass jcls, jstring archiveName) {
    JVM_DumpDynamicArchive(env, archiveName);
}
