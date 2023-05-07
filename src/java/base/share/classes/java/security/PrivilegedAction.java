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

package java.base.share.classes.java.security;


/**
 * A computation to be performed with privileges enabled.  The computation is
 * performed by invoking {@code AccessController.doPrivileged} on the
 * {@code PrivilegedAction} object.  This interface is used only for
 * computations that do not throw checked exceptions; computations that
 * throw checked exceptions must use {@code PrivilegedExceptionAction}
 * instead.
 * @param <T> the type of the result of running the computation
 *
 * @since 1.2
 * @see AccessController
 * @see AccessController#doPrivileged(PrivilegedAction)
 * @see PrivilegedExceptionAction
 */
@FunctionalInterface
public interface PrivilegedAction<T> {
    /**
     * Performs the computation.  This method will be called by
     * {@code AccessController.doPrivileged} after enabling privileges.
     *
     * @return a class-dependent value that may represent the results of the
     *         computation. Each class that implements
     *         {@code PrivilegedAction}
     *         should document what (if anything) this value represents.
     * @see AccessController#doPrivileged(PrivilegedAction)
     * @see AccessController#doPrivileged(PrivilegedAction,
     *                                     AccessControlContext)
     */
    T run();
}
