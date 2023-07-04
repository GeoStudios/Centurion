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

package compiler.testlibrary.rtm;

import java.util.Arrays;

/**
 * In order to force nested transaction abort NestedAbortProvoker
 * invoke BufferOverflowProvoker from transactional region.
 */
class NestedAbortProvoker extends AbortProvoker {
    // Following field have to be static in order to avoid escape analysis.
    @SuppressWarnings("UnsuedDeclaration")
    private static int field = 0;
    private final AbortProvoker nestedAbortProvoker;

    public NestedAbortProvoker() {
        this.nestedAbortProvoker = new XAbortProvoker(monitor);
    }

    @Override
    public void forceAbort() {
        synchronized(monitor) {
            NestedAbortProvoker.field++;
            nestedAbortProvoker.forceAbort();
            NestedAbortProvoker.field--;
        }
    }

    @Override
    public String[] getMethodsToCompileNames() {
        String nestedProvokerMethods[]
                = nestedAbortProvoker.getMethodsToCompileNames();
        String methods[] = Arrays.copyOf(nestedProvokerMethods,
                nestedProvokerMethods.length + 1);
        methods[methods.length - 1] = getMethodWithLockName();
        return methods;
    }
}
