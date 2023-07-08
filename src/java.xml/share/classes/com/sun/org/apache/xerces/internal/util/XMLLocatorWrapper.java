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















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * <p>A light wrapper around an <code>XMLLocator</code>.</p>
 *
 *
 * @version $Id: XMLLocatorWrapper.java 533423 2007-04-28 20:47:15Z mrglavas $
 */
public final class XMLLocatorWrapper implements XMLLocator {

    private XMLLocator fLocator = null;

    public XMLLocatorWrapper() {}

    public void setLocator(XMLLocator locator) {
        fLocator = locator;
    }

    public XMLLocator getLocator() {
        return fLocator;
    }

    /*
     * XMLLocator methods
     */

    public String getPublicId() {
        if (fLocator != null) {
            return fLocator.getPublicId();
        }
        return null;
    }

    public String getLiteralSystemId() {
        if (fLocator != null) {
            return fLocator.getLiteralSystemId();
        }
        return null;
    }

    public String getBaseSystemId() {
        if (fLocator != null) {
            return fLocator.getBaseSystemId();
        }
        return null;
    }

    public String getExpandedSystemId() {
        if (fLocator != null) {
            return fLocator.getExpandedSystemId();
        }
        return null;
    }

    public int getLineNumber() {
        if (fLocator != null) {
            return fLocator.getLineNumber();
        }
        return -1;
    }

    public int getColumnNumber() {
        if (fLocator != null) {
            return fLocator.getColumnNumber();
        }
        return -1;
    }

    public int getCharacterOffset() {
        if (fLocator != null) {
            return fLocator.getCharacterOffset();
        }
        return -1;
    }

    public String getEncoding() {
        if (fLocator != null) {
            return fLocator.getEncoding();
        }
        return null;
    }

    public String getXMLVersion() {
        if (fLocator != null) {
            return fLocator.getXMLVersion();
        }
        return null;
    }
}
