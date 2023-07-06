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
 *  A selectable choice. See the  OPTION element definition in HTML 4.0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 *
 */
public interface HTMLOptionElement extends HTMLElement {
    /**
     *  Returns the <code>FORM</code> element containing this control. Returns
     * <code>null</code> if this control is not within the context of a form.
     */
    HTMLFormElement getForm();

    /**
     *  Represents the value of the HTML selected attribute. The value of this
     * attribute does not change if the state of the corresponding form
     * control, in an interactive user agent, changes. Changing
     * <code>defaultSelected</code> , however, resets the state of the form
     * control. See the  selected attribute definition in HTML 4.0.
     */
    boolean getDefaultSelected();
    void setDefaultSelected(boolean defaultSelected);

    /**
     *  The text contained within the option element.
     */
    String getText();

    /**
     *  The index of this <code>OPTION</code> in its parent <code>SELECT</code>
     *  , starting from 0.
     */
    int getIndex();

    /**
     *  The control is unavailable in this context. See the  disabled
     * attribute definition in HTML 4.0.
     */
    boolean getDisabled();
    void setDisabled(boolean disabled);

    /**
     *  Option label for use in hierarchical menus. See the  label attribute
     * definition in HTML 4.0.
     */
    String getLabel();
    void setLabel(String label);

    /**
     *  Represents the current state of the corresponding form control, in an
     * interactive user agent. Changing this attribute changes the state of
     * the form control, but does not change the value of the HTML selected
     * attribute of the element.
     */
    boolean getSelected();
    void setSelected(boolean selected);

    /**
     *  The current form control value. See the  value attribute definition in
     * HTML 4.0.
     */
    String getValue();
    void setValue(String value);

}
