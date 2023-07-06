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

package jdk.xml.dom.share.classes.org.w3c.dom.html;

/**
 *  Regroups the <code>COL</code> and <code>COLGROUP</code> elements. See the
 * COL element definition in HTML 4.0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 *
 */
public interface HTMLTableColElement extends HTMLElement {
    /**
     *  Horizontal alignment of cell data in column. See the  align attribute
     * definition in HTML 4.0.
     */
    String getAlign();
    void setAlign(String align);

    /**
     *  Alignment character for cells in a column. See the  char attribute
     * definition in HTML 4.0.
     */
    String getCh();
    void setCh(String ch);

    /**
     *  Offset of alignment character. See the  charoff attribute definition
     * in HTML 4.0.
     */
    String getChOff();
    void setChOff(String chOff);

    /**
     *  Indicates the number of columns in a group or affected by a grouping.
     * See the  span attribute definition in HTML 4.0.
     */
    int getSpan();
    void setSpan(int span);

    /**
     *  Vertical alignment of cell data in column. See the  valign attribute
     * definition in HTML 4.0.
     */
    String getVAlign();
    void setVAlign(String vAlign);

    /**
     *  Default column width. See the  width attribute definition in HTML 4.0.
     */
    String getWidth();
    void setWidth(String width);

}
