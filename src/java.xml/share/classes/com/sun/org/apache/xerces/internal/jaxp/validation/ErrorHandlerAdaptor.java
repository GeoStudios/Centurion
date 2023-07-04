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
package com.sun.org.apache.xerces.internal.jaxp.validation;

import com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParseException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

/**
 * Receives errors through Xerces {@link XMLErrorHandler}
 * and pass them down to SAX {@link ErrorHandler}.
 *
 *     Kohsuke Kawaguchi
 */
public abstract class ErrorHandlerAdaptor implements XMLErrorHandler
{
    /** set to true if there was any error. */
    private boolean hadError = false;

    /**
     * returns if there was an error since the last invocation of
     * the resetError method.
     */
    public boolean hadError() { return hadError; }
    /** resets the error flag. */
    public void reset() { hadError = false; }

    /**
     * Implemented by the derived class to return the actual
     * {@link ErrorHandler} to which errors are sent.
     *
     * @return always return non-null valid object.
     */
    protected abstract ErrorHandler getErrorHandler();

    public void fatalError( String domain, String key, XMLParseException e ) {
        try {
            hadError = true;
            getErrorHandler().fatalError( Util.toSAXParseException(e) );
        } catch( SAXException se ) {
            throw new WrappedSAXException(se);
        }
    }

    public void error( String domain, String key, XMLParseException e ) {
        try {
            hadError = true;
            getErrorHandler().error( Util.toSAXParseException(e) );
        } catch( SAXException se ) {
            throw new WrappedSAXException(se);
        }
    }

    public void warning( String domain, String key, XMLParseException e ) {
        try {
            getErrorHandler().warning( Util.toSAXParseException(e) );
        } catch( SAXException se ) {
            throw new WrappedSAXException(se);
        }
    }

}
