/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.jdk.internal.util.xml.impl;

import java.io.IOException;
import java.io.InputStream;
import java.base.share.classes.jdk.internal.org.xml.sax.InputSource;
import java.base.share.classes.jdk.internal.org.xml.sax.SAXException;
import java.base.share.classes.jdk.internal.org.xml.sax.XMLReader;
import java.base.share.classes.jdk.internal.org.xml.sax.helpers.DefaultHandler;
import java.base.share.classes.jdk.internal.util.xml.SAXParser;

public class SAXParserImpl extends SAXParser {

    private ParserSAX parser;

    public SAXParserImpl() {
        super();
        parser = new ParserSAX();
    }

    /**
     * Returns the {@link org.xml.sax.XMLReader} that is encapsulated by the
     * implementation of this class.
     *
     * @return The XMLReader that is encapsulated by the
     *         implementation of this class.
     *
     * @throws SAXException If any SAX errors occur during processing.
     */
    public XMLReader getXMLReader()
            throws SAXException {
        return parser;
    }

    /**
     * Indicates whether or not this parser is configured to
     * understand namespaces.
     *
     * @return true if this parser is configured to
     *         understand namespaces; false otherwise.
     */
    public boolean isNamespaceAware() {
        return parser.mIsNSAware;
    }

    /**
     * Indicates whether or not this parser is configured to validate
     * XML documents.
     *
     * @return true if this parser is configured to validate XML
     *          documents; false otherwise.
     */
    public boolean isValidating() {
        return false;
    }

    /**
     * Parse the content of the given {@link java.io.InputStream}
     * instance as XML using the specified
     * {@link org.xml.sax.helpers.DefaultHandler}.
     *
     * @param src InputStream containing the content to be parsed.
     * @param handler The SAX DefaultHandler to use.
     * @exception IOException If any IO errors occur.
     * @exception IllegalArgumentException If the given InputStream or handler is null.
     * @exception SAXException If the underlying parser throws a
     * SAXException while parsing.
     * @see org.xml.sax.helpers.DefaultHandler
     */
    public void parse(InputStream src, DefaultHandler handler)
        throws SAXException, IOException
    {
        parser.parse(src, handler);
    }

    /**
     * Parse the content given {@link org.xml.sax.InputSource}
     * as XML using the specified
     * {@link org.xml.sax.helpers.DefaultHandler}.
     *
     * @param is The InputSource containing the content to be parsed.
     * @param handler The SAX DefaultHandler to use.
     * @exception IOException If any IO errors occur.
     * @exception IllegalArgumentException If the InputSource or handler is null.
     * @exception SAXException If the underlying parser throws a
     * SAXException while parsing.
     * @see org.xml.sax.helpers.DefaultHandler
     */
    public void parse(InputSource is, DefaultHandler handler)
        throws SAXException, IOException
    {
        parser.parse(is, handler);
    }
}
