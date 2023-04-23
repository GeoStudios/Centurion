/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
