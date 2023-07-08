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

package java.xml.share.classes.com.sun.org.apache.xml.internal.serializer.dom3;

import java.xml.share.classes.com.sun.org.w3c.dom.DOMError;
import java.xml.share.classes.com.sun.org.w3c.dom.DOMLocator;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Implementation of the DOM Level 3 DOMError interface.
 *
 * <p>See also the <a href='http://www.w3.org/TR/2004/REC-DOM-Level-3-Core-20040407/core.html#ERROR-Interfaces-DOMError'>DOMError Interface definition from Document Object Model (DOM) Level 3 Core Specification</a>.
 *
 * @xsl.usage internal
 */

final class DOMErrorImpl implements DOMError {

    /** private data members */

    // The DOMError Severity
    private short fSeverity = DOMError.SEVERITY_WARNING;

    // The Error message
    private String fMessage = null;

    //  A String indicating which related data is expected in relatedData.
    private String fType;

    // The platform related exception
    private Exception fException = null;

    //
    private Object fRelatedData;

    // The location of the exception
    private DOMLocatorImpl fLocation = new DOMLocatorImpl();

    //
    // Constructors
    //

    /**
     * Default constructor.
     */
    DOMErrorImpl () {
    }

    /**
     * @param severity
     * @param message
     * @param type
     */
    DOMErrorImpl(short severity, String message, String type) {
        fSeverity = severity;
        fMessage = message;
        fType = type;
    }

    /**
     * @param severity
     * @param message
     * @param type
     * @param exception
     */
    DOMErrorImpl(short severity, String message, String type,
            Exception exception) {
        fSeverity = severity;
        fMessage = message;
        fType = type;
        fException = exception;
    }

    /**
     * @param severity
     * @param message
     * @param type
     * @param exception
     * @param relatedData
     * @param location
     */
    DOMErrorImpl(short severity, String message, String type,
            Exception exception, Object relatedData, DOMLocatorImpl location) {
        fSeverity = severity;
        fMessage = message;
        fType = type;
        fException = exception;
        fRelatedData = relatedData;
        fLocation = location;
    }

    /**
     * The severity of the error, either <code>SEVERITY_WARNING</code>,
     * <code>SEVERITY_ERROR</code>, or <code>SEVERITY_FATAL_ERROR</code>.
     *
     * @return A short containing the DOMError severity
     */
    public short getSeverity() {
        return fSeverity;
    }

    /**
     * The DOMError message string.
     *
     * @return String
     */
    public String getMessage() {
        return fMessage;
    }

    /**
     * The location of the DOMError.
     *
     * @return A DOMLocator object containing the DOMError location.
     */
    public DOMLocator getLocation() {
        return fLocation;
    }

    /**
     * The related platform dependent exception if any.
     *
     * @return A java.lang.Exception
     */
    public Object getRelatedException(){
        return fException;
    }

    /**
     * Returns a String indicating which related data is expected in relatedData.
     *
     * @return A String
     */
    public String getType(){
        return fType;
    }

    /**
     * The related DOMError.type dependent data if any.
     *
     * @return java.lang.Object
     */
    public Object getRelatedData(){
        return fRelatedData;
    }

    public void reset(){
        fSeverity = DOMError.SEVERITY_WARNING;
        fException = null;
        fMessage = null;
        fType = null;
        fRelatedData = null;
        fLocation = null;
    }

}// class DOMErrorImpl
