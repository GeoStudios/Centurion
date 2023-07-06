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
 *  Inline subwindows. See the  IFRAME element definition in HTML 4.0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 *
 */
public interface HTMLIFrameElement extends HTMLElement {
    /**
     *  Aligns this object (vertically or horizontally)  with respect to its
     * surrounding text. See the  align attribute definition in HTML 4.0.
     * This attribute is deprecated in HTML 4.0.
     */
    String getAlign();
    void setAlign(String align);

    /**
     *  Request frame borders. See the  frameborder attribute definition in
     * HTML 4.0.
     */
    String getFrameBorder();
    void setFrameBorder(String frameBorder);

    /**
     *  Frame height. See the  height attribute definition in HTML 4.0.
     */
    String getHeight();
    void setHeight(String height);

    /**
     *  URI designating a long description of this image or frame. See the
     * longdesc attribute definition in HTML 4.0.
     */
    String getLongDesc();
    void setLongDesc(String longDesc);

    /**
     *  Frame margin height, in pixels. See the  marginheight attribute
     * definition in HTML 4.0.
     */
    String getMarginHeight();
    void setMarginHeight(String marginHeight);

    /**
     *  Frame margin width, in pixels. See the  marginwidth attribute
     * definition in HTML 4.0.
     */
    String getMarginWidth();
    void setMarginWidth(String marginWidth);

    /**
     *  The frame name (object of the <code>target</code> attribute). See the
     * name attribute definition in HTML 4.0.
     */
    String getName();
    void setName(String name);

    /**
     *  Specify whether or not the frame should have scrollbars. See the
     * scrolling attribute definition in HTML 4.0.
     */
    String getScrolling();
    void setScrolling(String scrolling);

    /**
     *  A URI designating the initial frame contents. See the  src attribute
     * definition in HTML 4.0.
     */
    String getSrc();
    void setSrc(String src);

    /**
     *  Frame width. See the  width attribute definition in HTML 4.0.
     */
    String getWidth();
    void setWidth(String width);

    /**
     *  The document this frame contains, if there is any and it is available,
     * or <code>null</code> otherwise.
     */
    Document getContentDocument();

}
