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

#include "java_net_Inet4Address.h"
#include "net_util.h"

/************************************************************************
 * Inet4Address
 */
jclass ia4_class;
jmethodID ia4_ctrID;

static int ia4_initialized = 0;

/*
 * Class:     java_net_Inet4Address
 * Method:    init
 * Signature: ()V
 */
JNIEXPORT void JNICALL
Java_java_net_Inet4Address_init(JNIEnv *env, jclass cls) {
    if (!ia4_initialized) {
        jclass c = (*env)->FindClass(env, "java/net/Inet4Address");
        CHECK_NULL(c);
        ia4_class = (*env)->NewGlobalRef(env, c);
        CHECK_NULL(ia4_class);
        ia4_ctrID = (*env)->GetMethodID(env, ia4_class, "<init>", "()V");
        CHECK_NULL(ia4_ctrID);
        ia4_initialized = 1;
    }
}
