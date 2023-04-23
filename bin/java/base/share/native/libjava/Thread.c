/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include "jni.h"
#include "jvm.h"

#include "java_lang_Thread.h"

#define THD "Ljava/lang/Thread;"
#define OBJ "Ljava/lang/Object;"
#define STE "Ljava/lang/StackTraceElement;"
#define STR "Ljava/lang/String;"

#define ARRAY_LENGTH(a) (sizeof(a)/sizeof(a[0]))

static JNINativeMethod methods[] = {
    {"start0",           "()V",        (void *)&JVM_StartThread},
    {"isAlive0",         "()Z",        (void *)&JVM_IsThreadAlive},
    {"setPriority0",     "(I)V",       (void *)&JVM_SetThreadPriority},
    {"yield0",           "()V",        (void *)&JVM_Yield},
    {"sleep0",           "(J)V",       (void *)&JVM_Sleep},
    {"currentCarrierThread", "()" THD, (void *)&JVM_CurrentCarrierThread},
    {"currentThread",    "()" THD,     (void *)&JVM_CurrentThread},
    {"setCurrentThread", "(" THD ")V", (void *)&JVM_SetCurrentThread},
    {"interrupt0",       "()V",        (void *)&JVM_Interrupt},
    {"holdsLock",        "(" OBJ ")Z", (void *)&JVM_HoldsLock},
    {"getThreads",       "()[" THD,    (void *)&JVM_GetAllThreads},
    {"dumpThreads",      "([" THD ")[[" STE, (void *)&JVM_DumpThreads},
    {"getStackTrace0",   "()" OBJ,     (void *)&JVM_GetStackTrace},
    {"setNativeName",    "(" STR ")V", (void *)&JVM_SetNativeThreadName},
    {"scopedValueCache", "()[" OBJ,    (void *)&JVM_ScopedValueCache},
    {"setScopedValueCache", "([" OBJ ")V",(void *)&JVM_SetScopedValueCache},
    {"getNextThreadIdOffset", "()J",   (void *)&JVM_GetNextThreadIdOffset},
    {"findScopedValueBindings", "()" OBJ, (void *)&JVM_FindScopedValueBindings},
    {"ensureMaterializedForStackWalk",
                         "(" OBJ ")V", (void*)&JVM_EnsureMaterializedForStackWalk_func},
};

#undef THD
#undef OBJ
#undef STE
#undef STR

JNIEXPORT void JNICALL
Java_java_lang_Thread_registerNatives(JNIEnv *env, jclass cls)
{
    (*env)->RegisterNatives(env, cls, methods, ARRAY_LENGTH(methods));
}

JNIEXPORT void JNICALL
Java_java_lang_Thread_clearInterruptEvent(JNIEnv *env, jclass cls)
{
#if defined(_WIN32)
    // Need to reset the interrupt event used by Process.waitFor
    ResetEvent((HANDLE) JVM_GetThreadInterruptEvent());
#endif
}
