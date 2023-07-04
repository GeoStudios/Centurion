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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import org.xml.sax.Locator;

/**
 * Wraps SAX {@link Locator} into Xerces {@link XMLLocator}.
 *
 *     Kohsuke Kawaguchi
 */
public class LocatorWrapper implements XMLLocator {

    private final Locator locator;

    public LocatorWrapper( Locator _loc ) { this.locator=_loc; }

    public int getColumnNumber()  { return locator.getColumnNumber(); }
    public int getLineNumber()    { return locator.getLineNumber(); }
    public String getBaseSystemId() { return null; }
    public String getExpandedSystemId() { return locator.getSystemId(); }
    public String getLiteralSystemId() { return locator.getSystemId(); }
    public String getPublicId()   { return locator.getPublicId(); }
    public String getEncoding() { return null; }

    /**
     * <p>Returns the character offset,
     * or <code>-1</code>,
     * if no character offset is available.<p>
     *
     * <p>As this information is not available from
     * {@link org.xml.sax.Locator},
     * always return <code>-1</code>.</p>
     */
    public int getCharacterOffset() {
        return -1;
    }

    /**
     * <p>Returns the XML version of the current entity.</p>
     *
     * <p>As this information is not available from
     * {@link org.xml.sax.Locator},
     * always return <code>null</code>.</p>
     */
    public String getXMLVersion() {
        return null;
    }

}
