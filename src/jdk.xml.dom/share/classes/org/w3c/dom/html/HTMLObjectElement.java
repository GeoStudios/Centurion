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

import jdk.xml.dom.share.classes.org.w3c.dom.Document;

/**
 *  Generic embedded object.  Note. In principle, all properties on the object
 * element are read-write but in some environments some properties may be
 * read-only once the underlying object is instantiated. See the  OBJECT
 * element definition in HTML 4.0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 *
 */
public interface HTMLObjectElement extends HTMLElement {
    /**
     *  Returns the <code>FORM</code> element containing this control. Returns
     * <code>null</code> if this control is not within the context of a form.
     */
    HTMLFormElement getForm();

    /**
     *  Applet class file. See the <code>code</code> attribute for
     * HTMLAppletElement.
     */
    String getCode();
    void setCode(String code);

    /**
     *  Aligns this object (vertically or horizontally)  with respect to its
     * surrounding text. See the  align attribute definition in HTML 4.0.
     * This attribute is deprecated in HTML 4.0.
     */
    String getAlign();
    void setAlign(String align);

    /**
     *  Space-separated list of archives. See the  archive attribute definition
     *  in HTML 4.0.
     */
    String getArchive();
    void setArchive(String archive);

    /**
     *  Width of border around the object. See the  border attribute definition
     *  in HTML 4.0. This attribute is deprecated in HTML 4.0.
     */
    String getBorder();
    void setBorder(String border);

    /**
     *  Base URI for <code>classid</code> , <code>data</code> , and
     * <code>archive</code> attributes. See the  codebase attribute definition
     *  in HTML 4.0.
     */
    String getCodeBase();
    void setCodeBase(String codeBase);

    /**
     *  Content type for data downloaded via <code>classid</code> attribute.
     * See the  codetype attribute definition in HTML 4.0.
     */
    String getCodeType();
    void setCodeType(String codeType);

    /**
     *  A URI specifying the location of the object's data.  See the  data
     * attribute definition in HTML 4.0.
     */
    String getData();
    void setData(String data);

    /**
     *  Declare (for future reference), but do not instantiate, this object.
     * See the  declare attribute definition in HTML 4.0.
     */
    boolean getDeclare();
    void setDeclare(boolean declare);

    /**
     *  Override height. See the  height attribute definition in HTML 4.0.
     */
    String getHeight();
    void setHeight(String height);

    /**
     *  Horizontal space to the left and right of this image, applet, or
     * object. See the  hspace attribute definition in HTML 4.0. This
     * attribute is deprecated in HTML 4.0.
     */
    String getHspace();
    void setHspace(String hspace);

    /**
     *  Form control or object name when submitted with a form. See the  name
     * attribute definition in HTML 4.0.
     */
    String getName();
    void setName(String name);

    /**
     *  Message to render while loading the object. See the  standby attribute
     * definition in HTML 4.0.
     */
    String getStandby();
    void setStandby(String standby);

    /**
     *  Index that represents the element's position in the tabbing order. See
     * the  tabindex attribute definition in HTML 4.0.
     */
    int getTabIndex();
    void setTabIndex(int tabIndex);

    /**
     *  Content type for data downloaded via <code>data</code> attribute. See
     * the  type attribute definition in HTML 4.0.
     */
    String getType();
    void setType(String type);

    /**
     *  Use client-side image map. See the  usemap attribute definition in
     * HTML 4.0.
     */
    String getUseMap();
    void setUseMap(String useMap);

    /**
     *  Vertical space above and below this image, applet, or object. See the
     * vspace attribute definition in HTML 4.0. This attribute is deprecated
     * in HTML 4.0.
     */
    String getVspace();
    void setVspace(String vspace);

    /**
     *  Override width. See the  width attribute definition in HTML 4.0.
     */
    String getWidth();
    void setWidth(String width);

    /**
     *  The document this object contains, if there is any and it is
     * available, or <code>null</code> otherwise.
     */
    Document getContentDocument();

}
