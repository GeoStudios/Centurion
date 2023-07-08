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
 *  The <code>LINK</code> element specifies a link to an external resource,
 * and defines this document's relationship to that resource (or vice versa).
 *  See the  LINK element definition in HTML 4.0  (see also the
 * <code>LinkStyle</code> interface in the  module).
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 *
 */
public interface HTMLLinkElement extends HTMLElement {
    /**
     *  Enables/disables the link. This is currently only used for style sheet
     * links, and may be used to activate or deactivate style sheets.
     */
    boolean getDisabled();
    void setDisabled(boolean disabled);

    /**
     *  The character encoding of the resource being linked to. See the
     * charset attribute definition in HTML 4.0.
     */
    String getCharset();
    void setCharset(String charset);

    /**
     *  The URI of the linked resource. See the  href attribute definition in
     * HTML 4.0.
     */
    String getHref();
    void setHref(String href);

    /**
     *  Language code of the linked resource. See the  hreflang attribute
     * definition in HTML 4.0.
     */
    String getHreflang();
    void setHreflang(String hreflang);

    /**
     *  Designed for use with one or more target media. See the  media
     * attribute definition in HTML 4.0.
     */
    String getMedia();
    void setMedia(String media);

    /**
     *  Forward link type. See the  rel attribute definition in HTML 4.0.
     */
    String getRel();
    void setRel(String rel);

    /**
     *  Reverse link type. See the  rev attribute definition in HTML 4.0.
     */
    String getRev();
    void setRev(String rev);

    /**
     *  Frame to render the resource in. See the  target attribute definition
     * in HTML 4.0.
     */
    String getTarget();
    void setTarget(String target);

    /**
     *  Advisory content type. See the  type attribute definition in HTML 4.0.
     */
    String getType();
    void setType(String type);

}
