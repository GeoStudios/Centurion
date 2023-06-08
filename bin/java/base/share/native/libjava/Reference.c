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
