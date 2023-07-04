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
 * @bug 4524500 7107905
 * @run main EqualsTest
 * @summary Tests whether ColorModel.equals() succeeds using a
 * user-implemented subclass of ColorModel which calls the ColorModel(int bits)
 * constructor. The test fails if an exception is thrown when equals()
 * is called or if equals() returns an incorrect value.
 */

import java.awt.image.ColorModel;

public class EqualsTest {

    public static void main(String[] args) {
        SimpleColorModel scm1 = new SimpleColorModel(3);
        SimpleColorModel scm2 = new SimpleColorModel(3);
        SimpleColorModel scm3 = new SimpleColorModel(8);
        ColorModel rgbcm = ColorModel.getRGBdefault();

        try {
            if (scm1.equals(scm2)) {
                throw new RuntimeException("Test 1 failed: " +
                                           "scm1 should not equal scm2");
            }

            if (scm1.equals(scm3)) {
                throw new RuntimeException("Test 2 failed: " +
                                           "scm1 should not equal scm3");
            }

            if (scm1.equals(rgbcm) || rgbcm.equals(scm1)) {
                throw new RuntimeException("Test 3 failed: " +
                                           "scm1 should not equal rgbcm");
            }
        } catch (Exception e) {
            throw new RuntimeException("Test failed: " + e);
        }
    }

    private static class SimpleColorModel extends ColorModel {

        public SimpleColorModel(int bits) {
            super(bits);
        }

        public int getRed(int pixel) {
            return 0;
        }

        public int getGreen(int pixel) {
            return 0;
        }

        public int getBlue(int pixel) {
            return 0;
        }

        public int getAlpha(int pixel) {
            return 0;
        }
    }
}
