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

package vm.mlvm.indy.share;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.CallSite;
import vm.mlvm.share.DekkerTest;

/**
 * The class implements Actor for {@link vm.mlvm.share.DekkerTest}.
 * <p>
 * This Actor switches targets for CallSite objects supplied in constructor.
 * Targets are method handles, generated with MethodHandle.constant(), which return true or false.
 * CallSite.setTarget() is used for setting appropriate target.
 * CallSite.dynamicInvoker().invokeExact() is used to detect which target is currently set.
 * No synchronization is used between setter and getter (see {@link vm.mlvm.share.DekkerTest} for details).
 *
 * @see vm.mlvm.share.DekkerTest
 */
public class CallSiteDekkerActor implements DekkerTest.Actor {

    public static final MethodHandle MH_FALSE = MethodHandles.constant(Boolean.class, false);
    public static final MethodHandle MH_TRUE = MethodHandles.constant(Boolean.class, true);

    private final CallSite a;
    private final CallSite b;

    public CallSiteDekkerActor(CallSite csa, CallSite csb) {
        a = csa;
        b = csb;
    }

    @Override
    public void reset() {
        a.setTarget(MH_FALSE);
        b.setTarget(MH_FALSE);
    }

    @Override
    public boolean actorA() throws Throwable {
        a.setTarget(MH_TRUE);
        return (Boolean) b.dynamicInvoker().invokeExact();
    }

    @Override
    public boolean actorB() throws Throwable {
        b.setTarget(MH_TRUE);
        return (Boolean) a.dynamicInvoker().invokeExact();
    }
}
