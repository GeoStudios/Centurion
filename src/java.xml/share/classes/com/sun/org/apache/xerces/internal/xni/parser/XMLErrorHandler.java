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


import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XNIException;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * An interface for handling errors. If the application is interested
 * in error notifications, then it can register an error handler object
 * that implements this interface with the parser configuration.
 *
 * @see XMLParserConfiguration
 *
 *
 */
public interface XMLErrorHandler {

    //
    // XMLErrorHandler methods
    //

    /**
     * Reports a warning. Warnings are non-fatal and can be safely ignored
     * by most applications.
     *
     * @param domain    The domain of the warning. The domain can be any
     *                  string but is suggested to be a valid URI. The
     *                  domain can be used to conveniently specify a web
     *                  site location of the relevent specification or
     *                  document pertaining to this warning.
     * @param key       The warning key. This key can be any string and
     *                  is implementation dependent.
     * @param exception Exception.
     *
     * @throws XNIException Thrown to signal that the parser should stop
     *                      parsing the document.
     */
    void warning(String domain, String key,
                        XMLParseException exception) throws XNIException;

    /**
     * Reports an error. Errors are non-fatal and usually signify that the
     * document is invalid with respect to its grammar(s).
     *
     * @param domain    The domain of the error. The domain can be any
     *                  string but is suggested to be a valid URI. The
     *                  domain can be used to conveniently specify a web
     *                  site location of the relevent specification or
     *                  document pertaining to this error.
     * @param key       The error key. This key can be any string and
     *                  is implementation dependent.
     * @param exception Exception.
     *
     * @throws XNIException Thrown to signal that the parser should stop
     *                      parsing the document.
     */
    void error(String domain, String key,
                      XMLParseException exception) throws XNIException;

    /**
     * Report a fatal error. Fatal errors usually occur when the document
     * is not well-formed and signifies that the parser cannot continue
     * normal operation.
     * <p>
     * <strong>Note:</strong> The error handler should <em>always</em>
     * throw an <code>XNIException</code> from this method. This exception
     * can either be the same exception that is passed as a parameter to
     * the method or a new XNI exception object. If the registered error
     * handler fails to throw an exception, the continuing operation of
     * the parser is undetermined.
     *
     * @param domain    The domain of the fatal error. The domain can be
     *                  any string but is suggested to be a valid URI. The
     *                  domain can be used to conveniently specify a web
     *                  site location of the relevent specification or
     *                  document pertaining to this fatal error.
     * @param key       The fatal error key. This key can be any string
     *                  and is implementation dependent.
     * @param exception Exception.
     *
     * @throws XNIException Thrown to signal that the parser should stop
     *                      parsing the document.
     */
    void fatalError(String domain, String key,
                           XMLParseException exception) throws XNIException;

} // interface XMLErrorHandler
