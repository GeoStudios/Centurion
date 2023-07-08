/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package java.base.share.classes.java.security.cert;

/**
 * A selector that defines a set of criteria for selecting
 * {@code Certificate}s. Classes that implement this interface
 * are often used to specify which {@code Certificate}s should
 * be retrieved from a {@code CertStore}.
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * Unless otherwise specified, the methods defined in this interface are not
 * thread-safe. Multiple threads that need to access a single
 * object concurrently should synchronize amongst themselves and
 * provide the necessary locking. Multiple threads each manipulating
 * separate objects need not synchronize.
 *
 * @see Certificate
 * @see CertStore
 * @see CertStore#getCertificates
 *
 */
public interface CertSelector extends Cloneable {

    /**
     * Decides whether a {@code Certificate} should be selected.
     *
     * @param   cert    the {@code Certificate} to be checked
     * @return  {@code true} if the {@code Certificate}
     * should be selected, {@code false} otherwise
     */
    boolean match(Certificate cert);

    /**
     * Makes a copy of this {@code CertSelector}. Changes to the
     * copy will not affect the original and vice versa.
     *
     * @return a copy of this {@code CertSelector}
     */
    Object clone();
}
