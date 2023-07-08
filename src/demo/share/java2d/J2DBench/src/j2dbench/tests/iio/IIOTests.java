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

package demo.share.java2d.J2DBench.src.j2dbench.tests.iio;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import demo.share.java2d.J2DBench.src.j2dbench.Group;
import demo.share.java2d.J2DBench.src.j2dbench.Option;
import demo.share.java2d.J2DBench.src.j2dbench.Result;
import demo.share.java2d.J2DBench.src.j2dbench.Test;
import demo.share.java2d.J2DBench.src.j2dbench.TestEnvironment;















/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */






public abstract class IIOTests extends Test {

    protected static final String CONTENT_BLANK  = "blank";
    protected static final String CONTENT_RANDOM = "random";
    protected static final String CONTENT_VECTOR = "vector";
    protected static final String CONTENT_PHOTO  = "photo";

    static boolean hasImageIO;

    static {
        try {
            hasImageIO = (javax.imageio.ImageIO.class != null);
        } catch (NoClassDefFoundError e) {
        }
    }

    protected static Group iioRoot;
    protected static Group iioOptRoot;

    protected static Option sizeList;
    protected static Option contentList;
    protected static Option listenerTog;

    public static void init() {
        if (!hasImageIO) {
            // REMIND: We currently rely on Image I/O to generate the image
            //         files that are used in the InputImageTests, so
            //         unfortunately we need to punt on pre-1.4 JDKs...
            return;
        }

        iioRoot = new Group("imageio", "Image I/O Benchmarks");
        iioRoot.setTabbed();

        iioOptRoot = new Group(iioRoot, "opts", "General Options");

        int[] sizes = new int[] {1, 20, 250, 1000, 4000};
        String[] sizeStrs = new String[] {
            "1x1", "20x20", "250x250", "1000x1000", "4000x4000"
        };
        String[] sizeDescs = new String[] {
            "Tiny Images (1x1)",
            "Small Images (20x20)",
            "Medium Images (250x250)",
            "Large Images (1000x1000)",
            "Huge Images (4000x4000)",
        };
        sizeList = new Option.IntList(iioOptRoot,
                                      "size", "Image Size",
                                      sizes, sizeStrs, sizeDescs, 0x4);
        ((Option.ObjectList) sizeList).setNumRows(5);

        String[] contentStrs = new String[] {
            CONTENT_BLANK, CONTENT_RANDOM, CONTENT_VECTOR, CONTENT_PHOTO,
        };
        String[] contentDescs = new String[] {
            "Blank (opaque black)",
            "Random",
            "Vector Art",
            "Photograph",
        };
        contentList = new Option.ObjectList(iioOptRoot,
                                            "content", "Image Content",
                                            contentStrs, contentStrs,
                                            contentStrs, contentDescs,
                                            0x8);

        InputTests.init();
        if (hasImageIO) {
            OutputTests.init();
        }
    }

    protected IIOTests(Group parent, String nodeName, String description) {
        super(parent, nodeName, description);
        addDependencies(iioOptRoot, true);
    }

    protected static BufferedImage createBufferedImage(int width,
                                                       int height,
                                                       String type,
                                                       boolean hasAlpha)
    {
        BufferedImage image;
        image = new BufferedImage(width, height, hasAlpha ?
                                  BufferedImage.TYPE_INT_ARGB :
                                  BufferedImage.TYPE_INT_RGB);

        if (type.equals(CONTENT_RANDOM)) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = (int)(Math.random() * 0xffffff);
                    if (hasAlpha) {
                        rgb |= 0x7f000000;
                    }
                    image.setRGB(x, y, rgb);
                }
            }
        } else if (type.equals(CONTENT_VECTOR)) {
            Graphics2D g = image.createGraphics();
            if (hasAlpha) {
                // fill background with a translucent color
                g.setComposite(AlphaComposite.getInstance(
                                   AlphaComposite.SRC, 0.5f));
            }
            g.setColor(Color.blue);
            g.fillRect(0, 0, width, height);
            g.setComposite(AlphaComposite.Src);
            g.setColor(Color.yellow);
            g.fillOval(2, 2, width-4, height-4);
            g.setColor(Color.red);
            g.fillOval(4, 4, width-8, height-8);
            g.setColor(Color.green);
            g.fillRect(8, 8, width-16, height-16);
            g.setColor(Color.white);
            g.drawLine(0, 0, width, height);
            g.drawLine(0, height, width, 0);
            g.dispose();
        } else if (type.equals(CONTENT_PHOTO)) {
            Image photo = null;
            try {
                photo = Toolkit.getDefaultToolkit().createImage(
                    IIOTests.class.getResource("images/photo.jpg"));
            } catch (Exception e) {
                System.err.println("error loading photo");
                e.printStackTrace();
            }
            Graphics2D g = image.createGraphics();
            if (hasAlpha) {
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC,
                                                          0.5f));
            }
            g.drawImage(photo, 0, 0, width, height, null);
            g.dispose();
        } else { // CONTENT_BLANK
            // leave the image empty
        }

        return image;
    }
}
