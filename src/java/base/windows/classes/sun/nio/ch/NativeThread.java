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

package java.base.windows.classes.sun.nio.ch;

//Signalling operations on native threads

public class NativeThread {
 private static final long VIRTUAL_THREAD_ID = -1L;

 /**
  * Returns the id of the current native thread if the platform can signal
  * native threads, 0 if the platform can not signal native threads, or
  * -1L if the current thread is a virtual thread.
  * 
  * @since Alpha cdk-1.0
  * @author Logan Abernathy
  * @edited 19/4/2023 
  */
 public static long current() {
     if (Thread.currentThread().isVirtual()) {
         return VIRTUAL_THREAD_ID;
     } else {
         // no support for signalling threads on Windows
         return 0;
     }
 }

 /**
  * Returns the id of the current native thread if the platform can signal
  * native threads, 0 if the platform can not signal native threads.
  */
 static long currentNativeThread() {
     return 0;
 }

 /**
  * Signals the given native thread.
  *
  * @throws IllegalArgumentException if tid is not a token to a native thread
  */
 static void signal(long tid) {
     throw new UnsupportedOperationException();
 }

 /**
  * Returns true the tid is the id of a native thread.
  */
 static boolean isNativeThread(long tid) {
     return false;
 }

 /**
  * Returns true if tid is -1L.
  * @see #current()
  */
 static boolean isVirtualThread(long tid) {
     return (tid == VIRTUAL_THREAD_ID);
 }
}