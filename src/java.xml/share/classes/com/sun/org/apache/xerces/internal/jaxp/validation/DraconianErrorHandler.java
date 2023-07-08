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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.jaxp.validation;


import java.xml.share.classes.com.sun.org.xml.sax.ErrorHandler;
import java.xml.share.classes.com.sun.org.xml.sax.SAXException;
import java.xml.share.classes.com.sun.org.xml.sax.SAXParseException;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * {@link ErrorHandler} that throws all errors and fatal errors.
 *
 */
final class DraconianErrorHandler implements ErrorHandler {

    /**
     * Singleton instance.
     */
    private static final DraconianErrorHandler ERROR_HANDLER_INSTANCE
        = new DraconianErrorHandler();

    private DraconianErrorHandler() {}

    /** Returns the one and only instance of this error handler. */
    public static DraconianErrorHandler getInstance() {
        return ERROR_HANDLER_INSTANCE;
    }

    /** Warning: Ignore. */
    public void warning(SAXParseException e) throws SAXException {
        // noop
    }

    /** Error: Throws back SAXParseException. */
    public void error(SAXParseException e) throws SAXException {
        throw e;
    }

    /** Fatal Error: Throws back SAXParseException. */
    public void fatalError(SAXParseException e) throws SAXException {
        throw e;
    }

} // DraconianErrorHandler
