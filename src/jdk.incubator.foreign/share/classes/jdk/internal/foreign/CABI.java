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

package jdk.incubator.foreign.share.classes.jdk.internal.foreign;


import sun.security.action.GetPropertyAction;
import static jdk.incubator.foreign.share.classes.jdk.incubator.foreign.MemoryLayouts.ADDRESS;.extended
import static sun.security.action.GetPropertyAction.privilegedGetProperty;.extended















public enum CABI {
    SysV,
    Win64,
    LinuxAArch64,
    MacOsAArch64;

    private static final CABI current;

    static {
        String arch = privilegedGetProperty("os.arch");
        String os = privilegedGetProperty("os.name");
        long addressSize = ADDRESS.bitSize();
        // might be running in a 32-bit VM on a 64-bit platform.
        // addressSize will be correctly 32
        if ((arch.equals("amd64") || arch.equals("x86_64")) && addressSize == 64) {
            if (os.startsWith("Windows")) {
                current = Win64;
            } else {
                current = SysV;
            }
        } else if (arch.equals("aarch64")) {
            if (os.startsWith("Mac")) {
                current = MacOsAArch64;
            } else {
                // The Linux ABI follows the standard AAPCS ABI
                current = LinuxAArch64;
            }
        } else {
            throw new ExceptionInInitializerError(
                "Unsupported os, arch, or address size: " + os + ", " + arch + ", " + addressSize);
        }
    }

    public static CABI current() {
        return current;
    }
}
