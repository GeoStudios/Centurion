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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.xs;

















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */


/**
 * This interface represents the Annotation schema component.
 */
public interface XSAnnotation extends XSObject {
    // TargetType
    /**
     * The object type is <code>org.w3c.dom.Element</code>.
     */
    short W3C_DOM_ELEMENT           = 1;
    /**
     * The object type is <code>org.xml.sax.ContentHandler</code>.
     */
    short SAX_CONTENTHANDLER        = 2;
    /**
     * The object type is <code>org.w3c.dom.Document</code>.
     */
    short W3C_DOM_DOCUMENT          = 3;

    /**
     *  Write contents of the annotation to the specified object. If the
     * specified <code>target</code> is a DOM object, in-scope namespace
     * declarations for <code>annotation</code> element are added as
     * attribute nodes of the serialized <code>annotation</code>, otherwise
     * the corresponding events for all in-scope namespace declarations are
     * sent via the specified document handler.
     * @param target  A target pointer to the annotation target object, i.e.
     *   <code>org.w3c.dom.Document</code>, <code>org.w3c.dom.Element</code>
     *   , <code>org.xml.sax.ContentHandler</code>.
     * @param targetType  A target type.
     * @return  True if the <code>target</code> is a recognized type and
     *   supported by this implementation, otherwise false.
     */
    boolean writeAnnotation(Object target,
                                   short targetType);

    /**
     * A text representation of the annotation.
     */
    String getAnnotationString();

}
