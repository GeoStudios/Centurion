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

import java.lang.reflect.Array;

import vm.mlvm.meth.share.Argument;

public class MHArrayEnvelopeTFPair extends MHEnvelopeArgTFPair {

    public MHArrayEnvelopeTFPair(MHCall outboundTarget, int argNum, int arrayIdx, int arraySize) {
        super(outboundTarget,
              "ArrayGetSet_" + outboundTarget.hashCode(),
              argNum,
              getLocatorArg(arrayIdx),
              getEnvelopeArg(outboundTarget.getArgs()[argNum], arrayIdx, arraySize));

        if ( arrayIdx > arraySize )
            throw new IllegalArgumentException("Array index [" + arrayIdx + "] should be less than array size [" + arraySize + "]");
    }

    private static Argument getLocatorArg(int arrayIdx) {
        return new Argument(int.class, arrayIdx);
    }

    private static Argument getEnvelopeArg(Argument componentArg, int arrayIdx, int arraySize) {
        Object array = Array.newInstance(componentArg.getType(), arraySize);
        Array.set(array, arrayIdx, componentArg.getValue());
        return new Argument(array.getClass(), array);
    }

    @Override
    protected MHTF computeGetTF(Argument envelopeArg, Argument envelopeLocatorArg) {
        return new MHArrayGetElemTF(envelopeArg, envelopeLocatorArg);
    }

    @Override
    protected MHTF computeSetTF(Argument envelopeArg, Argument envelopeLocatorArg, Argument componentArg) {
        return new MHArraySetElemTF(envelopeArg, envelopeLocatorArg, componentArg);
    }

}
