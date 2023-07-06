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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.dom;

import java.xml.share.classes.com.sun.org.w3c.dom.DOMError;
import java.xml.share.classes.com.sun.org.w3c.dom.DOMLocator;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLParseException;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * <code>DOMErrorImpl</code> is an implementation that describes an error.
 * <strong>Note:</strong> The error object that describes the error
 * might be reused by Xerces implementation, across multiple calls to the
 * handleEvent method on DOMErrorHandler interface.
 *
 *
 * <p>See also the <a href='http://www.w3.org/TR/2001/WD-DOM-Level-3-Core-20010913'>Document Object Model (DOM) Level 3 Core Specification</a>.
 *
 * @xerces.internal
 *
 *
 */

// REVISIT: the implementation of ErrorReporter.
//          we probably should not pass XMLParseException
//

public class DOMErrorImpl implements DOMError {

    //
    // Data
    //

    public short fSeverity = DOMError.SEVERITY_WARNING;
    public String fMessage = null;
    public DOMLocatorImpl fLocator = new DOMLocatorImpl();
    public Exception fException = null;
    public String fType;
    public Object fRelatedData;

    //
    // Constructors
    //

    /** Default constructor. */
    public DOMErrorImpl () {
    }

    /** Exctracts information from XMLParserException) */
    public DOMErrorImpl (short severity, XMLParseException exception) {
        fSeverity = severity;
        fException = exception;
        fLocator = createDOMLocator (exception);
    }

    /**
     * The severity of the error, either <code>SEVERITY_WARNING</code>,
     * <code>SEVERITY_ERROR</code>, or <code>SEVERITY_FATAL_ERROR</code>.
     */

    public short getSeverity() {
        return fSeverity;
    }

    /**
     * An implementation specific string describing the error that occured.
     */

    public String getMessage() {
        return fMessage;
    }

    /**
     * The location of the error.
     */

    public DOMLocator getLocation() {
        return fLocator;
    }

    // method to get the DOMLocator Object
    private DOMLocatorImpl createDOMLocator(XMLParseException exception) {
        // assuming DOMLocator wants the *expanded*, not the literal, URI of the doc... - neilg
        return new DOMLocatorImpl(exception.getLineNumber(),
                                  exception.getColumnNumber(),
                                  exception.getCharacterOffset(),
                                  exception.getExpandedSystemId());
    } // createDOMLocator()

    /**
     * The related platform dependent exception if any.exception is a reserved
     * word, we need to rename it.Change to "relatedException". (F2F 26 Sep
     * 2001)
     */
    public Object getRelatedException(){
        return fException;
    }

    public void reset(){
        fSeverity = DOMError.SEVERITY_WARNING;
        fException = null;
    }

    public String getType(){
        return fType;
    }

    public Object getRelatedData(){
        return fRelatedData;
    }

}// class DOMErrorImpl
