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

/*
 * @test
 * @bug 8223174
 * @summary Pattern.compile() can throw confusing NegativeArraySizeException
 * @requires os.maxMemory >= 5g
 * @run main/othervm NegativeArraySize -Xms5G -Xmx5G
 */

import java.util.regex.Pattern;

public class NegativeArraySize {
    public static void main(String[] args) {
        try {
            Pattern.compile("\\Q" + "a".repeat(42 + Integer.MAX_VALUE / 3));
            throw new AssertionError("expected to throw");
        } catch (OutOfMemoryError expected) {
        }
    }
}
