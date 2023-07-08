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
import java.xml.share.classes.com.sun.org.w3c.dom.DOMErrorHandler;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * This is the default implementation of the ErrorHandler interface and is
 * used if one is not provided.  The default implementation simply reports
 * DOMErrors to System.err.
 *
 * @xsl.usage internal
 */
final class DOMErrorHandlerImpl implements DOMErrorHandler {

    /**
     * Default Constructor
     */
    DOMErrorHandlerImpl() {
    }

    /**
     * Implementation of DOMErrorHandler.handleError that
     * adds copy of error to list for later retrieval.
     *
     */
    public boolean handleError(DOMError error) {
        boolean fail = true;
        String severity = null;
        if (error.getSeverity() == DOMError.SEVERITY_WARNING) {
            fail = false;
            severity = "[Warning]";
        } else if (error.getSeverity() == DOMError.SEVERITY_ERROR) {
            severity = "[Error]";
        } else if (error.getSeverity() == DOMError.SEVERITY_FATAL_ERROR) {
            severity = "[Fatal Error]";
        }

        System.err.println(severity + ": " + error.getMessage() + "\t");
        System.err.println("Type : " + error.getType() + "\t" + "Related Data: "
                + error.getRelatedData() + "\t" + "Related Exception: "
                + error.getRelatedException() );

        return fail;
    }
}
