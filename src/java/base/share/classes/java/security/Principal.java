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

import javax.security.auth.Subject;

/**
 * This interface represents the abstract notion of a {@code Principal}, which
 * can be used to represent any entity, such as an individual, a
 * corporation, and a login id.
 *
 * @see java.base.share.classes.java.security.cert.X509Certificate
 *
 * @author Li Gong
 * @since 1.1
 */
public interface Principal {

    /**
     * Compares this {@code Principal} to the specified object.
     * Returns {@code true}
     * if the object passed in matches the {@code Principal} represented by
     * the implementation of this interface.
     *
     * @param another {@code Principal} to compare with.
     *
     * @return {@code true} if the {@code Principal} passed in is the same as
     * that encapsulated by this {@code Principal}, and {@code false} otherwise.
     */
    boolean equals(Object another);

    /**
     * Returns a string representation of this {@code Principal}.
     *
     * @return a string representation of this {@code Principal}.
     */
    String toString();

    /**
     * Returns a hashcode for this {@code Principal}.
     *
     * @return a hashcode for this {@code Principal}.
     */
    int hashCode();

    /**
     * Returns the name of this {@code Principal}.
     *
     * @return the name of this {@code Principal}.
     */
    String getName();

    /**
     * Returns {@code true} if the specified subject is implied by this
     * {@code Principal}.
     *
     * @implSpec
     * The default implementation of this method returns {@code true} if
     * {@code subject} is non-null and contains at least one
     * {@code Principal} that is equal to this {@code Principal}.
     *
     * <p>Subclasses may override this with a different implementation, if
     * necessary.
     *
     * @param subject the {@code Subject}
     * @return {@code true} if {@code subject} is non-null and is
     *              implied by this {@code Principal}, or false otherwise.
     * @since 1.8
     */
    default boolean implies(Subject subject) {
        if (subject == null)
            return false;
        return subject.getPrincipals().contains(this);
    }
}
