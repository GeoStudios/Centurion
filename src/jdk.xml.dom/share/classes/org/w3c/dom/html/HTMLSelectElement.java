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

package org.w3c.dom.html;

import org.w3c.dom.DOMException;

/**
 *  The select element allows the selection of an option. The contained
 * options can be directly accessed through the select element as a
 * collection. See the  SELECT element definition in HTML 4.0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 *
 */
public interface HTMLSelectElement extends HTMLElement {
    /**
     *  The type of this form control. This is the string "select-multiple"
     * when the multiple attribute is <code>true</code> and the string
     * "select-one" when <code>false</code> .
     */
    String getType();

    /**
     *  The ordinal index of the selected option, starting from 0. The value
     * -1 is returned if no element is selected. If multiple options are
     * selected, the index of the first selected option is returned.
     */
    int getSelectedIndex();
    void setSelectedIndex(int selectedIndex);

    /**
     *  The current form control value.
     */
    String getValue();
    void setValue(String value);

    /**
     *  The number of options in this <code>SELECT</code> .
     */
    int getLength();

    /**
     *  Returns the <code>FORM</code> element containing this control. Returns
     * <code>null</code> if this control is not within the context of a form.
     */
    HTMLFormElement getForm();

    /**
     *  The collection of <code>OPTION</code> elements contained by this
     * element.
     */
    HTMLCollection getOptions();

    /**
     *  The control is unavailable in this context. See the  disabled
     * attribute definition in HTML 4.0.
     */
    boolean getDisabled();
    void setDisabled(boolean disabled);

    /**
     *  If true, multiple <code>OPTION</code> elements may  be selected in
     * this <code>SELECT</code> . See the  multiple attribute definition in
     * HTML 4.0.
     */
    boolean getMultiple();
    void setMultiple(boolean multiple);

    /**
     *  Form control or object name when submitted with a form. See the  name
     * attribute definition in HTML 4.0.
     */
    String getName();
    void setName(String name);

    /**
     *  Number of visible rows. See the  size attribute definition in HTML 4.0.
     */
    int getSize();
    void setSize(int size);

    /**
     *  Index that represents the element's position in the tabbing order. See
     * the  tabindex attribute definition in HTML 4.0.
     */
    int getTabIndex();
    void setTabIndex(int tabIndex);

    /**
     *  Add a new element to the collection of <code>OPTION</code> elements
     * for this <code>SELECT</code> . This method is the equivalent of the
     * <code>appendChild</code> method of the <code>Node</code> interface if
     * the <code>before</code> parameter is <code>null</code> . It is
     * equivalent to the <code>insertBefore</code> method on the parent of
     * <code>before</code> in all other cases.
     * @param element  The element to add.
     * @param before  The element to insert before, or <code>null</code> for
     *   the tail of the list.
     * @exception DOMException
     *    NOT_FOUND_ERR: Raised if <code>before</code> is not a descendant of
     *   the <code>SELECT</code> element.
     */
    void add(HTMLElement element,
                    HTMLElement before)
                    throws DOMException;

    /**
     *  Remove an element from the collection of <code>OPTION</code> elements
     * for this <code>SELECT</code> . Does nothing if no element has the given
     *  index.
     * @param index  The index of the item to remove, starting from 0.
     */
    void remove(int index);

    /**
     *  Removes keyboard focus from this element.
     */
    void blur();

    /**
     *  Gives keyboard focus to this element.
     */
    void focus();

}
