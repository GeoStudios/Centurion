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


import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.meta.JavaConstant;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.meta.JavaKind;















/**
 * Represents a constant that was retrieved from a constant pool. Used to keep track of the constant
 * pool slot for the constant.
 */
public final class HotSpotConstantPoolObject implements JavaConstant {

    public static JavaConstant forObject(HotSpotResolvedObjectType type, int cpi, JavaConstant object) {
        return new HotSpotConstantPoolObject(type, cpi, object);
    }

    private final JavaConstant constant;
    private final HotSpotResolvedObjectType type;
    private final int cpi;

    public HotSpotResolvedObjectType getCpType() {
        return type;
    }

    public int getCpi() {
        return cpi;
    }

    HotSpotConstantPoolObject(HotSpotResolvedObjectType type, int cpi, JavaConstant constant) {
        this.type = type;
        this.cpi = cpi;
        this.constant = constant;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof HotSpotConstantPoolObject other) {
            return type.equals(other.type) && cpi == other.cpi && constant.equals(other.constant);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return constant.hashCode() + cpi + type.hashCode();
    }

    @Override
    public JavaKind getJavaKind() {
        return constant.getJavaKind();
    }

    @Override
    public boolean isNull() {
        return constant.isNull();
    }

    @Override
    public boolean isDefaultForKind() {
        return constant.isDefaultForKind();
    }

    @Override
    public Object asBoxedPrimitive() {
        return constant.asBoxedPrimitive();
    }

    @Override
    public int asInt() {
        return constant.asInt();
    }

    @Override
    public boolean asBoolean() {
        return constant.asBoolean();
    }

    @Override
    public long asLong() {
        return constant.asLong();
    }

    @Override
    public float asFloat() {
        return constant.asFloat();
    }

    @Override
    public double asDouble() {
        return 0;
    }

    @Override
    public String toValueString() {
        return getCpType().getName() + getCpi();
    }

    @Override
    public String toString() {
        return super.toString() + "@" + toValueString();
    }

}
