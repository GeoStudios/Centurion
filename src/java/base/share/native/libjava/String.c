/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jvm.h"
#include "java_lang_String.h"

JNIEXPORT jobject JNICALL
Java_java_lang_String_intern(JNIEnv *env, jobject this)
{
    return JVM_InternString(env, this);
}

JNIEXPORT jboolean JNICALL
Java_java_lang_StringUTF16_isBigEndian(JNIEnv *env, jclass cls)
{
  unsigned int endianTest = 0xff000000;
  if (((char*)(&endianTest))[0] != 0) {
    return JNI_TRUE;
  } else {
    return JNI_FALSE;
  }
}
