/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jvm.h"

#include "java_lang_ref_Finalizer.h"

JNIEXPORT void JNICALL
Java_java_lang_ref_Finalizer_reportComplete(JNIEnv* env, jclass cls, jobject finalizee) {
    JVM_ReportFinalizationComplete(env, finalizee);
}

JNIEXPORT jboolean JNICALL
Java_java_lang_ref_Finalizer_isFinalizationEnabled(JNIEnv* env, jclass cls) {
    return JVM_IsFinalizationEnabled(env);
}
