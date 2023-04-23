/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

 #include "jni.h"
 #include "net_util.h"

 #include "sun_nio_ch_NativeSocketAddress.h"

 JNIEXPORT jint JNICALL
 Java_sun_nio_ch_NativeSocketAddress_AFINET(JNIEnv* env, jclass clazz)
 {
     return AF_INET;
 }

 JNIEXPORT jint JNICALL
 Java_sun_nio_ch_NativeSocketAddress_AFINET6(JNIEnv* env, jclass clazz)
 {
     return AF_INET6;
 }

JNIEXPORT jint JNICALL
Java_sun_nio_ch_NativeSocketAddress_sizeofSockAddr4(JNIEnv* env, jclass clazz)
{
    return sizeof(struct sockaddr_in);
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_NativeSocketAddress_sizeofSockAddr6(JNIEnv* env, jclass clazz)
{
    return sizeof(struct sockaddr_in6);
}

JNIEXPORT jint JNICALL
Java_sun_nio_ch_NativeSocketAddress_sizeofFamily(JNIEnv* env, jclass clazz)
{
    // sizeof(struct sockaddr, sa_family)
    return sizeof(((struct sockaddr *)0)->sa_family);
}

 JNIEXPORT jint JNICALL
 Java_sun_nio_ch_NativeSocketAddress_offsetFamily(JNIEnv* env, jclass clazz)
 {
     return offsetof(struct sockaddr, sa_family);
 }

 JNIEXPORT jint JNICALL
 Java_sun_nio_ch_NativeSocketAddress_offsetSin4Port(JNIEnv* env, jclass clazz)
 {
     return offsetof(struct sockaddr_in, sin_port);
 }

 JNIEXPORT jint JNICALL
 Java_sun_nio_ch_NativeSocketAddress_offsetSin4Addr(JNIEnv* env, jclass clazz)
 {
     return offsetof(struct sockaddr_in, sin_addr);
 }

 JNIEXPORT jint JNICALL
 Java_sun_nio_ch_NativeSocketAddress_offsetSin6Port(JNIEnv* env, jclass clazz)
 {
     return offsetof(struct sockaddr_in6, sin6_port);
 }

 JNIEXPORT jint JNICALL
 Java_sun_nio_ch_NativeSocketAddress_offsetSin6Addr(JNIEnv* env, jclass clazz)
 {
     return offsetof(struct sockaddr_in6, sin6_addr);
 }

 JNIEXPORT jint JNICALL
 Java_sun_nio_ch_NativeSocketAddress_offsetSin6ScopeId(JNIEnv* env, jclass clazz)
 {
     return offsetof(struct sockaddr_in6, sin6_scope_id);
 }

 JNIEXPORT jint JNICALL
 Java_sun_nio_ch_NativeSocketAddress_offsetSin6FlowInfo(JNIEnv* env, jclass clazz)
 {
     return offsetof(struct sockaddr_in6, sin6_flowinfo);
 }
