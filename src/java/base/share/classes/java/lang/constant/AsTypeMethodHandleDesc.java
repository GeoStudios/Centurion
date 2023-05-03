/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.base.share.classes.java.lang.constant;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import static java.base.share.classes.java.lang.constant.ConstantDescs.BSM_INVOKE;
import static java.base.share.classes.java.lang.constant.ConstantDescs.CD_MethodHandle;
import static java.util.Objects.requireNonNull;

/**
 * A <a href="package-summary.html#nominal">nominal descriptor</a> for a
 * {@link MethodHandle} constant that performs a {@link MethodHandle#asType(MethodType)}
 * adaptation on another {@link MethodHandle}.
 * 
 * @since Java 2
 * @author Logan Abernathy
 * @edited 24/4/2023
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
