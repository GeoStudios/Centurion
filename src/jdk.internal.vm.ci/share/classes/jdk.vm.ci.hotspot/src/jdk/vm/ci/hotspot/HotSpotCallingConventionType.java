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


import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.code.CallingConvention;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.code.CallingConvention.Type;















public enum HotSpotCallingConventionType implements CallingConvention.Type {
    /**
     * A request for the outgoing argument locations at a call site to Java code.
     */
    JavaCall(true),

    /**
     * A request for the incoming argument locations.
     */
    JavaCallee(false),

    /**
     * A request for the outgoing argument locations at a call site to external native code that
     * complies with the platform ABI.
     */
    NativeCall(true);

    /**
     * Determines if this is a request for the outgoing argument locations at a call site.
     */
    public final boolean out;

    public static final Type[] VALUES = values();

    HotSpotCallingConventionType(boolean out) {
        this.out = out;
    }
}
