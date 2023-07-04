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
package jdk.vm.ci.hotspot;

import java.util.Objects;

import jdk.vm.ci.meta.Constant;
import jdk.vm.ci.meta.VMConstant;

final class HotSpotMetaspaceConstantImpl implements HotSpotMetaspaceConstant, VMConstant {

    static HotSpotMetaspaceConstantImpl forMetaspaceObject(MetaspaceObject metaspaceObject, boolean compressed) {
        return new HotSpotMetaspaceConstantImpl(metaspaceObject, compressed);
    }

    static MetaspaceObject getMetaspaceObject(Constant constant) {
        return ((HotSpotMetaspaceConstantImpl) constant).metaspaceObject;
    }

    private final MetaspaceObject metaspaceObject;
    private final boolean compressed;

    private HotSpotMetaspaceConstantImpl(MetaspaceObject metaspaceObject, boolean compressed) {
        this.metaspaceObject = metaspaceObject;
        this.compressed = compressed;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(metaspaceObject) ^ (compressed ? 1 : 2);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HotSpotMetaspaceConstantImpl other)) {
            return false;
        }

        return Objects.equals(this.metaspaceObject, other.metaspaceObject) && this.compressed == other.compressed;
    }

    @Override
    public String toValueString() {
        return String.format("meta{%s%s}", metaspaceObject, compressed ? ";compressed" : "");
    }

    @Override
    public String toString() {
        return toValueString();
    }

    @Override
    public boolean isDefaultForKind() {
        return false;
    }

    @Override
    public boolean isCompressed() {
        return compressed;
    }

    @Override
    public Constant compress() {
        assert !isCompressed();
        HotSpotMetaspaceConstantImpl res = HotSpotMetaspaceConstantImpl.forMetaspaceObject(metaspaceObject, true);
        assert res.isCompressed();
        return res;
    }

    @Override
    public Constant uncompress() {
        assert isCompressed();
        HotSpotMetaspaceConstantImpl res = HotSpotMetaspaceConstantImpl.forMetaspaceObject(metaspaceObject, false);
        assert !res.isCompressed();
        return res;
    }

    @Override
    public HotSpotResolvedObjectType asResolvedJavaType() {
        if (metaspaceObject instanceof HotSpotResolvedObjectType) {
            return (HotSpotResolvedObjectType) metaspaceObject;
        }
        return null;
    }

    @Override
    public HotSpotResolvedJavaMethod asResolvedJavaMethod() {
        if (metaspaceObject instanceof HotSpotResolvedJavaMethod) {
            return (HotSpotResolvedJavaMethod) metaspaceObject;
        }
        return null;
    }
}
