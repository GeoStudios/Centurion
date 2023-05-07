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

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>

#include <string.h>

#include "jni.h"

#ifndef MAXDNAME
#define MAXDNAME                1025
#endif


/*
 * Class:     sun_net_dns_ResolverConfigurationImpl
 * Method:    loadConfig0
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL
Java_sun_net_dns_ResolverConfigurationImpl_fallbackDomain0(JNIEnv *env, jclass cls)
{
    char buf[MAXDNAME];

    /*
     * If domain or search directives aren't specified
     * then gethostname is used.
     */

    if (gethostname(buf, sizeof(buf)) == 0) {
        char *cp;

        /* gethostname doesn't null terminate if insufficient space */
        buf[sizeof(buf)-1] = '\0';

        cp = strchr(buf, '.');
        if (cp != NULL) {
            jstring s = (*env)->NewStringUTF(env, cp+1);
            return s;
        }
    }

    return (jstring)NULL;
}
