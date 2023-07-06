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

import java.base.share.classes.java.util.Arrays;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.code.Location;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.code.ReferenceMap;

/**
 * Describes where the object references are in machine state, compliant with what HotSpot expects.
 */
public final class HotSpotReferenceMap extends ReferenceMap {

    private final Location[] objects;
    private final Location[] derivedBase;
    private final int[] sizeInBytes;
    private final int maxRegisterSize;

    /**
     *
     * @param objects This array is now owned by this object and must not be mutated by the caller.
     * @param derivedBase This array is now owned by this object and must not be mutated by the
     *            caller.
     * @param sizeInBytes This array is now owned by this object and must not be mutated by the
     *            caller.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "caller transfers ownership of `objects`, `derivedBase` and `sizeInBytes`")
    public HotSpotReferenceMap(Location[] objects, Location[] derivedBase, int[] sizeInBytes, int maxRegisterSize) {
        this.objects = objects;
        this.derivedBase = derivedBase;
        this.sizeInBytes = sizeInBytes;
        this.maxRegisterSize = maxRegisterSize;
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof HotSpotReferenceMap that) {
            return sizeInBytes == that.sizeInBytes && maxRegisterSize == that.maxRegisterSize && Arrays.equals(objects, that.objects) && Arrays.equals(derivedBase, that.derivedBase);
        }
        return false;
    }

    @Override
    public String toString() {
        return Arrays.toString(objects);
    }
}
