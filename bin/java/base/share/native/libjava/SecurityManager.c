/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"

#include "java_lang_SecurityManager.h"
#include "java_lang_ClassLoader.h"

/*
 * Make sure a security manager instance is initialized.
 * TRUE means OK, FALSE means not.
 */
static jboolean
check(JNIEnv *env, jobject this)
{
    static jfieldID initField = 0;
    jboolean initialized = JNI_FALSE;

    if (initField == 0) {
        jclass clazz = (*env)->FindClass(env, "java/lang/SecurityManager");
        if (clazz == 0) {
            (*env)->ExceptionClear(env);
            return JNI_FALSE;
        }
        initField = (*env)->GetFieldID(env, clazz, "initialized", "Z");
        if (initField == 0) {
            (*env)->ExceptionClear(env);
            return JNI_FALSE;
        }
    }
    initialized = (*env)->GetBooleanField(env, this, initField);

    if (initialized == JNI_TRUE) {
        return JNI_TRUE;
    } else {
        jclass securityException =
            (*env)->FindClass(env, "java/lang/SecurityException");
        if (securityException != 0) {
            (*env)->ThrowNew(env, securityException,
                             "security manager not initialized.");
        }
        return JNI_FALSE;
    }
}

JNIEXPORT jobjectArray JNICALL
Java_java_lang_SecurityManager_getClassContext(JNIEnv *env, jobject this)
{
    if (!check(env, this)) {
        return NULL;            /* exception */
    }

    return JVM_GetClassContext(env);
}
