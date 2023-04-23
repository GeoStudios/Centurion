/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jni.h"
#include "jvm.h"
#include "java_lang_reflect_Field.h"

JNIEXPORT jbyteArray JNICALL
Java_java_lang_reflect_Field_getTypeAnnotationBytes0(JNIEnv *env,
                                                     jobject field) {
    return JVM_GetFieldTypeAnnotations(env, field);
}
