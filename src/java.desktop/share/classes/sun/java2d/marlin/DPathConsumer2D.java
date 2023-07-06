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

package java.desktop.share.classes.sun.java2d.marlin;

public interface DPathConsumer2D {
    /**
     * @see java.awt.geom.Path2D.Float#moveTo
     */
    void moveTo(double x, double y);

    /**
     * @see java.awt.geom.Path2D.Float#lineTo
     */
    void lineTo(double x, double y);

    /**
     * @see java.awt.geom.Path2D.Float#quadTo
     */
    void quadTo(double x1, double y1,
                       double x2, double y2);

    /**
     * @see java.awt.geom.Path2D.Float#curveTo
     */
    void curveTo(double x1, double y1,
                        double x2, double y2,
                        double x3, double y3);

    /**
     * @see java.awt.geom.Path2D.Float#closePath
     */
    void closePath();

    /**
     * Called after the last segment of the last subpath when the
     * iteration of the path segments is completely done.  This
     * method serves to trigger the end of path processing in the
     * consumer that would normally be triggered when a
     * {@link java.awt.geom.PathIterator PathIterator}
     * returns {@code true} from its {@code done} method.
     */
    void pathDone();

    /**
     * If a given PathConsumer performs all or most of its work
     * natively then it can return a (non-zero) pointer to a
     * native function vector that defines C functions for all
     * of the above methods.
     * The specific pointer it returns is a pointer to a
     * PathConsumerVec structure as defined in the include file
     * src/share/native/sun/java2d/pipe/PathConsumer2D.h
     * @return a native pointer to a PathConsumerVec structure.
     */
    long getNativeConsumer();
}