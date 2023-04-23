/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include <stdio.h>
#include <signal.h>

#include "jni.h"
#include "jvm.h"

#include "java_lang_Throwable.h"

/*
 * Fill in the current stack trace in this exception.  This is
 * usually called automatically when the exception is created but it
 * may also be called explicitly by the user.  This routine returns
 * `this' so you can write 'throw e.fillInStackTrace();'
 */
JNIEXPORT jobject JNICALL
Java_java_lang_Throwable_fillInStackTrace(JNIEnv *env, jobject throwable, jint dummy)
{
    JVM_FillInStackTrace(env, throwable);
    return throwable;
}
