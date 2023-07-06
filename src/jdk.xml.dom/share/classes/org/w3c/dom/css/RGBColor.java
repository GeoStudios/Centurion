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

package jdk.xml.dom.share.classes.org.w3c.dom.css;

















/**
 *  The <code>RGBColor</code> interface is used to represent any RGB color
 * value. This interface reflects the values in the underlying style
 * property. Hence, modifications made to the <code>CSSPrimitiveValue</code>
 * objects modify the style property.
 * <p> A specified RGB color is not clipped (even if the number is outside the
 * range 0-255 or 0%-100%). A computed RGB color is clipped depending on the
 * device.
 * <p> Even if a style sheet can only contain an integer for a color value,
 * the internal storage of this integer is a float, and this can be used as
 * a float in the specified or the computed style.
 * <p> A color percentage value can always be converted to a number and vice
 * versa.
 * <p>See also the <a href='http://www.w3.org/TR/2000/REC-DOM-Level-2-Style-20001113'>Document Object Model (DOM) Level 2 Style Specification</a>.
 */
public interface RGBColor {
    /**
     *  This attribute is used for the red value of the RGB color.
     */
    CSSPrimitiveValue getRed();

    /**
     *  This attribute is used for the green value of the RGB color.
     */
    CSSPrimitiveValue getGreen();

    /**
     *  This attribute is used for the blue value of the RGB color.
     */
    CSSPrimitiveValue getBlue();

}
