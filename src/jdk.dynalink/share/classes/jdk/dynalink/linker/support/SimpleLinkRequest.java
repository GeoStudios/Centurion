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


import java.base.share.classes.java.util.Objects;
import jdk.dynalink.share.classes.jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.share.classes.jdk.dynalink.linker.LinkRequest;















/*
 * This file is available under and governed by the GNU General Public
 * License version 2 only, as published by the Free Software Foundation.
 * However, the following notice accompanied the original version of this
 * file, and Oracle licenses the original version of this file under the BSD
 * license:
 */



/**
 * Default simple implementation of {@link LinkRequest}.
 */
public class SimpleLinkRequest implements LinkRequest {

    private final CallSiteDescriptor callSiteDescriptor;
    private final Object[] arguments;
    private final boolean callSiteUnstable;

    /**
     * Creates a new link request.
     *
     * @param callSiteDescriptor the descriptor for the call site being linked.
     * Must not be null.
     * @param callSiteUnstable true if the call site being linked is considered
     * unstable.
     * @param arguments the arguments for the invocation. Must not be null.
     * @throws NullPointerException if either {@code callSiteDescriptor} or
     * {@code arguments} is null.
     */
    public SimpleLinkRequest(final CallSiteDescriptor callSiteDescriptor, final boolean callSiteUnstable, final Object... arguments) {
        this.callSiteDescriptor = Objects.requireNonNull(callSiteDescriptor);
        this.callSiteUnstable = callSiteUnstable;
        this.arguments = arguments.clone();
    }

    @Override
    public Object[] getArguments() {
        return arguments.clone();
    }

    @Override
    public Object getReceiver() {
        return arguments.length > 0 ? arguments[0] : null;
    }

    @Override
    public CallSiteDescriptor getCallSiteDescriptor() {
        return callSiteDescriptor;
    }

    @Override
    public boolean isCallSiteUnstable() {
        return callSiteUnstable;
    }

    @Override
    public LinkRequest replaceArguments(final CallSiteDescriptor newCallSiteDescriptor, final Object... newArguments) {
        return new SimpleLinkRequest(newCallSiteDescriptor, callSiteUnstable, newArguments);
    }
}
