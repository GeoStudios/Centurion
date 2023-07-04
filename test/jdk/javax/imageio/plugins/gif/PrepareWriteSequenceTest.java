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
 * @bug 6284538
 * @summary Test verifies whether IllegalStateException is thrown if the output
 *          stream have not set to the GIF image writer instance
 */

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;

public class PrepareWriteSequenceTest {
    public static void main(String[] args) throws IOException {
        String format = "GIF";
        ImageWriter writer = ImageIO.getImageWritersByFormatName(format).next();

        ImageWriteParam param = writer.getDefaultWriteParam();

        IIOMetadata streamMetadata = writer.getDefaultStreamMetadata(param);

        boolean gotException = false;
        try {
            writer.prepareWriteSequence(streamMetadata);
        } catch (IllegalStateException e) {
            gotException = true;
            System.out.println("Test passed.");
            e.printStackTrace(System.out);
        }

        if (!gotException) {
            throw new RuntimeException("Test failed.");
        }
    }
}
