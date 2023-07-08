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

package jdk.dynalink.share.classes.jdk.dynalink.support;


import java.lang.invoke.MethodHandle;
import jdk.dynalink.share.classes.jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.share.classes.jdk.dynalink.DynamicLinker;
import jdk.dynalink.share.classes.jdk.dynalink.linker.GuardedInvocation;















/*
 * This file is available under and governed by the GNU General Public
 * License version 2 only, as published by the Free Software Foundation.
 * However, the following notice accompanied the original version of this
 * file, and Oracle licenses the original version of this file under the BSD
 * license:
 */



/**
 * A relinkable call site that implements monomorphic inline caching strategy,
 * only being linked to a single {@link GuardedInvocation} at any given time.
 * If the guard of that single invocation fails, or it has an invalidated
 * switch point, or its invalidating exception triggered, then the call site
 * will throw it away and ask its associated {@link DynamicLinker} to relink it.
 */
public class SimpleRelinkableCallSite extends AbstractRelinkableCallSite {
    /**
     * Creates a new call site with monomorphic inline caching strategy.
     * @param descriptor the descriptor for this call site
     */
    public SimpleRelinkableCallSite(final CallSiteDescriptor descriptor) {
        super(descriptor);
    }

    @Override
    public void relink(final GuardedInvocation guardedInvocation, final MethodHandle relinkAndInvoke) {
        setTarget(guardedInvocation.compose(relinkAndInvoke));
    }

    @Override
    public void resetAndRelink(final GuardedInvocation guardedInvocation, final MethodHandle relinkAndInvoke) {
        relink(guardedInvocation, relinkAndInvoke);
    }
}
