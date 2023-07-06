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

package jdk.dynalink.share.classes.jdk.dynalink.linker;


import jdk.dynalink.share.classes.jdk.dynalink.DynamicLinkerFactory;















/*
 * This file is available under and governed by the GNU General Public
 * License version 2 only, as published by the Free Software Foundation.
 * However, the following notice accompanied the original version of this
 * file, and Oracle licenses the original version of this file under the BSD
 * license:
 */



/**
 * Interface for objects that are used to transform one guarded invocation into
 * another one. Typical usage is for implementing
 * {@link DynamicLinkerFactory#setPrelinkTransformer(GuardedInvocationTransformer)
 * pre-link transformers}.
 */
@FunctionalInterface
public interface GuardedInvocationTransformer {
    /**
     * Given a guarded invocation, return either the same or potentially
     * different guarded invocation.
     * @param inv the original guarded invocation.
     * @param linkRequest the link request for which the invocation was
     * generated (usually by some linker).
     * @param linkerServices the linker services that can be used during
     * creation of a new invocation.
     * @return either the passed guarded invocation or a different one, with
     * the difference usually determined based on information in the link
     * request and the differing invocation created with the assistance of the
     * linker services. Whether or not {@code null} is an accepted return value
     * is dependent on the user of the filter.
     * @throws NullPointerException is allowed to be thrown by implementations
     * if any of the passed arguments is null.
     */
    GuardedInvocation filter(GuardedInvocation inv, LinkRequest linkRequest, LinkerServices linkerServices);
}
