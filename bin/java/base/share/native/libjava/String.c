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
