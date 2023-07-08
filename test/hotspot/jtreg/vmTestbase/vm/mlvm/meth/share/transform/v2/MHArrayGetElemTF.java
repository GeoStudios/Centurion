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
import java.lang.reflect.Array;
import vm.mlvm.meth.share.Argument;














public class MHArrayGetElemTF extends MHNullaryTF {

    private final Argument arrayArg;
    private final Argument idxArg;
    private final int idx;

    public MHArrayGetElemTF(Argument array, Argument idxArg) {
        this.arrayArg = array;
        this.idxArg = idxArg;
        this.idx = (Integer) idxArg.getValue();
    }

    @Override
    protected void check() throws IllegalArgumentException {
        if ( ! this.arrayArg.getType().isArray() )
            throw new IllegalArgumentException("Argument " + this.arrayArg + " should be an array!");

        if ( ! this.idxArg.getType().equals(int.class) )
            throw new IllegalArgumentException("Argument " + this.idxArg + " should be of type int!");

        if ( this.idx < 0 || this.idx >= Array.getLength(this.arrayArg.getValue()) )
            throw new IllegalArgumentException("Index " + this.idxArg + " is out of bounds for array " + this.arrayArg);
    }

    @Override
    protected Argument computeRetVal() {
        return new Argument(this.arrayArg.getType().getComponentType(), Array.get(this.arrayArg.getValue(), idx));
    }

    @Override
    protected Argument[] computeInboundArgs() {
        return new Argument[] { arrayArg, idxArg };
    }

    @Override
    protected MethodHandle computeInboundMH() {
        return MethodHandles.arrayElementGetter(this.arrayArg.getType());
    }

    @Override
    protected String getName() {
        return "arrayElementGetter";
    }

    @Override
    protected String getDescription() {
        return "array=" + this.arrayArg + "; idx=" + this.idxArg;
    }
}
