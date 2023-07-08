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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.xinclude;

import java.io.java.io.java.io.java.io.IOException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.util.XML11Char;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * This class is used for reading resources requested in &lt;include&gt; elements in
 * XML 1.1 entities, when the parse attribute of the &lt;include&gt; element is "text".
 * Using this class will open the location, detect the encoding, and discard the
 * byte order mark, if applicable.
 *
 *
 *
 * @see XIncludeHandler
 */
public class XInclude11TextReader
    extends XIncludeTextReader {

    /**
     * Construct the XIncludeReader using the XMLInputSource and XIncludeHandler.
     *
     * @param source The XMLInputSource to use.
     * @param handler The XIncludeHandler to use.
     * @param bufferSize The size of this text reader's buffer.
     */
    public XInclude11TextReader(XMLInputSource source, XIncludeHandler handler, int bufferSize)
        throws IOException {
        super(source, handler, bufferSize);
    }

    /**
     * Returns true if the specified character is a valid XML character
     * as per the rules of XML 1.1.
     *
     * @param ch The character to check.
     */
    protected boolean isValid(int ch) {
        return XML11Char.isXML11Valid(ch);
    }
}
