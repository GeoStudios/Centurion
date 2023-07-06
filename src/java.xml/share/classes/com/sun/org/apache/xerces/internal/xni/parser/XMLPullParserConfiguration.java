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


import java.io.java.io.java.io.java.io.IOException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XNIException;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




/**
 * Represents a parser configuration that can be used as the
 * configuration for a "pull" parser. A pull parser allows the
 * application to drive the parser instead of having document
 * information events "pushed" to the registered handlers.
 * <p>
 * A pull parser using this type of configuration first calls
 * the <code>setInputSource</code> method. After the input
 * source is set, the pull parser repeatedly calls the
 * <code>parse(boolean):boolean</code> method. This method
 * returns a value of true if there is more to parse in the
 * document.
 * <p>
 * Calling the <code>parse(XMLInputSource)</code> is equivalent
 * to setting the input source and calling the
 * <code>parse(boolean):boolean</code> method with a "complete"
 * value of <code>true</code>.
 *
 *
 */
public interface XMLPullParserConfiguration
    extends XMLParserConfiguration {

    //
    // XMLPullParserConfiguration methods
    //

    // parsing

    /**
     * Sets the input source for the document to parse.
     *
     * @param inputSource The document's input source.
     *
     * @exception XMLConfigurationException Thrown if there is a
     *                        configuration error when initializing the
     *                        parser.
     * @exception IOException Thrown on I/O error.
     *
     * @see #parse(boolean)
     */
    void setInputSource(XMLInputSource inputSource)
        throws XMLConfigurationException, IOException;

    /**
     * Parses the document in a pull parsing fashion.
     *
     * @param complete True if the pull parser should parse the
     *                 remaining document completely.
     *
     * @return True if there is more document to parse.
     *
     * @exception XNIException Any XNI exception, possibly wrapping
     *                         another exception.
     * @exception IOException  An IO exception from the parser, possibly
     *                         from a byte stream or character stream
     *                         supplied by the parser.
     *
     * @see #setInputSource
     */
    boolean parse(boolean complete) throws XNIException, IOException;

    /**
     * If the application decides to terminate parsing before the xml document
     * is fully parsed, the application should call this method to free any
     * resource allocated during parsing. For example, close all opened streams.
     */
    void cleanup();

} // interface XMLPullParserConfiguration
