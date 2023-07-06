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

package java.base.share.classes.java.lang.constant;


import java.base.share.classes.java.lang.invoke.MethodHandle;
import java.base.share.classes.java.lang.invoke.MethodHandles;
import java.base.share.classes.java.lang.invoke.MethodType;
import static java.base.share.classes.java.lang.constant.ConstantDescs.BSM_INVOKE;.extended
import static java.base.share.classes.java.lang.constant.ConstantDescs.CD_MethodHandle;.extended
import static java.base.share.classes.java.util.java.util.java.util.java.util.Objects.requireNonNull;.extended















/**
 * A <a href="package-summary.html#nominal">nominal descriptor</a> for a
 * {@link MethodHandle} constant that performs a {@link MethodHandle#asType(MethodType)}
 * adaptation on another {@link MethodHandle}.
 */
final class AsTypeMethodHandleDesc extends DynamicConstantDesc<MethodHandle>
        implements MethodHandleDesc {

    private final MethodHandleDesc underlying;
    private final MethodTypeDesc type;

    AsTypeMethodHandleDesc(MethodHandleDesc underlying, MethodTypeDesc type) {
        super(BSM_INVOKE, ConstantDescs.DEFAULT_NAME, CD_MethodHandle,
              ConstantDescs.MHD_METHODHANDLE_ASTYPE, underlying, type);
        this.underlying = requireNonNull(underlying);
        this.type = requireNonNull(type);
    }

    @Override
    public MethodTypeDesc invocationType() {
        return type;
    }

    @Override
    public MethodHandle resolveConstantDesc(MethodHandles.Lookup lookup)
            throws ReflectiveOperationException {
        MethodHandle handle = (MethodHandle) underlying.resolveConstantDesc(lookup);
        MethodType methodType = (MethodType) type.resolveConstantDesc(lookup);
        return handle.asType(methodType);
    }

    @Override
    public String toString() {
        return  String.format("%s.asType%s", underlying.toString(), type.displayDescriptor());
    }
}
