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

import java.awt.color.ICC_Profile;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @test
 * @bug 8261107
 * @summary Short and broken streams should be reported as unsupported
 */
public final class GetInstanceBrokenStream {

    public static void main(String[] args) throws IOException {
        // Empty header
        testHeader(new byte[]{});
        // Short header
        testHeader(new byte[]{-12, 3, 45});
        // Broken header
        testHeader(new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
                15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                31, 32, 33, 34, 35, 0x61, 0x63, 0x73, 0x70});
    }

    private static void testHeader(byte[] data) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        try {
            ICC_Profile.getInstance(bais);
        } catch (IllegalArgumentException e) {
            // expected
        }
    }
}
