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
 *  Client-side image map area definition. See the  AREA element definition in
 * HTML 4.0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 *
 */
public interface HTMLAreaElement extends HTMLElement {
    /**
     *  A single character access key to give access to the form control. See
     * the  accesskey attribute definition in HTML 4.0.
     */
    String getAccessKey();
    void setAccessKey(String accessKey);

    /**
     *  Alternate text for user agents not rendering the normal content of
     * this element. See the  alt attribute definition in HTML 4.0.
     */
    String getAlt();
    void setAlt(String alt);

    /**
     *  Comma-separated list of lengths, defining an active region geometry.
     * See also <code>shape</code> for the shape of the region. See the
     * coords attribute definition in HTML 4.0.
     */
    String getCoords();
    void setCoords(String coords);

    /**
     *  The URI of the linked resource. See the  href attribute definition in
     * HTML 4.0.
     */
    String getHref();
    void setHref(String href);

    /**
     *  Specifies that this area is inactive, i.e., has no associated action.
     * See the  nohref attribute definition in HTML 4.0.
     */
    boolean getNoHref();
    void setNoHref(boolean noHref);

    /**
     *  The shape of the active area. The coordinates are given by
     * <code>coords</code> . See the  shape attribute definition in HTML 4.0.
     */
    String getShape();
    void setShape(String shape);

    /**
     *  Index that represents the element's position in the tabbing order. See
     * the  tabindex attribute definition in HTML 4.0.
     */
    int getTabIndex();
    void setTabIndex(int tabIndex);

    /**
     *  Frame to render the resource in. See the  target attribute definition
     * in HTML 4.0.
     */
    String getTarget();
    void setTarget(String target);

}
