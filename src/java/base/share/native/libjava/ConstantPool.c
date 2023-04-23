/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jvm.h"
#include "jdk_internal_reflect_ConstantPool.h"

JNIEXPORT jint JNICALL Java_jdk_internal_reflect_ConstantPool_getSize0
(JNIEnv *env, jobject unused, jobject jcpool)
{
  return JVM_ConstantPoolGetSize(env, unused, jcpool);
}

JNIEXPORT jclass JNICALL Java_jdk_internal_reflect_ConstantPool_getClassAt0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstantPoolGetClassAt(env, unused, jcpool, index);
}

JNIEXPORT jclass JNICALL Java_jdk_internal_reflect_ConstantPool_getClassAtIfLoaded0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstantPoolGetClassAtIfLoaded(env, unused, jcpool, index);
}

JNIEXPORT jint JNICALL Java_jdk_internal_reflect_ConstantPool_getClassRefIndexAt0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
    return JVM_ConstantPoolGetClassRefIndexAt(env, unused, jcpool, index);
}

JNIEXPORT jobject JNICALL Java_jdk_internal_reflect_ConstantPool_getMethodAt0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstantPoolGetMethodAt(env, unused, jcpool, index);
}

JNIEXPORT jobject JNICALL Java_jdk_internal_reflect_ConstantPool_getMethodAtIfLoaded0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstantPoolGetMethodAtIfLoaded(env, unused, jcpool, index);
}

JNIEXPORT jobject JNICALL Java_jdk_internal_reflect_ConstantPool_getFieldAt0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstantPoolGetFieldAt(env, unused, jcpool, index);
}

JNIEXPORT jobject JNICALL Java_jdk_internal_reflect_ConstantPool_getFieldAtIfLoaded0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstantPoolGetFieldAtIfLoaded(env, unused, jcpool, index);
}

JNIEXPORT jobjectArray JNICALL Java_jdk_internal_reflect_ConstantPool_getMemberRefInfoAt0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstantPoolGetMemberRefInfoAt(env, unused, jcpool, index);
}

JNIEXPORT jint JNICALL Java_jdk_internal_reflect_ConstantPool_getNameAndTypeRefIndexAt0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
    return JVM_ConstantPoolGetNameAndTypeRefIndexAt(env, unused, jcpool, index);
}

JNIEXPORT jobjectArray JNICALL Java_jdk_internal_reflect_ConstantPool_getNameAndTypeRefInfoAt0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstantPoolGetNameAndTypeRefInfoAt(env, unused, jcpool, index);
}

JNIEXPORT jint JNICALL Java_jdk_internal_reflect_ConstantPool_getIntAt0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstantPoolGetIntAt(env, unused, jcpool, index);
}

JNIEXPORT jlong JNICALL Java_jdk_internal_reflect_ConstantPool_getLongAt0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstantPoolGetLongAt(env, unused, jcpool, index);
}

JNIEXPORT jfloat JNICALL Java_jdk_internal_reflect_ConstantPool_getFloatAt0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstantPoolGetFloatAt(env, unused, jcpool, index);
}

JNIEXPORT jdouble JNICALL Java_jdk_internal_reflect_ConstantPool_getDoubleAt0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstantPoolGetDoubleAt(env, unused, jcpool, index);
}

JNIEXPORT jstring JNICALL Java_jdk_internal_reflect_ConstantPool_getStringAt0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstantPoolGetStringAt(env, unused, jcpool, index);
}

JNIEXPORT jstring JNICALL Java_jdk_internal_reflect_ConstantPool_getUTF8At0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstantPoolGetUTF8At(env, unused, jcpool, index);
}

JNIEXPORT jbyte JNICALL Java_jdk_internal_reflect_ConstantPool_getTagAt0
(JNIEnv *env, jobject unused, jobject jcpool, jint index)
{
  return JVM_ConstantPoolGetTagAt(env, unused, jcpool, index);
}

