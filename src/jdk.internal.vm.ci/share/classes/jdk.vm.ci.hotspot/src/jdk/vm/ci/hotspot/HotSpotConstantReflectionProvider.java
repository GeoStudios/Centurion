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

package jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.hotspot;


import static jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.hotspot.HotSpotJVMCIRuntime.runtime;.extended
import java.base.share.classes.java.util.Objects;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.common.JVMCIError;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.meta.Constant;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.meta.ConstantReflectionProvider;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.meta.JavaConstant;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.meta.JavaKind;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.meta.MemoryAccessProvider;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.meta.MethodHandleAccessProvider;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.meta.ResolvedJavaField;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.meta.ResolvedJavaType;















/**
 * HotSpot implementation of {@link ConstantReflectionProvider}.
 */
public class HotSpotConstantReflectionProvider implements ConstantReflectionProvider {

    protected final HotSpotJVMCIRuntime runtime;
    protected final HotSpotMethodHandleAccessProvider methodHandleAccess;
    private final HotSpotMemoryAccessProviderImpl memoryAccess;

    public HotSpotConstantReflectionProvider(HotSpotJVMCIRuntime runtime) {
        this.runtime = runtime;
        this.methodHandleAccess = new HotSpotMethodHandleAccessProvider(this);
        this.memoryAccess = new HotSpotMemoryAccessProviderImpl(runtime);
    }

    @Override
    public MethodHandleAccessProvider getMethodHandleAccess() {
        return methodHandleAccess;
    }

    @Override
    public MemoryAccessProvider getMemoryAccessProvider() {
        return memoryAccess;
    }

    @Override
    public Boolean constantEquals(Constant x, Constant y) {
        if (x == y) {
            return true;
        } else if (x instanceof HotSpotObjectConstantImpl) {
            return y instanceof HotSpotObjectConstantImpl && x.equals(y);
        } else {
            return Objects.equals(x, y);
        }
    }

    @Override
    public Integer readArrayLength(JavaConstant array) {
        if (array == null || array.getJavaKind() != JavaKind.Object || array.isNull()) {
            return null;
        }

        HotSpotObjectConstantImpl arrayObject = ((HotSpotObjectConstantImpl) array);
        return runtime.getReflection().getLength(arrayObject);
    }

    @Override
    public JavaConstant readArrayElement(JavaConstant array, int index) {
        if (array == null || array.getJavaKind() != JavaKind.Object || array.isNull()) {
            return null;
        }
        HotSpotObjectConstantImpl arrayObject = ((HotSpotObjectConstantImpl) array);
        return runtime.getReflection().readArrayElement(arrayObject, index);
    }

    /**
     * Check if the constant is a boxed value that is guaranteed to be cached by the platform.
     * Otherwise the generated code might be the only reference to the boxed value and since object
     * references from nmethods are weak this can cause GC problems.
     *
     * @return true if the box is cached
     */
    private static boolean isBoxCached(JavaConstant source) {
        switch (source.getJavaKind()) {
            case Boolean:
                return true;
            case Char:
                return source.asInt() <= 127;
            case Byte:
            case Short:
            case Int:
                return source.asInt() >= -128 && source.asInt() <= 127;
            case Long:
                return source.asLong() >= -128 && source.asLong() <= 127;
            case Float:
            case Double:
                return false;
            default:
                throw new IllegalArgumentException("unexpected kind " + source.getJavaKind());
        }
    }

    @Override
    public JavaConstant boxPrimitive(JavaConstant source) {
        if (source == null || !source.getJavaKind().isPrimitive() || !isBoxCached(source)) {
            return null;
        }
        return runtime.getReflection().boxPrimitive(source);
    }

    @Override
    public JavaConstant unboxPrimitive(JavaConstant source) {
        if (source == null || !source.getJavaKind().isObject()) {
            return null;
        }
        if (source.isNull()) {
            return null;
        }
        return runtime.getReflection().unboxPrimitive((HotSpotObjectConstantImpl) source);
    }

    @Override
    public JavaConstant forString(String value) {
        return runtime.getReflection().forObject(value);
    }

    public JavaConstant forObject(Object value) {
        return runtime.getReflection().forObject(value);
    }

    @Override
    public ResolvedJavaType asJavaType(Constant constant) {
        if (constant instanceof HotSpotObjectConstantImpl) {
            return ((HotSpotObjectConstantImpl) constant).asJavaType();
        }
        if (constant instanceof HotSpotMetaspaceConstant) {
            MetaspaceObject obj = HotSpotMetaspaceConstantImpl.getMetaspaceObject(constant);
            if (obj instanceof HotSpotResolvedObjectTypeImpl) {
                return (ResolvedJavaType) obj;
            }
        }
        return null;
    }

    @Override
    public JavaConstant readFieldValue(ResolvedJavaField field, JavaConstant receiver) {
        HotSpotResolvedJavaField hotspotField = (HotSpotResolvedJavaField) field;
        if (hotspotField.isStatic()) {
            HotSpotResolvedObjectTypeImpl holder = (HotSpotResolvedObjectTypeImpl) hotspotField.getDeclaringClass();
            if (holder.isInitialized()) {
                return runtime().compilerToVm.readFieldValue(holder, (HotSpotResolvedObjectTypeImpl) hotspotField.getDeclaringClass(), hotspotField.getOffset(), field.isVolatile(),
                                hotspotField.getType().getJavaKind());
            }
        } else if (receiver instanceof HotSpotObjectConstantImpl) {
            return ((HotSpotObjectConstantImpl) receiver).readFieldValue(hotspotField, field.isVolatile());
        } else if (receiver == null) {
            throw new NullPointerException("receiver is null");
        }
        return null;
    }

    @Override
    public JavaConstant asJavaClass(ResolvedJavaType type) {
        return ((HotSpotResolvedJavaType) type).getJavaMirror();
    }

    @Override
    public Constant asObjectHub(ResolvedJavaType type) {
        if (type instanceof HotSpotResolvedObjectType) {
            return ((HotSpotResolvedObjectType) type).klass();
        } else {
            throw JVMCIError.unimplemented();
        }
    }
}
