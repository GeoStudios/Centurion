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

/**
 *  Multi-line text field. See the  TEXTAREA element definition in HTML 4.0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 *
 */
public interface HTMLTextAreaElement extends HTMLElement {
    /**
     *  Represents the contents of the element. The value of this attribute
     * does not change if the contents of the corresponding form control, in
     * an interactive user agent, changes. Changing this attribute, however,
     * resets the contents of the form control.
     */
    String getDefaultValue();
    void setDefaultValue(String defaultValue);

    /**
     *  Returns the <code>FORM</code> element containing this control. Returns
     * <code>null</code> if this control is not within the context of a form.
     */
    HTMLFormElement getForm();

    /**
     *  A single character access key to give access to the form control. See
     * the  accesskey attribute definition in HTML 4.0.
     */
    String getAccessKey();
    void setAccessKey(String accessKey);

    /**
     *  Width of control (in characters). See the  cols attribute definition
     * in HTML 4.0.
     */
    int getCols();
    void setCols(int cols);

    /**
     *  The control is unavailable in this context. See the  disabled
     * attribute definition in HTML 4.0.
     */
    boolean getDisabled();
    void setDisabled(boolean disabled);

    /**
     *  Form control or object name when submitted with a form. See the  name
     * attribute definition in HTML 4.0.
     */
    String getName();
    void setName(String name);

    /**
     *  This control is read-only. See the  readonly attribute definition in
     * HTML 4.0.
     */
    boolean getReadOnly();
    void setReadOnly(boolean readOnly);

    /**
     *  Number of text rows. See the  rows attribute definition in HTML 4.0.
     */
    int getRows();
    void setRows(int rows);

    /**
     *  Index that represents the element's position in the tabbing order. See
     * the  tabindex attribute definition in HTML 4.0.
     */
    int getTabIndex();
    void setTabIndex(int tabIndex);

    /**
     *  The type of this form control. This the string "textarea".
     */
    String getType();

    /**
     *  Represents the current contents of the corresponding form control, in
     * an interactive user agent. Changing this attribute changes the
     * contents of the form control, but does not change the contents of the
     * element. If the entirety of the data can not fit into a single
     * <code>DOMString</code> , the implementation may truncate the data.
     */
    String getValue();
    void setValue(String value);

    /**
     *  Removes keyboard focus from this element.
     */
    void blur();

    /**
     *  Gives keyboard focus to this element.
     */
    void focus();

    /**
     *  Select the contents of the <code>TEXTAREA</code> .
     */
    void select();

}
