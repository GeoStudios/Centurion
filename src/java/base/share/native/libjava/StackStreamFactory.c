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

/*
 *      Implementation of class StackStreamFactory and AbstractStackWalker
 */

#include <stdio.h>
#include <signal.h>

#include "jni.h"
#include "jvm.h"

#include "java_lang_StackStreamFactory.h"
#include "java_lang_StackStreamFactory_AbstractStackWalker.h"

/*
 * Class:     java_lang_StackStreamFactory
 * Method:    checkStackWalkModes
 * Signature: ()
 */
JNIEXPORT jboolean JNICALL Java_java_lang_StackStreamFactory_checkStackWalkModes
  (JNIEnv *env, jclass dummy)
{
   return JVM_STACKWALK_FILL_CLASS_REFS_ONLY == java_lang_StackStreamFactory_FILL_CLASS_REFS_ONLY &&
          JVM_STACKWALK_SHOW_HIDDEN_FRAMES == java_lang_StackStreamFactory_SHOW_HIDDEN_FRAMES &&
          JVM_STACKWALK_FILL_LIVE_STACK_FRAMES == java_lang_StackStreamFactory_FILL_LIVE_STACK_FRAMES;
}

/*
 * Class:     java_lang_StackStreamFactory_AbstractStackWalker
 * Method:    callStackWalk
 * Signature: (JILjdk/internal/vm/ContinuationScope;Ljdk/internal/vm/Continuation;II[Ljava/lang/Object;)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL Java_java_lang_StackStreamFactory_00024AbstractStackWalker_callStackWalk
  (JNIEnv *env, jobject stackstream, jlong mode, jint skipFrames, jobject contScope, jobject cont,
   jint batchSize, jint startIndex, jobjectArray frames)
{
    return JVM_CallStackWalk(env, stackstream, mode, skipFrames, contScope, cont,
                             batchSize, startIndex, frames);
}

/*
 * Class:     java_lang_StackStreamFactory_AbstractStackWalker
 * Method:    fetchStackFrames
 * Signature: (JJII[Ljava/lang/Object;)I
 */
JNIEXPORT jint JNICALL Java_java_lang_StackStreamFactory_00024AbstractStackWalker_fetchStackFrames
  (JNIEnv *env, jobject stackstream, jlong mode, jlong anchor,
   jint batchSize, jint startIndex,
   jobjectArray frames)
{
    return JVM_MoreStackWalk(env, stackstream, mode, anchor, batchSize,
                             startIndex, frames);
}

/*
 * Class:     java_lang_StackStreamFactory_AbstractStackWalker
 * Method:    setContinuation
 * Signature: (J[Ljava/lang/Object;Ljdk/internal/vm/Continuation;)V
 */
JNIEXPORT void JNICALL Java_java_lang_StackStreamFactory_00024AbstractStackWalker_setContinuation
  (JNIEnv *env, jobject stackstream, jlong anchor, jobjectArray frames, jobject cont)
{
    JVM_SetStackWalkContinuation(env, stackstream, anchor, frames, cont);
}
