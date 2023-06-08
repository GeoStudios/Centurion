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

#include <string.h>

#include "java_net_InetAddress.h"
#include "net_util.h"

/************************************************************************
 * InetAddress
 */

jclass ia_class;
jclass iac_class;
jfieldID ia_holderID;
jfieldID iac_addressID;
jfieldID iac_familyID;
jfieldID iac_hostNameID;
jfieldID iac_origHostNameID;

static int ia_initialized = 0;

/*
 * Class:     java_net_InetAddress
 * Method:    init
 * Signature: ()V
 */
JNIEXPORT void JNICALL
Java_java_net_InetAddress_init(JNIEnv *env, jclass cls) {
    if (!ia_initialized) {
        jclass c = (*env)->FindClass(env,"java/net/InetAddress");
        CHECK_NULL(c);
        ia_class = (*env)->NewGlobalRef(env, c);
        CHECK_NULL(ia_class);
        c = (*env)->FindClass(env,"java/net/InetAddress$InetAddressHolder");
        CHECK_NULL(c);
        iac_class = (*env)->NewGlobalRef(env, c);
        CHECK_NULL(iac_class);
        ia_holderID = (*env)->GetFieldID(env, ia_class, "holder", "Ljava/net/InetAddress$InetAddressHolder;");
        CHECK_NULL(ia_holderID);

        iac_addressID = (*env)->GetFieldID(env, iac_class, "address", "I");
        CHECK_NULL(iac_addressID);
        iac_familyID = (*env)->GetFieldID(env, iac_class, "family", "I");
        CHECK_NULL(iac_familyID);
        iac_hostNameID = (*env)->GetFieldID(env, iac_class, "hostName", "Ljava/lang/String;");
        CHECK_NULL(iac_hostNameID);
        iac_origHostNameID = (*env)->GetFieldID(env, iac_class, "originalHostName", "Ljava/lang/String;");
        CHECK_NULL(iac_origHostNameID);
        ia_initialized = 1;
    }
}

/*
 * Class:     java_net_InetAddress
 * Method:    isIPv4Available
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL
Java_java_net_InetAddress_isIPv4Available(JNIEnv *env, jclass clazz) {
    return ipv4_available();
}

/*
 * Class:     java_net_InetAddress
 * Method:    isIPv6Supported
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL
Java_java_net_InetAddress_isIPv6Supported(JNIEnv *env, jclass clazz) {
    return ipv6_available();
}
