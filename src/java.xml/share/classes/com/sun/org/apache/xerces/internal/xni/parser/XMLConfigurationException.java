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


import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.Status;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XNIException;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * An XNI parser configuration exception. This exception class extends
 * <code>XNIException</code> in order to differentiate between general
 * parsing errors and configuration errors.
 *
 *
 */
public class XMLConfigurationException
    extends XNIException {

    /** Serialization version. */
    static final long serialVersionUID = -5437427404547669188L;

    //
    // Data
    //

    /** Exception type. */
    protected Status fType;

    /** Identifier. */
    protected String fIdentifier;

    //
    // Constructors
    //

    /**
     * Constructs a configuration exception with the specified type
     * and feature/property identifier.
     *
     * @param type       The type of the exception.
     * @param identifier The feature or property identifier.
     */
    public XMLConfigurationException(Status type, String identifier) {
        super(identifier);
        fType = type;
        fIdentifier = identifier;
    } // <init>(short,String)

    /**
     * Constructs a configuration exception with the specified type,
     * feature/property identifier, and error message
     *
     * @param type       The type of the exception.
     * @param identifier The feature or property identifier.
     * @param message    The error message.
     */
    public XMLConfigurationException(Status type, String identifier,
                                     String message) {
        super(message);
        fType = type;
        fIdentifier = identifier;
    } // <init>(short,String,String)

    //
    // Public methods
    //

    /**
     * Returns the exception type.
     */
    public Status getType() {
        return fType;
    } // getType():short

    /** Returns the feature or property identifier. */
    public String getIdentifier() {
        return fIdentifier;
    } // getIdentifier():String

} // class XMLConfigurationException
