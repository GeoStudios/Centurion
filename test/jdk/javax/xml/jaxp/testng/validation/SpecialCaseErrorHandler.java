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

package validation;

import java.util.HashMap;
import java.util.Iterator;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SpecialCaseErrorHandler implements ErrorHandler {
    public static final boolean DEBUG = false;

    private HashMap errors;

    public SpecialCaseErrorHandler(String[] specialCases) {
        errors = new HashMap();
        for (int i = 0; i < specialCases.length; ++i) {
            errors.put(specialCases[i], Boolean.FALSE);
        }
    }

    public void reset() {
        for (Iterator iter = errors.keySet().iterator(); iter.hasNext();) {
            String error = (String) iter.next();
            errors.put(error, Boolean.FALSE);
        }
    }

    public void warning(SAXParseException arg0) throws SAXException {
        if (DEBUG) {
            System.err.println(arg0.getMessage());
        }
    }

    public void error(SAXParseException arg0) throws SAXException {
        if (DEBUG) {
            System.err.println(arg0.getMessage());
        }
        for (Iterator iter = errors.keySet().iterator(); iter.hasNext();) {
            String error = (String) iter.next();
            if (arg0.getMessage().startsWith(error)) {
                errors.put(error, Boolean.TRUE);
            }
        }
    }

    public void fatalError(SAXParseException arg0) throws SAXException {
        throw arg0;
    }

    public boolean specialCaseFound(String key) {
        return ((Boolean) errors.get(key)).booleanValue();
    }
}
