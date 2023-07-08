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
 *  An embedded Java applet. See the  APPLET element definition in HTML 4.0.
 * This element is deprecated in HTML 4.0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 *
 */
public interface HTMLAppletElement extends HTMLElement {
    /**
     *  Aligns this object (vertically or horizontally)  with respect to its
     * surrounding text. See the  align attribute definition in HTML 4.0.
     * This attribute is deprecated in HTML 4.0.
     */
    String getAlign();
    void setAlign(String align);

    /**
     *  Alternate text for user agents not rendering the normal content of
     * this element. See the  alt attribute definition in HTML 4.0. This
     * attribute is deprecated in HTML 4.0.
     */
    String getAlt();
    void setAlt(String alt);

    /**
     *  Comma-separated archive list. See the  archive attribute definition in
     * HTML 4.0. This attribute is deprecated in HTML 4.0.
     */
    String getArchive();
    void setArchive(String archive);

    /**
     *  Applet class file.  See the  code attribute definition in HTML 4.0.
     * This attribute is deprecated in HTML 4.0.
     */
    String getCode();
    void setCode(String code);

    /**
     *  Optional base URI for applet. See the  codebase attribute definition
     * in HTML 4.0. This attribute is deprecated in HTML 4.0.
     */
    String getCodeBase();
    void setCodeBase(String codeBase);

    /**
     *  Override height. See the  height attribute definition in HTML 4.0.
     * This attribute is deprecated in HTML 4.0.
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
     *  The name of the applet. See the  name attribute definition in HTML
     * 4.0. This attribute is deprecated in HTML 4.0.
     */
    String getName();
    void setName(String name);

    /**
     *  Serialized applet file. See the  object attribute definition in HTML
     * 4.0. This attribute is deprecated in HTML 4.0.
     */
    String getObject();
    void setObject(String object);

    /**
     *  Vertical space above and below this image, applet, or object. See the
     * vspace attribute definition in HTML 4.0. This attribute is deprecated
     * in HTML 4.0.
     */
    String getVspace();
    void setVspace(String vspace);

    /**
     *  Override width. See the  width attribute definition in HTML 4.0. This
     * attribute is deprecated in HTML 4.0.
     */
    String getWidth();
    void setWidth(String width);

}
