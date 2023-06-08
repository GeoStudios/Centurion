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
