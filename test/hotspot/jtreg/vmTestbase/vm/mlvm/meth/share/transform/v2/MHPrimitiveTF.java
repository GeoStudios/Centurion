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
import vm.mlvm.meth.share.Argument;














public abstract class MHPrimitiveTF extends MHTF {

    @Override
    public MHCall computeInboundCall() throws IllegalArgumentException, NoSuchMethodException, IllegalAccessException {
        check();
        MHCall result = new MHCall(
                computeRetVal(),
                this,
                computeInboundMH(),
                computeInboundArgs());
        result.check();
        return result;
    }

    protected abstract void check() throws IllegalArgumentException;
    protected abstract Argument computeRetVal();
    protected abstract Argument[] computeInboundArgs();
    protected abstract MethodHandle computeInboundMH() throws NoSuchMethodException, IllegalAccessException;

    @Override
    public MHTF[] getSubTFs() {
        return new MHTF[0];
    }
}
