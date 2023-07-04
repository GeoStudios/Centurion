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
 * @bug 4895547
 * @summary Test verifies that mergeTree() of JPEGMetadata does not throw the
 *          NPE
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageOutputStream;

import org.w3c.dom.Node;

public class MergeTreeTest {
    public static void main(String[] args) throws IOException {
        ImageWriter iw =
            (ImageWriter)ImageIO.getImageWritersByFormatName("jpeg").next();

        ImageTypeSpecifier type =
            ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_RGB);

        ImageOutputStream ios =
            ImageIO.createImageOutputStream(new File("MergeTreeTest.jpeg"));
        iw.setOutput(ios);

        IIOMetadata meta = iw.getDefaultImageMetadata(type, null);

        boolean isFailed = false;

        String[] fmts = meta.getMetadataFormatNames();
        for (int i=0; i<fmts.length; i++) {
            System.out.print("Format: " + fmts[i] + " ... ");
            Node root = meta.getAsTree(fmts[i]);
            try {
                meta.mergeTree(fmts[i], root);
            } catch (NullPointerException e) {
                throw new RuntimeException("Test failed for format " + fmts[i], e);
            }
            System.out.println("PASSED");
        }
    }
}
