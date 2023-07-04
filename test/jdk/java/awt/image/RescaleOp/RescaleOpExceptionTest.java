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
 * @bug 8180501
 * @summary Verify RescaleOp.filter() throws exception for different sized
            source and destination.
 * @run main RescaleOpExceptionTest
 */

import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import java.awt.image.RescaleOp;
import java.awt.image.WritableRaster;

public class RescaleOpExceptionTest {

    public static void main(String[] args) throws Exception {

        RescaleOp op = new RescaleOp(1.0f, 0.0f, null);

        BufferedImage srcI = new BufferedImage(1, 1, TYPE_INT_RGB);
        BufferedImage dstI = new BufferedImage(1, 2, TYPE_INT_RGB);

        boolean caughtIAE = false;
        try {
             op.filter(srcI, dstI);
        } catch (IllegalArgumentException e) {
            caughtIAE = true;
        }
        if (!caughtIAE) {
            throw new RuntimeException("Expected IllegalArgumentException");
        }

        WritableRaster srcR = srcI.getRaster();
        WritableRaster dstR = dstI.getRaster();

        caughtIAE = false;
        try {
             op.filter(srcR, dstR);
        } catch (IllegalArgumentException e) {
            caughtIAE = true;
        }
        if (!caughtIAE) {
            throw new RuntimeException("Expected IllegalArgumentException");
        }
    }
}
