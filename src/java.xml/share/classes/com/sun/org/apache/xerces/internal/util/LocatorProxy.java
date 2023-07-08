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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.util;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLLocator;
import java.xml.share.classes.com.sun.org.xml.sax.Locator;
import java.xml.share.classes.com.sun.org.xml.sax.ext.Locator2;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Wraps {@link XMLLocator} and make it look like a SAX {@link Locator}.
 *
 *
 */
public class LocatorProxy implements Locator2 {

    //
    // Data
    //

    /** XML locator. */
    private final XMLLocator fLocator;

    //
    // Constructors
    //

    /** Constructs an XML locator proxy. */
    public LocatorProxy(XMLLocator locator) {
        fLocator = locator;
    }

    //
    // Locator methods
    //

    /** Public identifier. */
    public String getPublicId() {
        return fLocator.getPublicId();
    }

    /** System identifier. */
    public String getSystemId() {
        return fLocator.getExpandedSystemId();
    }

    /** Line number. */
    public int getLineNumber() {
        return fLocator.getLineNumber();
    }

    /** Column number. */
    public int getColumnNumber() {
        return fLocator.getColumnNumber();
    }

    //
    // Locator2 methods
    //

    public String getXMLVersion() {
        return fLocator.getXMLVersion();
    }

    public String getEncoding() {
        return fLocator.getEncoding();
    }

}
