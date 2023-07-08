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

import jdk.xml.dom.share.classes.org.w3c.dom.DOMException;

/**
 *  The create* and delete* methods on the table allow authors to construct
 * and modify tables. HTML 4.0 specifies that only one of each of the
 * <code>CAPTION</code> , <code>THEAD</code> , and <code>TFOOT</code>
 * elements may exist in a table. Therefore, if one exists, and the
 * createTHead() or createTFoot() method is called, the method returns the
 * existing THead or TFoot element. See the  TABLE element definition in HTML
 * 4.0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 *
 */
public interface HTMLTableElement extends HTMLElement {
    /**
     *  Returns the table's <code>CAPTION</code> , or void if none exists.
     */
    HTMLTableCaptionElement getCaption();
    void setCaption(HTMLTableCaptionElement caption);

    /**
     *  Returns the table's <code>THEAD</code> , or <code>null</code> if none
     * exists.
     */
    HTMLTableSectionElement getTHead();
    void setTHead(HTMLTableSectionElement tHead);

    /**
     *  Returns the table's <code>TFOOT</code> , or <code>null</code> if none
     * exists.
     */
    HTMLTableSectionElement getTFoot();
    void setTFoot(HTMLTableSectionElement tFoot);

    /**
     *  Returns a collection of all the rows in the table, including all in
     * <code>THEAD</code> , <code>TFOOT</code> , all <code>TBODY</code>
     * elements.
     */
    HTMLCollection getRows();

    /**
     *  Returns a collection of the defined table bodies.
     */
    HTMLCollection getTBodies();

    /**
     *  Specifies the table's position with respect to the rest of the
     * document. See the  align attribute definition in HTML 4.0. This
     * attribute is deprecated in HTML 4.0.
     */
    String getAlign();
    void setAlign(String align);

    /**
     *  Cell background color. See the  bgcolor attribute definition in HTML
     * 4.0. This attribute is deprecated in HTML 4.0.
     */
    String getBgColor();
    void setBgColor(String bgColor);

    /**
     *  The width of the border around the table. See the  border attribute
     * definition in HTML 4.0.
     */
    String getBorder();
    void setBorder(String border);

    /**
     *  Specifies the horizontal and vertical space between cell content and
     * cell borders. See the  cellpadding attribute definition in HTML 4.0.
     */
    String getCellPadding();
    void setCellPadding(String cellPadding);

    /**
     *  Specifies the horizontal and vertical separation between cells. See
     * the  cellspacing attribute definition in HTML 4.0.
     */
    String getCellSpacing();
    void setCellSpacing(String cellSpacing);

    /**
     *  Specifies which external table borders to render. See the  frame
     * attribute definition in HTML 4.0.
     */
    String getFrame();
    void setFrame(String frame);

    /**
     *  Specifies which internal table borders to render. See the  rules
     * attribute definition in HTML 4.0.
     */
    String getRules();
    void setRules(String rules);

    /**
     *  Description about the purpose or structure of a table. See the
     * summary attribute definition in HTML 4.0.
     */
    String getSummary();
    void setSummary(String summary);

    /**
     *  Specifies the desired table width. See the  width attribute definition
     * in HTML 4.0.
     */
    String getWidth();
    void setWidth(String width);

    /**
     *  Create a table header row or return an existing one.
     * @return  A new table header element (<code>THEAD</code> ).
     */
    HTMLElement createTHead();

    /**
     *  Delete the header from the table, if one exists.
     */
    void deleteTHead();

    /**
     *  Create a table footer row or return an existing one.
     * @return  A footer element (<code>TFOOT</code> ).
     */
    HTMLElement createTFoot();

    /**
     *  Delete the footer from the table, if one exists.
     */
    void deleteTFoot();

    /**
     *  Create a new table caption object or return an existing one.
     * @return  A <code>CAPTION</code> element.
     */
    HTMLElement createCaption();

    /**
     *  Delete the table caption, if one exists.
     */
    void deleteCaption();

    /**
     *  Insert a new empty row in the table. The new row is inserted
     * immediately before and in the same section as the current
     * <code>index</code> th row in the table. If <code>index</code> is equal
     * to the number of rows, the new row is appended. In addition, when the
     * table is empty the row is inserted into a <code>TBODY</code> which is
     * created and inserted into the table. Note. A table row cannot be empty
     * according to HTML 4.0 Recommendation.
     * @param index  The row number where to insert a new row. This index
     *   starts from 0 and is relative to all the rows contained inside the
     *   table, regardless of section parentage.
     * @return  The newly created row.
     * @exception DOMException
     *    INDEX_SIZE_ERR: Raised if the specified index is greater than the
     *   number of rows or if the index is negative.
     */
    HTMLElement insertRow(int index)
                                 throws DOMException;

    /**
     *  Delete a table row.
     * @param index  The index of the row to be deleted. This index starts
     *   from 0 and is relative to all the rows contained inside the table,
     *   regardless of section parentage.
     * @exception DOMException
     *    INDEX_SIZE_ERR: Raised if the specified index is greater than or
     *   equal to the number of rows or if the index is negative.
     */
    void deleteRow(int index)
                          throws DOMException;

}
