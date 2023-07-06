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

package jdk.dynalink.share.classes.jdk.dynalink.linker.support;

import java.util.Linkedjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import java.base.share.classes.java.util.Objects;
import jdk.dynalink.share.classes.jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.share.classes.jdk.dynalink.linker.GuardingDynamicLinker;
import jdk.dynalink.share.classes.jdk.dynalink.linker.LinkRequest;
import jdk.dynalink.share.classes.jdk.dynalink.linker.LinkerServices;

/*
 * This file is available under and governed by the GNU General Public
 * License version 2 only, as published by the Free Software Foundation.
 * However, the following notice accompanied the original version of this
 * file, and Oracle licenses the original version of this file under the BSD
 * license:
 */

/**
 * A {@link GuardingDynamicLinker} that delegates sequentially to a list of
 * other guarding dynamic linkers in its
 * {@link #getGuardedInvocation(LinkRequest, LinkerServices)}.
 */
public class CompositeGuardingDynamicLinker implements GuardingDynamicLinker {

    private final GuardingDynamicLinker[] linkers;

    /**
     * Creates a new composite linker.
     *
     * @param linkers a list of component linkers.
     * @throws NullPointerException if {@code linkers} or any of its elements
     * are null.
     */
    public CompositeGuardingDynamicLinker(final Iterable<? extends GuardingDynamicLinker> linkers) {
        final List<GuardingDynamicLinker> l = new LinkedList<>();
        for(final GuardingDynamicLinker linker: linkers) {
            l.add(Objects.requireNonNull(linker));
        }
        this.linkers = l.toArray(new GuardingDynamicLinker[0]);
    }

    /**
     * Delegates the call to its component linkers. The first non-null value
     * returned from a component linker is returned. If no component linker
     * returns a non-null invocation, null is returned.
     * @param linkRequest the object describing the request for linking a
     * particular invocation
     * @param linkerServices linker services
     * @return the first non-null return value from a component linker, or null
     * if none of the components returned a non-null.
     */
    @Override
    public GuardedInvocation getGuardedInvocation(final LinkRequest linkRequest, final LinkerServices linkerServices)
            throws Exception {
        for(final GuardingDynamicLinker linker: linkers) {
            final GuardedInvocation invocation = linker.getGuardedInvocation(linkRequest, linkerServices);
            if(invocation != null) {
                return invocation;
            }
        }
        return null;
    }
}
