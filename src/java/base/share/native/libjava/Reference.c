/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jvm.h"
#include "java_lang_ref_Reference.h"

JNIEXPORT jobject JNICALL
Java_java_lang_ref_Reference_getAndClearReferencePendingList(JNIEnv *env, jclass ignore)
{
    return JVM_GetAndClearReferencePendingList(env);
}

JNIEXPORT jboolean JNICALL
Java_java_lang_ref_Reference_hasReferencePendingList(JNIEnv *env, jclass ignore)
{
    return JVM_HasReferencePendingList(env);
}

JNIEXPORT void JNICALL
Java_java_lang_ref_Reference_waitForReferencePendingList(JNIEnv *env, jclass ignore)
{
    JVM_WaitForReferencePendingList(env);
}

JNIEXPORT jboolean JNICALL
Java_java_lang_ref_Reference_refersTo0(JNIEnv *env, jobject ref, jobject o)
{
    return JVM_ReferenceRefersTo(env, ref, o);
}

JNIEXPORT void JNICALL
Java_java_lang_ref_Reference_clear0(JNIEnv *env, jobject ref)
{
    JVM_ReferenceClear(env, ref);
}
