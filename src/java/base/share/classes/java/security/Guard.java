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
 * <p> This interface represents a guard, which is an object that is used
 * to protect access to another object.
 *
 * <p>This interface contains a single method, {@code checkGuard},
 * with a single {@code object} argument. {@code checkGuard} is
 * invoked (by the GuardedObject {@code getObject} method)
 * to determine whether to allow access to the object.
 *
 * @see GuardedObject
 *
 * @author Roland Schemers
 * @author Li Gong
 * @since 1.2
 */

public interface Guard {

    /**
     * Determines whether to allow access to the guarded object
     * {@code object}. Returns silently if access is allowed.
     * Otherwise, throws a {@code SecurityException}.
     *
     * @param object the object being protected by the guard.
     *
     * @throws    SecurityException if access is denied.
     *
     */
    void checkGuard(Object object) throws SecurityException;
}
