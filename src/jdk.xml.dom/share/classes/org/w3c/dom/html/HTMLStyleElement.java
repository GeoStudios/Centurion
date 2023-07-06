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
 *  Style information. See the  STYLE element definition in HTML 4.0, the
 * module and the <code>LinkStyle</code> interface in the  module.
 * <p>See also the <a href='http://www.w3.org/TR/2000/CR-DOM-Level-2-20000510'>Document Object Model (DOM) Level 2 Specification</a>.
 *
 */
public interface HTMLStyleElement extends HTMLElement {
    /**
     *  Enables/disables the style sheet.
     */
    boolean getDisabled();
    void setDisabled(boolean disabled);

    /**
     *  Designed for use with one or more target media. See the  media
     * attribute definition in HTML 4.0.
     */
    String getMedia();
    void setMedia(String media);

    /**
     *  The content type pf the style sheet language. See the  type attribute
     * definition in HTML 4.0.
     */
    String getType();
    void setType(String type);

}