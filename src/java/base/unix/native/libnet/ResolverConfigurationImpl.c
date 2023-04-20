/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
