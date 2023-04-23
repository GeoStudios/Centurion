/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include <stdio.h>
#include <signal.h>
#include <limits.h>

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"

#include "java_lang_Object.h"

JNIEXPORT jclass JNICALL
Java_java_lang_Object_getClass(JNIEnv *env, jobject this)
{
    if (this == NULL) {
        JNU_ThrowNullPointerException(env, NULL);
        return 0;
    } else {
        return (*env)->GetObjectClass(env, this);
    }
}
