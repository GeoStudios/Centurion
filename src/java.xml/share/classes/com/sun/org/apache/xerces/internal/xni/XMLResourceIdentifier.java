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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.xni;

















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */


/**
 * <p> This represents the basic physical description of the location of any
 * XML resource (a Schema grammar, a DTD, a general entity etc.) </p>
 *
 */

public interface XMLResourceIdentifier {

    /** Sets the public identifier. */
    void setPublicId(String publicId);

    /** Returns the public identifier. */
    String getPublicId();

    /** Sets the expanded system identifier. */
    void setExpandedSystemId(String systemId);

    /** Returns the expanded system identifier. */
    String getExpandedSystemId();

    /** Sets the literal system identifier. */
    void setLiteralSystemId(String systemId);

    /** Returns the literal system identifier. */
    String getLiteralSystemId();

    /** Setsthe base URI against which the literal SystemId is to be
        resolved.*/
    void setBaseSystemId(String systemId);

    /** <p> Returns the base URI against which the literal SystemId is to be
        resolved. </p> */
    String getBaseSystemId();

    /** Sets the namespace of the resource. */
    void setNamespace(String namespace);

    /** Returns the namespace of the resource. */
    String getNamespace();

} // XMLResourceIdentifier
