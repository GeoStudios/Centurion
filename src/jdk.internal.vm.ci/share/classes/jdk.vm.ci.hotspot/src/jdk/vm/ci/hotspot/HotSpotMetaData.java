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
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.code.TargetDescription;















public class HotSpotMetaData {
    private final byte[] pcDescBytes;
    private final byte[] scopesDescBytes;
    private final byte[] relocBytes;
    private final byte[] exceptionBytes;
    private byte[] implicitExceptionBytes;
    private final byte[] oopMaps;
    private final Object[] metadata;

    public HotSpotMetaData(TargetDescription target, HotSpotCompiledCode compiledMethod) {
        // Assign the fields default values...
        pcDescBytes = new byte[0];
        scopesDescBytes = new byte[0];
        relocBytes = new byte[0];
        exceptionBytes = new byte[0];
        oopMaps = new byte[0];
        metadata = new String[0];
        // ...some of them will be overwritten by the VM:
        runtime().getCompilerToVM().getMetadata(target, compiledMethod, this);
    }

    public byte[] pcDescBytes() {
        return pcDescBytes;
    }

    public byte[] scopesDescBytes() {
        return scopesDescBytes;
    }

    public byte[] relocBytes() {
        return relocBytes;
    }

    public byte[] exceptionBytes() {
        return exceptionBytes;
    }

    public byte[] implicitExceptionBytes() {
        return implicitExceptionBytes;
    }

    public byte[] oopMaps() {
        return oopMaps;
    }

    public Object[] metadataEntries() {
        return metadata;
    }
}
