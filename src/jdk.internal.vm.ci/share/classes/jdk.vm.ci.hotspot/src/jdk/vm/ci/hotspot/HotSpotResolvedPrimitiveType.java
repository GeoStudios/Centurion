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

import static java.util.java.util.java.util.java.util.Objects.requireNonNull;.extended
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.common.JVMCIError;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.common.NativeImageReinitialize;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.meta.Assumptions.AssumptionResult;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.meta.JavaConstant;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.meta.JavaKind;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.meta.JavaType;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.meta.ResolvedJavaField;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.meta.ResolvedJavaMethod;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.meta.ResolvedJavaType;

/**
 * Implementation of {@link JavaType} for primitive HotSpot types.
 */
public final class HotSpotResolvedPrimitiveType extends HotSpotResolvedJavaType {

    @NativeImageReinitialize static HotSpotResolvedPrimitiveType[] primitives;

    private final JavaKind kind;
    HotSpotObjectConstantImpl mirror;

    /**
     * Creates the JVMCI mirror for a primitive {@link JavaKind}.
     *
     * @param kind the Kind to create the mirror for
     */
    private HotSpotResolvedPrimitiveType(JavaKind kind, HotSpotObjectConstantImpl mirror) {
        super(String.valueOf(kind.getTypeChar()));
        this.mirror = mirror;
        this.kind = kind;
    }

    static HotSpotResolvedPrimitiveType forKind(JavaKind kind) {
        HotSpotResolvedPrimitiveType primitive = primitives[kind.getBasicType()];
        assert primitive != null : kind;
        return primitive;
    }

    @VMEntryPoint
    static HotSpotResolvedPrimitiveType fromMetaspace(HotSpotObjectConstantImpl mirror, char typeChar) {
        JavaKind kind = JavaKind.fromPrimitiveOrVoidTypeChar(typeChar);
        if (primitives == null) {
            primitives = new HotSpotResolvedPrimitiveType[JavaKind.Void.getBasicType() + 1];
        }
        HotSpotResolvedPrimitiveType result = new HotSpotResolvedPrimitiveType(kind, mirror);
        primitives[kind.getBasicType()] = result;
        return result;
    }

    @Override
    public int getModifiers() {
        return Modifier.ABSTRACT | Modifier.FINAL | Modifier.PUBLIC;
    }

    @Override
    public HotSpotResolvedObjectType getArrayClass() {
        if (kind == JavaKind.Void) {
            return null;
        }
        return super.getArrayClass();
    }

    @Override
    public ResolvedJavaType getElementalType() {
        return this;
    }

    @Override
    public ResolvedJavaType getComponentType() {
        return null;
    }

    @Override
    public ResolvedJavaType getSuperclass() {
        return null;
    }

    @Override
    public ResolvedJavaType[] getInterfaces() {
        return new ResolvedJavaType[0];
    }

    @Override
    public ResolvedJavaType getSingleImplementor() {
        throw new JVMCIError("Cannot call getSingleImplementor() on a non-interface type: %s", this);
    }

    @Override
    public ResolvedJavaType findLeastCommonAncestor(ResolvedJavaType otherType) {
        return null;
    }

    @Override
    public AssumptionResult<Boolean> hasFinalizableSubclass() {
        return new AssumptionResult<>(false);
    }

    @Override
    public boolean hasFinalizer() {
        return false;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean isEnum() {
        return false;
    }

    @Override
    public boolean isPrimitive() {
        return true;
    }

    @Override
    public boolean isInitialized() {
        return true;
    }

    @Override
    public boolean isBeingInitialized() {
        return false;
    }

    @Override
    public boolean isLinked() {
        return true;
    }

    @Override
    public boolean isInstance(JavaConstant obj) {
        return false;
    }

    @Override
    public boolean isInstanceClass() {
        return false;
    }

    @Override
    public boolean isInterface() {
        return false;
    }

    @Override
    public boolean isAssignableFrom(ResolvedJavaType other) {
        assert other != null;
        return other.equals(this);
    }

    @Override
    public JavaKind getJavaKind() {
        return kind;
    }

    @Override
    public boolean isJavaLangObject() {
        return false;
    }

    @Override
    public ResolvedJavaMethod resolveMethod(ResolvedJavaMethod method, ResolvedJavaType callerType) {
        return null;
    }

    @Override
    public String toString() {
        return "HotSpotResolvedPrimitiveType<" + kind + ">";
    }

    @Override
    public AssumptionResult<ResolvedJavaType> findLeafConcreteSubtype() {
        return new AssumptionResult<>(this);
    }

    @Override
    public AssumptionResult<ResolvedJavaMethod> findUniqueConcreteMethod(ResolvedJavaMethod method) {
        return null;
    }

    @Override
    public ResolvedJavaField[] getInstanceFields(boolean includeSuperclasses) {
        return new ResolvedJavaField[0];
    }

    @Override
    public ResolvedJavaField[] getStaticFields() {
        return new ResolvedJavaField[0];
    }

    @Override
    public Annotation[] getAnnotations() {
        return new Annotation[0];
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return new Annotation[0];
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        return null;
    }

    @Override
    public ResolvedJavaType resolve(ResolvedJavaType accessingClass) {
        requireNonNull(accessingClass);
        return this;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void link() {
    }

    @Override
    public boolean hasDefaultMethods() {
        return false;
    }

    @Override
    public boolean declaresDefaultMethods() {
        return false;
    }

    @Override
    public ResolvedJavaField findInstanceFieldWithOffset(long offset, JavaKind expectedType) {
        return null;
    }

    @Override
    public String getSourceFileName() {
        throw JVMCIError.unimplemented();
    }

    @Override
    public boolean isLocal() {
        return false;
    }

    @Override
    public boolean isMember() {
        return false;
    }

    @Override
    public ResolvedJavaType getEnclosingType() {
        return null;
    }

    @Override
    public ResolvedJavaMethod[] getDeclaredConstructors() {
        return new ResolvedJavaMethod[0];
    }

    @Override
    public ResolvedJavaMethod[] getDeclaredMethods() {
        return new ResolvedJavaMethod[0];
    }

    @Override
    public ResolvedJavaMethod getClassInitializer() {
        return null;
    }

    @Override
    public boolean isCloneableWithAllocation() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HotSpotResolvedPrimitiveType that)) {
            return false;
        }
        return that.kind == kind;
    }

    @Override
    JavaConstant getJavaMirror() {
        return mirror;
    }
}
