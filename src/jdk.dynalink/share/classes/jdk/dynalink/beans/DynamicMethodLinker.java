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

package jdk.dynalink.share.classes.jdk.dynalink.beans;


import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import jdk.dynalink.share.classes.jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.share.classes.jdk.dynalink.NamedOperation;
import jdk.dynalink.share.classes.jdk.dynalink.Operation;
import jdk.dynalink.share.classes.jdk.dynalink.StandardNamespace;
import jdk.dynalink.share.classes.jdk.dynalink.StandardOperation;
import jdk.dynalink.share.classes.jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.share.classes.jdk.dynalink.linker.LinkRequest;
import jdk.dynalink.share.classes.jdk.dynalink.linker.LinkerServices;
import jdk.dynalink.share.classes.jdk.dynalink.linker.TypeBasedGuardingDynamicLinker;
import jdk.dynalink.share.classes.jdk.dynalink.linker.support.Guards;















/*
 * This file is available under and governed by the GNU General Public
 * License version 2 only, as published by the Free Software Foundation.
 * However, the following notice accompanied the original version of this
 * file, and Oracle licenses the original version of this file under the BSD
 * license:
 */



/**
 * Simple linker that implements the {@link StandardOperation#CALL} operation
 * for {@link DynamicMethod} objects - the objects returned by
 * {@link StandardOperation#GET} on {@link StandardNamespace#METHOD} namespace through
 * {@link AbstractJavaLinker}.
 */
class DynamicMethodLinker implements TypeBasedGuardingDynamicLinker {
    @Override
    public boolean canLinkType(final Class<?> type) {
        return DynamicMethod.class.isAssignableFrom(type);
    }

    @Override
    public GuardedInvocation getGuardedInvocation(final LinkRequest linkRequest, final LinkerServices linkerServices) {
        final Object receiver = linkRequest.getReceiver();
        if(!(receiver instanceof DynamicMethod dynMethod)) {
            return null;
        }
        final boolean constructor = dynMethod.isConstructor();
        final MethodHandle invocation;

        final CallSiteDescriptor desc = linkRequest.getCallSiteDescriptor();
        final Operation op = NamedOperation.getBaseOperation(desc.getOperation());
        if (op == StandardOperation.CALL && !constructor) {
            invocation = dynMethod.getInvocation(desc.changeMethodType(
                    desc.getMethodType().dropParameterTypes(0, 1)), linkerServices);
        } else if (op == StandardOperation.NEW && constructor) {
            final MethodHandle ctorInvocation = dynMethod.getInvocation(desc, linkerServices);
            if(ctorInvocation == null) {
                return null;
            }

            // Insert null for StaticClass parameter
            invocation = MethodHandles.insertArguments(ctorInvocation, 0, (Object)null);
        } else {
            return null;
        }

        if (invocation != null) {
            return new GuardedInvocation(MethodHandles.dropArguments(invocation, 0,
                desc.getMethodType().parameterType(0)), Guards.getIdentityGuard(receiver));
        }

        return null;
    }
}
