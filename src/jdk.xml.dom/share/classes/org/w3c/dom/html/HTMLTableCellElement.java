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
 *  The object used to represent the <code>TH</code> and <code>TD</code>
 * elements. See the  TD element definition in HTML 4.0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 *
 */
public interface HTMLTableCellElement extends HTMLElement {
    /**
     *  The index of this cell in the row, starting from 0. This index is in
     * document tree order and not display order.
     */
    int getCellIndex();

    /**
     *  Abbreviation for header cells. See the  abbr attribute definition in
     * HTML 4.0.
     */
    String getAbbr();
    void setAbbr(String abbr);

    /**
     *  Horizontal alignment of data in cell. See the  align attribute
     * definition in HTML 4.0.
     */
    String getAlign();
    void setAlign(String align);

    /**
     *  Names group of related headers. See the  axis attribute definition in
     * HTML 4.0.
     */
    String getAxis();
    void setAxis(String axis);

    /**
     *  Cell background color. See the  bgcolor attribute definition in HTML
     * 4.0. This attribute is deprecated in HTML 4.0.
     */
    String getBgColor();
    void setBgColor(String bgColor);

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
     *  Number of columns spanned by cell. See the  colspan attribute
     * definition in HTML 4.0.
     */
    int getColSpan();
    void setColSpan(int colSpan);

    /**
     *  List of <code>id</code> attribute values for header cells. See the
     * headers attribute definition in HTML 4.0.
     */
    String getHeaders();
    void setHeaders(String headers);

    /**
     *  Cell height. See the  height attribute definition in HTML 4.0. This
     * attribute is deprecated in HTML 4.0.
     */
    String getHeight();
    void setHeight(String height);

    /**
     *  Suppress word wrapping. See the  nowrap attribute definition in HTML
     * 4.0. This attribute is deprecated in HTML 4.0.
     */
    boolean getNoWrap();
    void setNoWrap(boolean noWrap);

    /**
     *  Number of rows spanned by cell. See the  rowspan attribute definition
     * in HTML 4.0.
     */
    int getRowSpan();
    void setRowSpan(int rowSpan);

    /**
     *  Scope covered by header cells. See the  scope attribute definition in
     * HTML 4.0.
     */
    String getScope();
    void setScope(String scope);

    /**
     *  Vertical alignment of data in cell. See the  valign attribute
     * definition in HTML 4.0.
     */
    String getVAlign();
    void setVAlign(String vAlign);

    /**
     *  Cell width. See the  width attribute definition in HTML 4.0. This
     * attribute is deprecated in HTML 4.0.
     */
    String getWidth();
    void setWidth(String width);

}
