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

package java.base.share.classes.jdk.internal.ref;

import java.base.share.classes.jdk.internal.misc.InnocuousThread;

import java.lang.ref.Cleaner;
import java.util.concurrent.ThreadFactory;

/**
 * CleanerFactory provides a Cleaner for use within system modules.
 * The cleaner is created on the first reference to the CleanerFactory.
 */
public final class CleanerFactory {

    /* The common Cleaner. */
    private static final Cleaner commonCleaner = Cleaner.create(new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return InnocuousThread.newSystemThread("Common-Cleaner",
                    r, Thread.MAX_PRIORITY - 2);
        }
    });

    /**
     * Cleaner for use within system modules.
     *
     * This Cleaner will run on a thread whose context class loader
     * is {@code null}. The system cleaning action to perform in
     * this Cleaner should handle a {@code null} context class loader.
     *
     * @return a Cleaner for use within system modules
     */
    public static Cleaner cleaner() {
        return commonCleaner;
    }
}
