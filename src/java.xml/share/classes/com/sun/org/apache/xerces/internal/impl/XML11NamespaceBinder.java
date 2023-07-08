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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * This class performs namespace binding on the startElement and endElement
 * method calls in accordance with Namespaces in XML 1.1.  It extends the standard,
 * Namespace-1.0-compliant binder in order to do this.
 *
 * @xerces.internal
 *
 *
 */
public class XML11NamespaceBinder extends XMLNamespaceBinder {

    //
    // Constants
    //

    //
    // Data
    //

    //
    // Constructors
    //

    /** Default constructor. */
    public XML11NamespaceBinder() {
    } // <init>()
    //
    // Public methods
    //

    //
    // Protected methods
    //

    // returns true iff the given prefix is bound to "" *and*
    // this is disallowed by the version of XML namespaces in use.
    protected boolean prefixBoundToNullURI(String uri, String localpart) {
        return false;
    } // prefixBoundToNullURI(String, String):  boolean

} // class XML11NamespaceBinder
