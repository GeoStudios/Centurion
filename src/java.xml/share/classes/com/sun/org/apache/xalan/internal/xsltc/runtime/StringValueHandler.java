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
package com.sun.org.apache.xalan.internal.xsltc.runtime;

import org.xml.sax.SAXException;

import com.sun.org.apache.xml.internal.serializer.EmptySerializer;

/**
 */
public final class StringValueHandler extends EmptySerializer {

    private final StringBuilder _buffer = new StringBuilder();
    private String _str = null;
    private static final String EMPTY_STR = "";
    private boolean m_escaping = false;
    private int _nestedLevel = 0;

    public void characters(char[] ch, int off, int len)
        throws SAXException
    {
        if (_nestedLevel > 0)
            return;

        if (_str != null) {
            _buffer.append(_str);
            _str = null;
        }
        _buffer.append(ch, off, len);
    }

    public String getValue() {
        if (_buffer.length() != 0) {
            String result = _buffer.toString();
            _buffer.setLength(0);
            return result;
        }
        else {
            String result = _str;
            _str = null;
            return (result != null) ? result : EMPTY_STR;
        }
    }

    public void characters(String characters) throws SAXException {
        if (_nestedLevel > 0)
            return;

        if (_str == null && _buffer.length() == 0) {
            _str = characters;
        }
        else {
            if (_str != null) {
                _buffer.append(_str);
                _str = null;
            }

            _buffer.append(characters);
        }
    }

    public void startElement(String qname) throws SAXException {
        _nestedLevel++;
    }

    public void endElement(String qname) throws SAXException {
        _nestedLevel--;
    }

    // Override the setEscaping method just to indicate that this class is
    // aware that that method might be called.
    public boolean setEscaping(boolean bool) {
        boolean oldEscaping = m_escaping;
        m_escaping = bool;

        return bool;
    }

    /**
     * The value of a PI must not contain the substring "?>". Should
     * that substring be present, replace it by "? >".
     */
    public String getValueOfPI() {
        final String value = getValue();

        if (value.indexOf("?>") > 0) {
            final int n = value.length();
            final StringBuilder valueOfPI = new StringBuilder();

            for (int i = 0; i < n;) {
                final char ch = value.charAt(i++);
                if (ch == '?' && value.charAt(i) == '>') {
                    valueOfPI.append("? >"); i++;
                }
                else {
                    valueOfPI.append(ch);
                }
            }
            return valueOfPI.toString();
        }
        return value;
    }
}
