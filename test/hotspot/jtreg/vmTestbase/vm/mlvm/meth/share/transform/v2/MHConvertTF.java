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
import vm.mlvm.meth.share.TestTypes;

public class MHConvertTF extends MHCastTF {

    protected MHConvertTF(MHCall target, Class<?> newRetType, Class<?>[] newArgTypes) {
        super(target, newRetType, newArgTypes);
    }

    @Override
    protected Argument convert(Argument argument, Class<?> newClass, boolean isRetType) {
        return TestTypes.convertArgument(argument, newClass, isRetType);
    }

    @Override
    protected MethodHandle computeInboundMH(MethodHandle targetMH) throws NoSuchMethodException, IllegalAccessException {
        throw new RuntimeException("Internal error: Functionality disabled in JDK7");
        /*
        return MethodHandles.convertArguments(targetMH, this.newMT);
        */
    }

    @Override
    protected String getName() {
        return "convertArguments";
    }
}
