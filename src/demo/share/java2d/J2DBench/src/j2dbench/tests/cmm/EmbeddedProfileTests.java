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

package demo.share.java2d.J2DBench.src.j2dbench.tests.cmm;

import java.awt.image.BufferedImage;
import java.io.java.io.java.io.java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import demo.share.java2d.J2DBench.src.j2dbench.Group;
import demo.share.java2d.J2DBench.src.j2dbench.Option;
import demo.share.java2d.J2DBench.src.j2dbench.Result;
import demo.share.java2d.J2DBench.src.j2dbench.TestEnvironment;

/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */

/* This benchmark verifies how changes in cmm library affects image decoding */
public class EmbeddedProfileTests extends ColorConversionTests {

    protected static Group grpRoot;
    protected static Group grpOptionsRoot;

    protected static Option inputImages;

    public static void init() {
        grpRoot = new Group(colorConvRoot, "embed", "Embedded Profile Tests");

        grpOptionsRoot = new Group(grpRoot, "embedOptions", "Options");

        inputImages = createImageList();

        new ReadImageTest();
    }

    private static class IccImageResource {
        static IccImageResource SMALL = new IccImageResource("images/img_icc_small.jpg", "512x512", "Small: 512x512");
        static IccImageResource MEDIUM = new IccImageResource("images/img_icc_medium.jpg", "2048x2048", "Medium: 2048x2048");
        static IccImageResource LARGE = new IccImageResource("images/img_icc_large.jpg", "4096x4096", "Large: 4096x4096");

        private IccImageResource(String file, String name, String description) {
            this.url = CMMTests.class.getResource(file);
            this.abbrev = name;
            this.description = description;
        }

        public final URL url;
        public final String abbrev;
        public final String description;

        public static IccImageResource[] values() {
            return new IccImageResource[]{SMALL, MEDIUM, LARGE};
        }
    }

    private static Option createImageList() {
        IccImageResource[] images = IccImageResource.values();

        int num = images.length;

        String[] names = new String[num];
        String[] abbrev = new String[num];
        String[] descr = new String[num];

        for (int i = 0; i < num; i++) {
            names[i] = images[i].abbrev;
            abbrev[i] = images[i].abbrev;
            descr[i] = images[i].description;
        }

         Option list = new Option.ObjectList(grpOptionsRoot,
                "Images", "Input Images",
                names, images, abbrev, descr, 1);

         return list;
    }

    public EmbeddedProfileTests(Group parent, String nodeName, String description) {
        super(parent, nodeName, description);
        addDependencies(grpOptionsRoot, true);
    }

    private static class Context {
        URL input;

        public Context(TestEnvironment env, Result res) {

            IccImageResource icc_input = (IccImageResource)
                    env.getModifier(inputImages);

            input = icc_input.url;
        }
    }

     public Object initTest(TestEnvironment env, Result res) {
        return new Context(env, res);
    }

    public void cleanupTest(TestEnvironment env, Object o) {
        Context ctx = (Context)o;
        ctx.input = null;
    }

    private static class ReadImageTest extends EmbeddedProfileTests {
        public ReadImageTest() {
            super(grpRoot, "embd_img_read", "ImageReader.read()");
        }

        public void runTest(Object octx, int numReps) {
            final Context ctx = (Context)octx;
            final URL url = ctx.input;
            ImageInputStream iis = null;
            ImageReader reader = null;

            try {
                iis = ImageIO.createImageInputStream(url.openStream());
                reader = ImageIO.getImageReaders(iis).next();
            } catch (IOException e) {
                throw new RuntimeException("Unable to run the benchmark", e);
            }

            do {
                try {
                    reader.setInput(iis);
                    BufferedImage img = reader.read(0);
                    reader.reset();

                    iis = ImageIO.createImageInputStream(url.openStream());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (--numReps >= 0);
        }
    }
}
