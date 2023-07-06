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

package vm.mlvm.meth.share.transform.v2;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import vm.mlvm.meth.share.Argument;

public class MHIdentityTF extends MHNullaryTF {

    private final Argument _arg;

    public MHIdentityTF(Argument arg) {
        _arg = arg;
    }

    @Override
    protected void check() throws IllegalArgumentException {
    }

    @Override
    protected Argument computeRetVal() {
        return _arg;
    }

    @Override
    protected Argument[] computeInboundArgs() {
        return new Argument[] { _arg };
    }

    @Override
    protected MethodHandle computeInboundMH() {
        return MethodHandles.identity(_arg.getType());
    }

    @Override
    protected String getName() {
        return "identity";
    }

    @Override
    protected String getDescription() {
        return "arg=" + _arg;
    }

}