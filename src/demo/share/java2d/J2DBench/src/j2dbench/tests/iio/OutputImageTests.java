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

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.java.io.java.io.java.io.IOException;
import java.io.OutputStream;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.event.IIOWriteProgressjava.util.Listener;
import javax.imageio.spi.IIORegistry;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.stream.ImageOutputStream;
import demo.share.java2d.J2DBench.src.j2dbench.Group;
import demo.share.java2d.J2DBench.src.j2dbench.Modifier;
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

abstract class OutputImageTests extends OutputTests {

    private static final int TEST_IMAGEIO     = 1;
    private static final int TEST_IMAGEWRITER = 2;

    private static Group imageRoot;

    private static Group imageioRoot;
    private static Group imageioOptRoot;
    private static ImageWriterSpi[] imageioWriterSpis;
    private static String[] imageioWriteFormatShortNames;
    private static Option imageioWriteFormatList;
    private static Group imageioTestRoot;

    private static Group imageWriterRoot;
    private static Group imageWriterOptRoot;
    private static Option installListenerTog;
    private static Group imageWriterTestRoot;

    public static void init() {
        imageRoot = new Group(outputRoot, "image", "Image Writing Benchmarks");
        imageRoot.setTabbed();

        // Image I/O Benchmarks
        if (hasImageIO) {
            imageioRoot = new Group(imageRoot, "imageio", "Image I/O");

            // Image I/O Options
            imageioOptRoot = new Group(imageioRoot, "opts",
                                       "Image I/O Options");
            initIIOWriteFormats();
            imageioWriteFormatList =
                new Option.ObjectList(imageioOptRoot,
                                      "format", "Image Format",
                                      imageioWriteFormatShortNames,
                                      imageioWriterSpis,
                                      imageioWriteFormatShortNames,
                                      imageioWriteFormatShortNames,
                                      0x0);

            // Image I/O Tests
            imageioTestRoot = new Group(imageioRoot, "tests",
                                        "Image I/O Tests");
            new ImageIOWrite();

            // ImageWriter Options
            imageWriterRoot = new Group(imageioRoot, "writer",
                                        "ImageWriter Benchmarks");
            imageWriterOptRoot = new Group(imageWriterRoot, "opts",
                                           "ImageWriter Options");
            installListenerTog =
                new Option.Toggle(imageWriterOptRoot,
                                  "installListener",
                                  "Install Progress Listener",
                                  Option.Toggle.Off);

            // ImageWriter Tests
            imageWriterTestRoot = new Group(imageWriterRoot, "tests",
                                            "ImageWriter Tests");
            new ImageWriterWrite();
        }
    }

    private static void initIIOWriteFormats() {
        List spis = new ArrayList();
        List shortNames = new ArrayList();

        ImageIO.scanForPlugins();
        IIORegistry registry = IIORegistry.getDefaultInstance();
        java.util.Iterator writerspis =
            registry.getServiceProviders(ImageWriterSpi.class, false);
        while (writerspis.hasNext()) {
            // REMIND: there could be more than one non-core plugin for
            // a particular format, as is the case for JPEG2000 in the JAI
            // IIO Tools package, so we should support that somehow
            ImageWriterSpi spi = (ImageWriterSpi)writerspis.next();
            String klass = spi.getClass().getName();
            String format = spi.getFormatNames()[0].toLowerCase();
            String suffix = spi.getFileSuffixes()[0].toLowerCase();
            if (suffix == null || suffix.equals("")) {
                suffix = format;
            }
            String shortName;
            if (klass.startsWith("com.sun.imageio.plugins")) {
                shortName = "core-" + suffix;
            } else {
                shortName = "ext-" + suffix;
            }
            spis.add(spi);
            shortNames.add(shortName);
        }

        imageioWriterSpis = new ImageWriterSpi[spis.size()];
        imageioWriterSpis = (ImageWriterSpi[])spis.toArray(imageioWriterSpis);
        imageioWriteFormatShortNames = new String[shortNames.size()];
        imageioWriteFormatShortNames =
            (String[])shortNames.toArray(imageioWriteFormatShortNames);
    }

    protected OutputImageTests(Group parent,
                               String nodeName, String description)
    {
        super(parent, nodeName, description);
    }

    public void cleanupTest(TestEnvironment env, Object ctx) {
        Context iioctx = (Context)ctx;
        iioctx.cleanup(env);
    }

    private static class Context extends OutputTests.Context {
        String format;
        BufferedImage image;
        ImageWriter writer;

        Context(TestEnvironment env, Result result, int testType) {
            super(env, result);

            String content = (String)env.getModifier(contentList);
            if (content == null) {
                content = CONTENT_BLANK;
            }
            // REMIND: add option for non-opaque images
            image = createBufferedImage(size, size, content, false);

            result.setUnits(size*size);
            result.setUnitName("pixel");

            if (testType == TEST_IMAGEIO || testType == TEST_IMAGEWRITER) {
                ImageWriterSpi writerspi =
                    (ImageWriterSpi)env.getModifier(imageioWriteFormatList);
                format = writerspi.getFileSuffixes()[0].toLowerCase();
                if (testType == TEST_IMAGEWRITER) {
                    try {
                        writer = writerspi.createWriterInstance();
                    } catch (IOException e) {
                        System.err.println("error creating writer");
                        e.printStackTrace();
                    }
                    if (env.isEnabled(installListenerTog)) {
                        writer.addIIOWriteProgressListener(
                            new WriteProgressListener());
                    }
                }
                if (format.equals("wbmp")) {
                    // REMIND: this is a hack to create an image that the
                    //         WBMPImageWriter can handle (a better approach
                    //         would involve checking the ImageTypeSpecifier
                    //         of the writer's default image param)
                    BufferedImage newimg =
                        new BufferedImage(size, size,
                                          BufferedImage.TYPE_BYTE_BINARY);
                    Graphics g = newimg.createGraphics();
                    g.drawImage(image, 0, 0, null);
                    g.dispose();
                    image = newimg;
                }
            } else { // testType == TEST_JPEGCODEC
                format = "jpeg";
            }

            initOutput();
        }

        void initContents(File f) throws IOException {
            ImageIO.write(image, format, f);
        }

        void initContents(OutputStream out) throws IOException {
            ImageIO.write(image, format, out);
        }

        void cleanup(TestEnvironment env) {
            super.cleanup(env);
            if (writer != null) {
                writer.dispose();
                writer = null;
            }
        }
    }

    private static class ImageIOWrite extends OutputImageTests {
        public ImageIOWrite() {
            super(imageioTestRoot,
                  "imageioWrite",
                  "ImageIO.write()");
            addDependency(generalDestRoot,
                new Modifier.Filter() {
                    public boolean isCompatible(Object val) {
                        // ImageIO.write() handles FILE and ARRAY, but
                        // not FILECHANNEL (well, I suppose we could create
                        // an ImageOutputStream from a FileChannel source,
                        // but that's not a common use case; FileChannel is
                        // better handled by the ImageWriter tests below)
                        OutputType t = (OutputType)val;
                        return (t.getType() != OUTPUT_FILECHANNEL);
                    }
                });
            addDependencies(imageioOptRoot, true);
        }

        public Object initTest(TestEnvironment env, Result result) {
            return new Context(env, result, TEST_IMAGEIO);
        }

        public void runTest(Object ctx, int numReps) {
            final Context ictx = (Context)ctx;
            final Object output = ictx.output;
            final BufferedImage image = ictx.image;
            final String format = ictx.format;
            final int outputType = ictx.outputType;
            switch (outputType) {
            case OUTPUT_FILE:
                do {
                    try {
                        ImageIO.write(image, format, (File)output);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } while (--numReps >= 0);
                break;
            case OUTPUT_ARRAY:
                do {
                    try {
                        ByteArrayOutputStream baos =
                            new ByteArrayOutputStream();
                        BufferedOutputStream bos =
                            new BufferedOutputStream(baos);
                        ImageIO.write(image, format, bos);
                        baos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } while (--numReps >= 0);
                break;
            default:
                throw new IllegalArgumentException("Invalid output type");
            }
        }
    }

    private static class ImageWriterWrite extends OutputImageTests {
        public ImageWriterWrite() {
            super(imageWriterTestRoot,
                  "write",
                  "ImageWriter.write()");
            addDependency(generalDestRoot);
            addDependencies(imageioGeneralOptRoot, true);
            addDependencies(imageioOptRoot, true);
            addDependencies(imageWriterOptRoot, true);
        }

        public Object initTest(TestEnvironment env, Result result) {
            return new Context(env, result, TEST_IMAGEWRITER);
        }

        public void runTest(Object ctx, int numReps) {
            final Context ictx = (Context)ctx;
            final ImageWriter writer = ictx.writer;
            final BufferedImage image = ictx.image;
            do {
                try {
                    ImageOutputStream ios = ictx.createImageOutputStream();
                    writer.setOutput(ios);
                    writer.write(image);
                    writer.reset();
                    ios.close();
                    ictx.closeOriginalStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (--numReps >= 0);
        }
    }

    private static class WriteProgressListener
        implements IIOWriteProgressListener
    {
        public void imageStarted(ImageWriter source, int imageIndex) {}
        public void imageProgress(ImageWriter source,
                                  float percentageDone) {}
        public void imageComplete(ImageWriter source) {}
        public void thumbnailStarted(ImageWriter source,
                                     int imageIndex, int thumbnailIndex) {}
        public void thumbnailProgress(ImageWriter source,
                                      float percentageDone) {}
        public void thumbnailComplete(ImageWriter source) {}
        public void writeAborted(ImageWriter source) {}
    }
}
