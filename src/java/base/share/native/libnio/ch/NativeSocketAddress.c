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
