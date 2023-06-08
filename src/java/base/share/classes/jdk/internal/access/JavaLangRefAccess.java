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

package java.base.share.classes.jdk.internal.access;

import java.lang.ref.ReferenceQueue;

public interface JavaLangRefAccess {

    /**
     * Starts the Finalizer and Reference Handler threads.
     */
    void startThreads();

    /**
     * Wait for progress in {@link java.lang.ref.Reference}
     * processing.  If there aren't any pending {@link
     * java.lang.ref.Reference}s, return immediately.
     *
     * @return {@code true} if there were any pending
     * {@link java.lang.ref.Reference}s, {@code false} otherwise.
     */
    boolean waitForReferenceProcessing() throws InterruptedException;

    /**
     * Runs the finalization methods of any objects pending finalization.
     *
     * Invoked by Runtime.runFinalization()
     */
    void runFinalization();

    /**
     * Constructs a new NativeReferenceQueue.
     *
     * Invoked by MethodType.ConcurrentWeakInternSet
     */
    <T> ReferenceQueue<T> newNativeReferenceQueue();
}
