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
 *  Represents a PSVI item for one element information item.
 */
public interface ElementPSVI extends ItemPSVI {
    /**
     * [element declaration]: an item isomorphic to the element declaration
     * used to validate this element.
     */
    XSElementDeclaration getElementDeclaration();

    /**
     *  [notation]: the notation declaration.
     */
    XSNotationDeclaration getNotation();

    /**
     * [nil]: true if clause 3.2 of Element Locally Valid (Element) (3.3.4) is
     * satisfied, otherwise false.
     */
    boolean getNil();

    /**
     * schema information: the schema information property if it is the
     * validation root, <code>null</code> otherwise.
     */
    XSModel getSchemaInformation();

}
