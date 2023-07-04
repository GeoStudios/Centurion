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
public class NonasciiDigit {
    public static void main(String[] args) {
        // error: only ASCII allowed in constants
        int i1 = \uff11;
        int i2 = 1\uff11;
        int i3 = \ud835\udfff;
        // error: floating literals use ascii only
        double d1 = \uff11.0;
        double d2 = 0.\uff11;
        double d3 = 0x0P\uff11;
        double d4 = 0E\uff11;
        double d5 = .\uff11;
        double d6 = \ud835\udfff.0;
    }
}
