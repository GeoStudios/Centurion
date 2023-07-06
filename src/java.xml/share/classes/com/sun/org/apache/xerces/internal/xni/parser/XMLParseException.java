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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLLocator;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XNIException;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * A parsing exception. This exception is different from the standard
 * XNI exception in that it stores the location in the document (or
 * its entities) where the exception occurred.
 *
 *
 */
public class XMLParseException
    extends XNIException {

    /** Serialization version. */
    static final long serialVersionUID = 1732959359448549967L;

    //
    // Data
    //

    /** Public identifier. */
    protected String fPublicId;

    /** literal System identifier. */
    protected String fLiteralSystemId;

    /** expanded System identifier. */
    protected String fExpandedSystemId;

    /** Base system identifier. */
    protected String fBaseSystemId;

    /** Line number. */
    protected int fLineNumber = -1;

    /** Column number. */
    protected int fColumnNumber = -1;

    /** Character offset. */
    protected int fCharacterOffset = -1;

    //
    // Constructors
    //

    /** Constructs a parse exception. */
    public XMLParseException(XMLLocator locator, String message) {
        super(message);
        if (locator != null) {
            fPublicId = locator.getPublicId();
            fLiteralSystemId = locator.getLiteralSystemId();
            fExpandedSystemId = locator.getExpandedSystemId();
            fBaseSystemId = locator.getBaseSystemId();
            fLineNumber = locator.getLineNumber();
            fColumnNumber = locator.getColumnNumber();
            fCharacterOffset = locator.getCharacterOffset();
        }
    } // <init>(XMLLocator,String)

    /** Constructs a parse exception. */
    public XMLParseException(XMLLocator locator,
                             String message, Exception exception) {
        super(message, exception);
        if (locator != null) {
            fPublicId = locator.getPublicId();
            fLiteralSystemId = locator.getLiteralSystemId();
            fExpandedSystemId = locator.getExpandedSystemId();
            fBaseSystemId = locator.getBaseSystemId();
            fLineNumber = locator.getLineNumber();
            fColumnNumber = locator.getColumnNumber();
            fCharacterOffset = locator.getCharacterOffset();
        }
    } // <init>(XMLLocator,String,Exception)

    //
    // Public methods
    //

    /** Returns the public identifier. */
    public String getPublicId() {
        return fPublicId;
    } // getPublicId():String

    /** Returns the expanded system identifier. */
    public String getExpandedSystemId() {
        return fExpandedSystemId;
    } // getExpandedSystemId():String

    /** Returns the literal system identifier. */
    public String getLiteralSystemId() {
        return fLiteralSystemId;
    } // getLiteralSystemId():String

    /** Returns the base system identifier. */
    public String getBaseSystemId() {
        return fBaseSystemId;
    } // getBaseSystemId():String

    /** Returns the line number. */
    public int getLineNumber() {
        return fLineNumber;
    } // getLineNumber():int

    /** Returns the row number. */
    public int getColumnNumber() {
        return fColumnNumber;
    } // getRowNumber():int

    /** Returns the character offset. */
    public int getCharacterOffset() {
        return fCharacterOffset;
    } // getCharacterOffset():int

    //
    // Object methods
    //

    /** Returns a string representation of this object. */
    public String toString() {

        StringBuffer str = new StringBuffer();
        if (fPublicId != null) {
            str.append(fPublicId);
        }
        str.append(':');
        if (fLiteralSystemId != null) {
            str.append(fLiteralSystemId);
        }
        str.append(':');
        if (fExpandedSystemId != null) {
            str.append(fExpandedSystemId);
        }
        str.append(':');
        if (fBaseSystemId != null) {
            str.append(fBaseSystemId);
        }
        str.append(':');
        str.append(fLineNumber);
        str.append(':');
        str.append(fColumnNumber);
        str.append(':');
        str.append(fCharacterOffset);
        str.append(':');
        String message = getMessage();
        if (message == null) {
            Exception exception = getException();
            if (exception != null) {
                message = exception.getMessage();
            }
        }
        if (message != null) {
            str.append(message);
        }
        return str.toString();

    } // toString():String

} // XMLParseException
