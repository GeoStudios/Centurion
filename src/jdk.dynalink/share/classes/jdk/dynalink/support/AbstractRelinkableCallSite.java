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

/*
 * This file is available under and governed by the GNU General Public
 * License version 2 only, as published by the Free Software Foundation.
 * However, the following notice accompanied the original version of this
 * file, and Oracle licenses the original version of this file under the BSD
 * license:
 */
package jdk.dynalink.support;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MutableCallSite;
import jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.RelinkableCallSite;
import jdk.dynalink.linker.GuardedInvocation;

/**
 * A basic implementation of the {@link RelinkableCallSite} as a
 * {@link MutableCallSite}. It carries a {@link CallSiteDescriptor} passed in
 * the constructor and provides the correct implementation of the
 * {@link #initialize(MethodHandle)} method. Subclasses must provide
 * {@link #relink(GuardedInvocation, MethodHandle)} and
 * {@link #resetAndRelink(GuardedInvocation, MethodHandle)}
 * methods.
 */
public abstract class AbstractRelinkableCallSite extends MutableCallSite implements RelinkableCallSite {
    private final CallSiteDescriptor descriptor;

    /**
     * Creates a new abstract relinkable call site.
     * @param descriptor the descriptor for this call site that will be returned
     * from {@link #getDescriptor()}. The call site's {@link CallSite#type()}
     * will be equal to descriptor's {@link CallSiteDescriptor#getMethodType()}.
     * @throws NullPointerException if {@code descriptor} is null.
     */
    protected AbstractRelinkableCallSite(final CallSiteDescriptor descriptor) {
        super(descriptor.getMethodType());
        this.descriptor = descriptor;
    }

    @Override
    public CallSiteDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public void initialize(final MethodHandle relinkAndInvoke) {
        setTarget(relinkAndInvoke);
    }
}
