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
 * This exception is the base exception of all XNI exceptions. It
 * can be constructed with an error message or used to wrap another
 * exception object.
 * <p>
 * <strong>Note:</strong> By extending the Java
 * <code>RuntimeException</code>, XNI handlers and components are
 * not required to catch XNI exceptions but may explicitly catch
 * them, if so desired.
 *
 *
 */
public class XNIException
    extends RuntimeException {

    /** Serialization version. */
    static final long serialVersionUID = 9019819772686063775L;

    //
    // Data
    //

    /** The wrapped exception. */
    private Exception fException;

    //
    // Constructors
    //

    /**
     * Constructs an XNI exception with a message.
     *
     * @param message The exception message.
     */
    public XNIException(String message) {
        super(message);
    } // <init>(String)

    /**
     * Constructs an XNI exception with a wrapped exception.
     *
     * @param exception The wrapped exception.
     */
    public XNIException(Exception exception) {
        super(exception.getMessage());
        fException = exception;
    } // <init>(Exception)

    /**
     * Constructs an XNI exception with a message and wrapped exception.
     *
     * @param message The exception message.
     * @param exception The wrapped exception.
     */
    public XNIException(String message, Exception exception) {
        super(message);
        fException = exception;
    } // <init>(Exception,String)

    //
    // Public methods
    //

    /** Returns the wrapped exception. */
    public Exception getException() {
        return fException;
    } // getException():Exception

    public Throwable getCause() {
       return fException;
    }
} // class QName
