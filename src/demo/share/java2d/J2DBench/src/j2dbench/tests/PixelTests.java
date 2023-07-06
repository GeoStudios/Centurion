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

package demo.share.java2d.J2DBench.src.j2dbench.tests;


import demo.share.java2d.J2DBench.src.j2dbench.Destinations;
import demo.share.java2d.J2DBench.src.j2dbench.Group;
import demo.share.java2d.J2DBench.src.j2dbench.Modifier;
import demo.share.java2d.J2DBench.src.j2dbench.Option;
import demo.share.java2d.J2DBench.src.j2dbench.Test;
import demo.share.java2d.J2DBench.src.j2dbench.Result;
import demo.share.java2d.J2DBench.src.j2dbench.TestEnvironment;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.VolatileImage;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferShort;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.awt.image.IndexColorModel;















/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */





public abstract class PixelTests extends Test {
    static Group pixelroot;

    static Group pixeloptroot;
    static Group.EnableSet bufimgsrcroot;

    static Option doRenderTo;
    static Option doRenderFrom;

    static Group bufimgtestroot;
    static Group rastertestroot;
    static Group dbtestroot;

    public static void init() {
        pixelroot = new Group("pixel", "Pixel Access Benchmarks");

        pixeloptroot = new Group(pixelroot, "opts", "Pixel Access Options");
        doRenderTo = new Option.Toggle(pixeloptroot, "renderto",
                                       "Render to Image before test",
                                       Option.Toggle.Off);
        doRenderFrom = new Option.Toggle(pixeloptroot, "renderfrom",
                                         "Render from Image before test",
                                         Option.Toggle.Off);

        // BufferedImage Sources
        bufimgsrcroot = new Group.EnableSet(pixelroot, "src",
                                            "BufferedImage Sources");
        new BufImg(BufferedImage.TYPE_BYTE_BINARY, 1);
        new BufImg(BufferedImage.TYPE_BYTE_BINARY, 2);
        new BufImg(BufferedImage.TYPE_BYTE_BINARY, 4);
        new BufImg(BufferedImage.TYPE_BYTE_INDEXED);
        new BufImg(BufferedImage.TYPE_BYTE_GRAY);
        new BufImg(BufferedImage.TYPE_USHORT_555_RGB);
        new BufImg(BufferedImage.TYPE_USHORT_565_RGB);
        new BufImg(BufferedImage.TYPE_USHORT_GRAY);
        new BufImg(BufferedImage.TYPE_3BYTE_BGR);
        new BufImg(BufferedImage.TYPE_4BYTE_ABGR);
        new BufImg(BufferedImage.TYPE_INT_RGB);
        new BufImg(BufferedImage.TYPE_INT_BGR);
        new BufImg(BufferedImage.TYPE_INT_ARGB);

        // BufferedImage Tests
        bufimgtestroot = new Group(pixelroot, "bimgtests",
                                   "BufferedImage Tests");
        new BufImgTest.GetRGB();
        new BufImgTest.SetRGB();

        // Raster Tests
        rastertestroot = new Group(pixelroot, "rastests",
                                   "Raster Tests");
        new RasTest.GetDataElements();
        new RasTest.SetDataElements();
        new RasTest.GetPixel();
        new RasTest.SetPixel();

        // DataBuffer Tests
        dbtestroot = new Group(pixelroot, "dbtests",
                               "DataBuffer Tests");
        new DataBufTest.GetElem();
        new DataBufTest.SetElem();
    }

    public PixelTests(Group root, String nodeName, String description) {
        super(root, nodeName, description);
        addDependency(bufimgsrcroot);
        addDependencies(pixeloptroot, false);
    }

    public static class Context {
        BufferedImage bimg;
        WritableRaster ras;
        DataBuffer db;
        int[] pixeldata;
        Object elemdata;
    }

    public Object initTest(TestEnvironment env, Result result) {
        Context ctx = new Context();
        ctx.bimg = ((BufImg) env.getModifier(bufimgsrcroot)).getImage();
        if (env.isEnabled(doRenderTo)) {
            Graphics2D g2d = ctx.bimg.createGraphics();
            g2d.setColor(Color.white);
            g2d.fillRect(3, 0, 1, 1);
            g2d.dispose();
        }
        if (env.isEnabled(doRenderFrom)) {
            GraphicsConfiguration cfg =
                GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();
            VolatileImage vimg = cfg.createCompatibleVolatileImage(8, 1);
            vimg.validate(cfg);
            Graphics2D g2d = vimg.createGraphics();
            for (int i = 0; i < 100; i++) {
                g2d.drawImage(ctx.bimg, 0, 0, null);
            }
            g2d.dispose();
            vimg.flush();
        }
        result.setUnits(1);
        result.setUnitName(getUnitName());
        return ctx;
    }

    public abstract String getUnitName();

    public void cleanupTest(TestEnvironment env, Object context) {
    }

    public static class BufImg extends Option.Enable {
        public static int[] rgbvals = {
            0x00000000,
            0xff0000ff,
            0x8000ff00,
            0xffffffff
        };

        static int[] cmap = {
            0xff000000,  // 0: opaque black
            0xffffffff,  // 1: opaque white

            0x00000000,  // 2: transparent black
            0x80ffffff,  // 3: translucent white

            0x00ffffff,  // 4: transparent white
            0x80000000,  // 5: translucent black
            0xff333333,  // 6: opaque dark gray
            0xff666666,  // 7: opaque medium gray
            0xff999999,  // 8: opaque gray
            0xffcccccc,  // 9: opaque light gray
            0xff0000ff,  // A: opaque blue
            0xff00ff00,  // B: opaque green
            0xff00ffff,  // C: opaque cyan
            0xffff0000,  // D: opaque red
            0xffff00ff,  // E: opaque magenta
            0xffffff00,  // F: opaque yellow
        };

        int type;
        int nbits;

        public BufImg(int type) {
            super(bufimgsrcroot,
                  Destinations.BufImg.ShortNames[type],
                  Destinations.BufImg.Descriptions[type], false);
            this.type = type;
            this.nbits = 0;
        }

        public BufImg(int type, int nbits) {
            super(bufimgsrcroot,
                  nbits+"BitBinary",
                  nbits+"-bit Binary Image", false);
            this.type = type;
            this.nbits = nbits;
        }

        public String getModifierValueName(Object val) {
            return "BufImg("+getNodeName()+")";
        }

        public BufferedImage getImage() {
            BufferedImage bimg;
            if (nbits == 0) {
                bimg = new BufferedImage(8, 1, type);
            } else {
                IndexColorModel icm =
                    new IndexColorModel(nbits, (1 << nbits),
                                        cmap, 0, (nbits > 1), -1,
                                        DataBuffer.TYPE_BYTE);
                // Note that this constructor has bugs pre 1.4...
                // bimg = new BufferedImage(64/nbits, 1, type, icm);
                WritableRaster wr =
                    icm.createCompatibleWritableRaster(64/nbits, 1);
                bimg = new BufferedImage(icm, wr, false, null);
            }
            for (int i = 0; i < bimg.getWidth(); i++) {
                bimg.setRGB(i, 0, rgbvals[i&3]);
            }
            return bimg;
        }
    }

    public abstract static class BufImgTest extends PixelTests {
        public BufImgTest(String nodeName, String description) {
            super(bufimgtestroot, nodeName, description);
        }

        public String getUnitName() {
            return "pixel";
        }

        public static class GetRGB extends BufImgTest {
            public GetRGB() {
                super("getrgb", "BufferedImage.getRGB(x, y)");
            }

            public void runTest(Object context, int numReps) {
                BufferedImage bimg = ((Context) context).bimg;
                do {
                    bimg.getRGB(numReps&7, 0);
                } while (--numReps > 0);
            }
        }

        public static class SetRGB extends BufImgTest {
            public SetRGB() {
                super("setrgb", "BufferedImage.setRGB(x, y, rgb)");
            }

            public void runTest(Object context, int numReps) {
                BufferedImage bimg = ((Context) context).bimg;
                do {
                    bimg.setRGB(numReps&7, 0, BufImg.rgbvals[numReps&3]);
                } while (--numReps > 0);
            }
        }
    }

    public abstract static class RasTest extends PixelTests {
        public RasTest(String nodeName, String description) {
            super(rastertestroot, nodeName, description);
        }

        public String getUnitName() {
            return "pixel";
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = (Context) super.initTest(env, result);
            ctx.ras = ctx.bimg.getRaster();
            ctx.pixeldata = ctx.ras.getPixel(0, 0, (int[]) null);
            ctx.elemdata = ctx.ras.getDataElements(0, 0, null);
            return ctx;
        }

        public static class GetDataElements extends RasTest {
            public GetDataElements() {
                super("getdataelem", "Raster.getDataElements(x, y, o)");
            }

            public void runTest(Object context, int numReps) {
                Raster ras = ((Context) context).ras;
                Object elemdata = ((Context) context).elemdata;
                do {
                    ras.getDataElements(numReps&7, 0, elemdata);
                } while (--numReps > 0);
            }
        }

        public static class SetDataElements extends RasTest {
            public SetDataElements() {
                super("setdataelem", "WritableRaster.setDataElements(x, y, o)");
            }

            public void runTest(Object context, int numReps) {
                WritableRaster ras = ((Context) context).ras;
                Object elemdata = ((Context) context).elemdata;
                do {
                    ras.setDataElements(numReps&7, 0, elemdata);
                } while (--numReps > 0);
            }
        }

        public static class GetPixel extends RasTest {
            public GetPixel() {
                super("getpixel", "Raster.getPixel(x, y, v[])");
            }

            public void runTest(Object context, int numReps) {
                Raster ras = ((Context) context).ras;
                int[] pixeldata = ((Context) context).pixeldata;
                do {
                    ras.getPixel(numReps&7, 0, pixeldata);
                } while (--numReps > 0);
            }
        }

        public static class SetPixel extends RasTest {
            public SetPixel() {
                super("setpixel", "WritableRaster.setPixel(x, y, v[])");
            }

            public void runTest(Object context, int numReps) {
                WritableRaster ras = ((Context) context).ras;
                int[] pixeldata = ((Context) context).pixeldata;
                do {
                    ras.setPixel(numReps&7, 0, pixeldata);
                } while (--numReps > 0);
            }
        }
    }

    public abstract static class DataBufTest extends PixelTests {
        public DataBufTest(String nodeName, String description) {
            super(dbtestroot, nodeName, description);
        }

        public String getUnitName() {
            return "element";
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = (Context) super.initTest(env, result);
            ctx.db = ctx.bimg.getRaster().getDataBuffer();
            return ctx;
        }

        public static class GetElem extends DataBufTest {
            public GetElem() {
                super("getelem", "DataBuffer.getElem(i)");
            }

            public void runTest(Object context, int numReps) {
                DataBuffer db = ((Context) context).db;
                do {
                    db.getElem(numReps&7);
                } while (--numReps > 0);
            }
        }

        public static class SetElem extends DataBufTest {
            public SetElem() {
                super("setelem", "DataBuffer.setElem(i, v)");
            }

            public void runTest(Object context, int numReps) {
                DataBuffer db = ((Context) context).db;
                do {
                    db.setElem(numReps&7, 0);
                } while (--numReps > 0);
            }
        }
    }
}
