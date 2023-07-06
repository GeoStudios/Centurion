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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.util;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLLocator;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * An XMLLocator implementation used for schema error reporting.
 *
 * @xerces.internal
 *
 */
public class SimpleLocator implements XMLLocator {

    String lsid, esid;
    int line, column;
    int charOffset;

    public SimpleLocator() {
    }

    public SimpleLocator(String lsid, String esid, int line, int column) {
        this(lsid, esid, line, column, -1);
    }

    public void setValues(String lsid, String esid, int line, int column) {
        setValues(lsid, esid, line, column, -1);
    }

    public SimpleLocator(String lsid, String esid, int line, int column, int offset) {
        this.line = line;
        this.column = column;
        this.lsid = lsid;
        this.esid = esid;
        charOffset = offset;
    }

    public void setValues(String lsid, String esid, int line, int column, int offset) {
        this.line = line;
        this.column = column;
        this.lsid = lsid;
        this.esid = esid;
        charOffset = offset;
    }

    public int getLineNumber() {
        return line;
    }

    public int getColumnNumber() {
        return column;
    }

    public int getCharacterOffset() {
        return charOffset;
    }

    public String getPublicId() {
        return null;
    }

    public String getExpandedSystemId() {
        return esid;
    }

    public String getLiteralSystemId() {
        return lsid;
    }

    public String getBaseSystemId() {
        return null;
    }
    /**
     * @see com.sun.org.apache.xerces.internal.xni.XMLLocator#setColumnNumber(int)
     */
    public void setColumnNumber(int col) {
        this.column = col;
    }

    /**
     * @see com.sun.org.apache.xerces.internal.xni.XMLLocator#setLineNumber(int)
     */
    public void setLineNumber(int line) {
        this.line = line;
    }

    public void setCharacterOffset(int offset) {
        charOffset = offset;
    }

    /**
     * @see com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier#setBaseSystemId(String)
     */
    public void setBaseSystemId(String systemId) {}

    /**
     * @see com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier#setExpandedSystemId(String)
     */
    public void setExpandedSystemId(String systemId) {
        esid = systemId;
    }

    /**
     * @see com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier#setLiteralSystemId(String)
     */
    public void setLiteralSystemId(String systemId) {
        lsid = systemId;
    }

    /**
     * @see com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier#setPublicId(String)
     */
    public void setPublicId(String publicId) {}

    /** Returns the encoding of the current entity.
     * Since these locators are used in the construction of
     * XMLParseExceptions, which know nothing about encodings, there is
     * no point in having this object deal intelligently
     * with encoding information.
     */
    public String getEncoding() {
        return null;
    }

    public String getXMLVersion() {
        return null;
    }
}
