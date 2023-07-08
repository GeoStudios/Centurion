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
 * The <code>XSObject</code> is a base object for the XML Schema component
 * model.
 */
public interface XSObject {
    /**
     *  The <code>type</code> of this object, i.e.
     * <code>ELEMENT_DECLARATION</code>.
     */
    short getType();

    /**
     * The name of type <code>NCName</code>, as defined in XML Namespaces, of
     * this declaration specified in the <code>{name}</code> property of the
     * component or <code>null</code> if the definition of this component
     * does not have a <code>{name}</code> property. For anonymous types,
     * the processor must construct and expose an anonymous type name that
     * is distinct from the name of every named type and the name of every
     * other anonymous type.
     */
    String getName();

    /**
     *  The [target namespace] of this object, or <code>null</code> if it is
     * unspecified.
     */
    String getNamespace();

    /**
     * A namespace schema information item corresponding to the target
     * namespace of the component, if it is globally declared; or
     * <code>null</code> otherwise.
     */
    XSNamespaceItem getNamespaceItem();

}
