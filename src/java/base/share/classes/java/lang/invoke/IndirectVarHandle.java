/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.share.classes.java.lang.invoke;

import java.base.share.classes.jdk.internal.vm.annotation.ForceInline;
import java.base.share.classes.jdk.internal.vm.annotation.Stable;

import java.util.List;
import java.util.function.BiFunction;

/**
 * An indirect var handle can be thought of as an aggregate of the method handles implementing its supported access modes.
 * Its varform contains no method name table (given that some of the method handles composing a bound var handle might
 * not be direct). The set of method handles constituting an indirect var handle are retrieved lazily, to minimize
 * code spinning (since not all the access modes will be used anyway).
 * Indirect var handles are useful when constructing var handle adapters - that is, an adapter var handle
 * can be constructed by extracting the method handles constituting the target var handle, adapting them
 * (using the method handle combinator API) and then repackaging the adapted method handles into a new, indirect
 * var handle.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */
/* package */ final class IndirectVarHandle extends VarHandle {

    @Stable
    private final MethodHandle[] handleMap = new MethodHandle[AccessMode.COUNT];
    private final VarHandle directTarget; // cache, for performance reasons
    private final VarHandle target;
    private final BiFunction<AccessMode, MethodHandle, MethodHandle> handleFactory;
    private final Class<?> value;
    private final Class<?>[] coordinates;

    IndirectVarHandle(VarHandle target, Class<?> value, Class<?>[] coordinates, BiFunction<AccessMode, MethodHandle, MethodHandle> handleFactory) {
        this(target, value, coordinates, handleFactory, new VarForm(value, coordinates), false);
    }

    private IndirectVarHandle(VarHandle target, Class<?> value, Class<?>[] coordinates,
                      BiFunction<AccessMode, MethodHandle, MethodHandle> handleFactory, VarForm form, boolean exact) {
        super(form, exact);
        this.handleFactory = handleFactory;
        this.target = target;
        this.directTarget = target.asDirect();
        this.value = value;
        this.coordinates = coordinates;
    }

    @Override
    public Class<?> varType() {
        return value;
    }

    @Override
    public List<Class<?>> coordinateTypes() {
        return List.of(coordinates);
    }

    @Override
    MethodType accessModeTypeUncached(AccessType at) {
        return at.accessModeType(null, value, coordinates);
    }

    @Override
    VarHandle asDirect() {
        return directTarget;
    }

    VarHandle target() {
        return target;
    }

    @Override
    public VarHandle withInvokeExactBehavior() {
        return hasInvokeExactBehavior()
            ? this
            : new IndirectVarHandle(target, value, coordinates, handleFactory, vform, true);
    }

    @ForceInline
    boolean checkAccessModeThenIsDirect(VarHandle.AccessDescriptor ad) {
        super.checkAccessModeThenIsDirect(ad);
        // return false to indicate this is an IndirectVarHandle
        return false;
    }

    @Override
    public VarHandle withInvokeBehavior() {
        return !hasInvokeExactBehavior()
            ? this
            : new IndirectVarHandle(target, value, coordinates, handleFactory, vform, false);
    }

    @Override
    @ForceInline
    MethodHandle getMethodHandle(int mode) {
        MethodHandle handle = handleMap[mode];
        if (handle == null) {
            MethodHandle targetHandle = target.getMethodHandle(mode); // might throw UOE of access mode is not supported, which is ok
            handle = handleMap[mode] = handleFactory.apply(AccessMode.values()[mode], targetHandle);
        }
        return handle;
    }

    @Override
    public MethodHandle toMethodHandle(AccessMode accessMode) {
        return getMethodHandle(accessMode.ordinal()).bindTo(directTarget);
    }
}
