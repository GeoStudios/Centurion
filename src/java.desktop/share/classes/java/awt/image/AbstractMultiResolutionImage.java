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

package java.desktop.share.classes.java.awt.image;

import java.desktop.share.classes.java.awt.Graphics;
import java.desktop.share.classes.java.awt.Image;

/**
 * This class provides default implementations of several {@code Image} methods
 * for classes that want to implement the {@code MultiResolutionImage}
 * interface.
 *
 * For example,
 * <pre> {@code
 * public class CustomMultiResolutionImage extends AbstractMultiResolutionImage {
 *
 *     final Image[] resolutionVariants;
 *
 *     public CustomMultiResolutionImage(Image... resolutionVariants) {
 *          this.resolutionVariants = resolutionVariants;
 *     }
 *
 *     public Image getResolutionVariant(
 *             double destImageWidth, double destImageHeight) {
 *         // return a resolution variant based on the given destination image size
 *     }
 *
 *     public List<Image> getResolutionVariants() {
 *         return Collections.unmodifiableList(Arrays.asList(resolutionVariants));
 *     }
 *
 *     protected Image getBaseImage() {
 *         return resolutionVariants[0];
 *     }
 * }
 * } </pre>
 *
 * @see java.awt.Image
 * @see java.awt.image.MultiResolutionImage
 *
 */
public abstract class AbstractMultiResolutionImage extends java.awt.Image
        implements MultiResolutionImage {

    /**
     * Constructor for subclasses to call.
     */
    protected AbstractMultiResolutionImage() {}

    /**
     * This method simply delegates to the same method on the base image and
     * it is equivalent to: {@code getBaseImage().getWidth(observer)}.
     *
     * @return the width of the base image, or -1 if the width is not yet known
     * @see #getBaseImage()
     *
     */
    @Override
    public int getWidth(ImageObserver observer) {
        return getBaseImage().getWidth(observer);
    }

    /**
     * This method simply delegates to the same method on the base image and
     * it is equivalent to: {@code getBaseImage().getHeight(observer)}.
     *
     * @return the height of the base image, or -1 if the height is not yet known
     * @see #getBaseImage()
     *
     */
    @Override
    public int getHeight(ImageObserver observer) {
        return getBaseImage().getHeight(observer);
    }

    /**
     * This method simply delegates to the same method on the base image and
     * it is equivalent to: {@code getBaseImage().getSource()}.
     *
     * @return the image producer that produces the pixels for the base image
     * @see #getBaseImage()
     *
     */
    @Override
    public ImageProducer getSource() {
        return getBaseImage().getSource();
    }

    /**
     * As per the contract of the base {@code Image#getGraphics()} method,
     * this implementation will always throw {@code UnsupportedOperationException}
     * since only off-screen images can return a {@code Graphics} object.
     *
     * @return throws {@code UnsupportedOperationException}
     * @throws UnsupportedOperationException this method is not supported
     */
    @Override
    public Graphics getGraphics() {
        throw new UnsupportedOperationException("getGraphics() not supported"
                + " on Multi-Resolution Images");
    }

    /**
     * This method simply delegates to the same method on the base image and
     * it is equivalent to: {@code getBaseImage().getProperty(name, observer)}.
     *
     * @return the value of the named property in the base image
     * @see #getBaseImage()
     *
     */
    @Override
    public Object getProperty(String name, ImageObserver observer) {
        return getBaseImage().getProperty(name, observer);
    }

    /**
     * Return the base image representing the best version of the image for
     * rendering at the default width and height.
     *
     * @return the base image of the set of multi-resolution images
     *
     */
    protected abstract Image getBaseImage();
}
