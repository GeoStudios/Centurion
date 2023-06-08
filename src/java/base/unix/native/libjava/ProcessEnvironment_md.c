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

#include <stdlib.h>
#include <string.h>
#include "jni.h"
#include "jni_util.h"

#ifdef __APPLE__
#include <crt_externs.h>
#define environ (*_NSGetEnviron())
#else
/* This is one of the rare times it's more portable to declare an
 * external symbol explicitly, rather than via a system header.
 * The declaration is standardized as part of UNIX98, but there is
 * no standard (not even de-facto) header file where the
 * declaration is to be found.  See:
 * http://www.opengroup.org/onlinepubs/009695399/functions/environ.html
 * http://www.opengroup.org/onlinepubs/009695399/functions/xsh_chap02_02.html
 *
 * "All identifiers in this volume of IEEE Std 1003.1-2001, except
 * environ, are defined in at least one of the headers" (!)
 */
extern char **environ;
#endif

JNIEXPORT jobjectArray JNICALL
Java_java_lang_ProcessEnvironment_environ(JNIEnv *env, jclass ign)
{
    jsize count = 0;
    jsize i, j;
    jobjectArray result;
    jclass byteArrCls = (*env)->FindClass(env, "[B");
    CHECK_NULL_RETURN(byteArrCls, NULL);

    for (i = 0; environ[i]; i++) {
        /* Ignore corrupted environment variables */
        if (strchr(environ[i], '=') != NULL)
            count++;
    }

    result = (*env)->NewObjectArray(env, 2*count, byteArrCls, 0);
    CHECK_NULL_RETURN(result, NULL);

    for (i = 0, j = 0; environ[i]; i++) {
        const char * varEnd = strchr(environ[i], '=');
        /* Ignore corrupted environment variables */
        if (varEnd != NULL) {
            jbyteArray var, val;
            const char * valBeg = varEnd + 1;
            jsize varLength = varEnd - environ[i];
            jsize valLength = strlen(valBeg);
            var = (*env)->NewByteArray(env, varLength);
            CHECK_NULL_RETURN(var, NULL);
            val = (*env)->NewByteArray(env, valLength);
            CHECK_NULL_RETURN(val, NULL);
            (*env)->SetByteArrayRegion(env, var, 0, varLength,
                                       (jbyte*) environ[i]);
            (*env)->SetByteArrayRegion(env, val, 0, valLength,
                                       (jbyte*) valBeg);
            (*env)->SetObjectArrayElement(env, result, 2*j  , var);
            (*env)->SetObjectArrayElement(env, result, 2*j+1, val);
            (*env)->DeleteLocalRef(env, var);
            (*env)->DeleteLocalRef(env, val);
            j++;
        }
    }

    return result;
}
