/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jni.h"
#include "jni_util.h"
#include "jlong.h"
#include "jvm.h"

#include "java_lang_invoke_LambdaProxyClassArchive.h"

JNIEXPORT void JNICALL
Java_java_lang_invoke_LambdaProxyClassArchive_addToArchive(JNIEnv *env, jclass ignore,
                                                               jclass caller,
                                                               jstring interfaceMethodName,
                                                               jobject factoryType,
                                                               jobject interfaceMethodType,
                                                               jobject implementationMember,
                                                               jobject dynamicMethodType,
                                                               jclass lambdaProxyClass) {
    JVM_RegisterLambdaProxyClassForArchiving(env, caller, interfaceMethodName, factoryType,
                                             interfaceMethodType, implementationMember,
                                             dynamicMethodType, lambdaProxyClass);
}

JNIEXPORT jclass JNICALL
Java_java_lang_invoke_LambdaProxyClassArchive_findFromArchive(JNIEnv *env, jclass ignore,
                                                            jclass caller,
                                                            jstring interfaceMethodName,
                                                            jobject factoryType,
                                                            jobject interfaceMethodType,
                                                            jobject implementationMember,
                                                            jobject dynamicMethodType) {
    return JVM_LookupLambdaProxyClassFromArchive(env, caller, interfaceMethodName, factoryType,
                                                 interfaceMethodType, implementationMember,
                                                 dynamicMethodType);
}
