/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

#include <stdlib.h>
#include <string.h>

#include "jni.h"
#include "jni_util.h"
#include "jvm.h"

#include "java_lang_Module.h"

JNIEXPORT void JNICALL
Java_java_lang_Module_defineModule0(JNIEnv *env, jclass cls, jobject module,
                                            jboolean is_open, jstring version,
                                            jstring location, jobjectArray packages)
{
    JVM_DefineModule(env, module, is_open, version, location, packages);
}

JNIEXPORT void JNICALL
Java_java_lang_Module_addReads0(JNIEnv *env, jclass cls, jobject from, jobject to)
{
    JVM_AddReadsModule(env, from, to);
}

JNIEXPORT void JNICALL
Java_java_lang_Module_addExports0(JNIEnv *env, jclass cls, jobject from,
                                  jstring pkg, jobject to)
{
    JVM_AddModuleExports(env, from, pkg, to);
}

JNIEXPORT void JNICALL
Java_java_lang_Module_addExportsToAll0(JNIEnv *env, jclass cls, jobject from,
                                       jstring pkg)
{
    JVM_AddModuleExportsToAll(env, from, pkg);
}

JNIEXPORT void JNICALL
Java_java_lang_Module_addExportsToAllUnnamed0(JNIEnv *env, jclass cls,
                                              jobject from, jstring pkg)
{
    JVM_AddModuleExportsToAllUnnamed(env, from, pkg);
}
