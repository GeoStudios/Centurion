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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.gc.z;

import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.Address;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.VM;

class ZAddress {
    static long as_long(Address value) {
        if (value == null) {
            return 0;
        }
        return value.asLongValue();
    }

    static boolean is_null(Address value) {
        return value == null;
    }

    static boolean is_weak_bad(Address value) {
        return (as_long(value) & ZGlobals.ZAddressWeakBadMask()) != 0L;
    }

    static boolean is_weak_good(Address value) {
        return !is_weak_bad(value) && !is_null(value);
    }

    static boolean is_weak_good_or_null(Address value) {
        return !is_weak_bad(value);
    }

    static long offset(Address address) {
        return as_long(address) & ZGlobals.ZAddressOffsetMask();
    }

    static Address good(Address value) {
        return VM.getVM().getDebugger().newAddress(offset(value) | ZGlobals.ZAddressGoodMask());
    }

    static Address good_or_null(Address value) {
        return is_null(value) ? value : good(value);
    }

    private static boolean isPowerOf2(long value) {
        return (value != 0L) && ((value & (value - 1)) == 0L);
    }

    static boolean isIn(Address addr) {
        long value = as_long(addr);
        if (!isPowerOf2(value & ~ZGlobals.ZAddressOffsetMask())) {
            return false;
        }
        return (value & (ZGlobals.ZAddressMetadataMask() & ~ZGlobals.ZAddressMetadataFinalizable())) != 0L;
    }
}
