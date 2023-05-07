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
#include "jvm.h"

#include "java_io_ObjectStreamClass.h"

static jclass noSuchMethodErrCl;

/*
 * Class:     java_io_ObjectStreamClass
 * Method:    initNative
 * Signature: ()V
 *
 * Native code initialization hook.
 */
JNIEXPORT void JNICALL
Java_java_io_ObjectStreamClass_initNative(JNIEnv *env, jclass this)
{
    jclass cl = (*env)->FindClass(env, "java/lang/NoSuchMethodError");
    if (cl == NULL) {           /* exception thrown */
        return;
    }
    noSuchMethodErrCl = (*env)->NewGlobalRef(env, cl);
}

/*
 * Class:     java_io_ObjectStreamClass
 * Method:    hasStaticInitializer
 * Signature: (Ljava/lang/Class;)Z
 *
 * Returns true if the given class defines a <clinit>()V method; returns false
 * otherwise.
 */
JNIEXPORT jboolean JNICALL
Java_java_io_ObjectStreamClass_hasStaticInitializer(JNIEnv *env, jclass this,
                                                    jclass clazz)
{
    jclass superCl = NULL;
    jmethodID superClinitId = NULL;
    jmethodID clinitId =
        (*env)->GetStaticMethodID(env, clazz, "<clinit>", "()V");
    if (clinitId == NULL) {     /* error thrown */
        jthrowable th = (*env)->ExceptionOccurred(env);
        (*env)->ExceptionClear(env);    /* normal return */
        if (!(*env)->IsInstanceOf(env, th, noSuchMethodErrCl)) {
            (*env)->Throw(env, th);
        }
        return JNI_FALSE;
    }

    /*
     * Check superclass for static initializer as well--if the same method ID
     * is returned, then the static initializer is from a superclass.
     * Empirically, this step appears to be unnecessary in 1.4; however, the
     * JNI spec makes no guarantee that GetStaticMethodID will not return the
     * ID for a superclass initializer.
     */

    if ((superCl = (*env)->GetSuperclass(env, clazz)) == NULL) {
        return JNI_TRUE;
    }
    superClinitId =
        (*env)->GetStaticMethodID(env, superCl, "<clinit>", "()V");
    if (superClinitId == NULL) {        /* error thrown */
        jthrowable th = (*env)->ExceptionOccurred(env);
        (*env)->ExceptionClear(env);    /* normal return */
        if (!(*env)->IsInstanceOf(env, th, noSuchMethodErrCl)) {
            (*env)->Throw(env, th);
        }
        return JNI_TRUE;
    }

    return (clinitId != superClinitId);
}
