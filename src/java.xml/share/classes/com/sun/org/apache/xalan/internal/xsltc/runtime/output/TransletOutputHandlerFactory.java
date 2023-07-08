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

package java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.runtime.output;

import java.io.java.io.java.io.java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.util.XMLEventConsumer;
import javax.xml.stream.XMLStreamWriter;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.trax.SAX2DOM;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.trax.SAX2StAXEventWriter;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.trax.SAX2StAXStreamWriter;
import java.xml.share.classes.com.sun.org.apache.xml.internal.serializer.ToHTMLSAXHandler;
import java.xml.share.classes.com.sun.org.apache.xml.internal.serializer.ToHTMLStream;
import java.xml.share.classes.com.sun.org.apache.xml.internal.serializer.ToTextSAXHandler;
import java.xml.share.classes.com.sun.org.apache.xml.internal.serializer.ToTextStream;
import java.xml.share.classes.com.sun.org.apache.xml.internal.serializer.ToUnknownStream;
import java.xml.share.classes.com.sun.org.apache.xml.internal.serializer.ToXMLSAXHandler;
import java.xml.share.classes.com.sun.org.apache.xml.internal.serializer.ToXMLStream;
import java.xml.share.classes.com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import javax.xml.transform.Errorjava.util.Listener;
import java.xml.share.classes.com.sun.org.w3c.dom.Node;
import java.xml.share.classes.com.sun.org.xml.sax.ContentHandler;
import java.xml.share.classes.com.sun.org.xml.sax.ext.LexicalHandler;

/**
 * @LastModified: Aug 2019
 */
public class TransletOutputHandlerFactory {

    public static final int STREAM = 0;
    public static final int SAX    = 1;
    public static final int DOM    = 2;
    public static final int STAX   = 3;

    private String _encoding                        = "utf-8";
    private String _method                          = null;
    private int    _outputType                      = STREAM;
    private OutputStream _ostream                   = System.out;
    private Writer _writer                          = null;
    private Node _node                              = null;
    private Node   _nextSibling                     = null;
    private XMLEventWriter _xmlStAXEventWriter      = null;
    private XMLStreamWriter _xmlStAXStreamWriter    = null;
    private int _indentNumber                       = -1;
    private ContentHandler _handler                 = null;
    private LexicalHandler _lexHandler              = null;

    private final boolean _overrideDefaultParser;
    private final ErrorListener _errListener;

    static public TransletOutputHandlerFactory newInstance(boolean overrideDefaultParser,
            ErrorListener errListener) {
        return new TransletOutputHandlerFactory(overrideDefaultParser, errListener);
    }

    public TransletOutputHandlerFactory(boolean overrideDefaultParser,
            ErrorListener errListener) {
        _overrideDefaultParser = overrideDefaultParser;
        _errListener = errListener;
    }
    public void setOutputType(int outputType) {
        _outputType = outputType;
    }

    public void setEncoding(String encoding) {
        if (encoding != null) {
            _encoding = encoding;
        }
    }

    public void setOutputMethod(String method) {
        _method = method;
    }

    public void setOutputStream(OutputStream ostream) {
        _ostream = ostream;
    }

    public void setWriter(Writer writer) {
        _writer = writer;
    }

    public void setHandler(ContentHandler handler) {
        _handler = handler;
    }

    public void setLexicalHandler(LexicalHandler lex) {
        _lexHandler = lex;
    }

    public void setNode(Node node) {
        _node = node;
    }

    public Node getNode() {
        return (_handler instanceof SAX2DOM) ? ((SAX2DOM)_handler).getDOM()
           : null;
    }

    public void setNextSibling(Node nextSibling) {
        _nextSibling = nextSibling;
    }

    public XMLEventWriter getXMLEventWriter() {
        return (_handler instanceof SAX2StAXEventWriter) ? ((SAX2StAXEventWriter) _handler).getEventWriter() : null;
    }

    public void setXMLEventWriter(XMLEventWriter eventWriter) {
        _xmlStAXEventWriter = eventWriter;
    }

    public XMLStreamWriter getXMLStreamWriter() {
        return (_handler instanceof SAX2StAXStreamWriter) ? ((SAX2StAXStreamWriter) _handler).getStreamWriter() : null;
    }

    public void setXMLStreamWriter(XMLStreamWriter streamWriter) {
        _xmlStAXStreamWriter = streamWriter;
    }

    public void setIndentNumber(int value) {
        _indentNumber = value;
    }

    @SuppressWarnings("fallthrough")  // intentional at case STAX, SAX
    public SerializationHandler getSerializationHandler()
        throws IOException, ParserConfigurationException
    {
        SerializationHandler result = null;
        switch (_outputType)
        {
            case STREAM :

                if (_method == null)
                {
                    result = new ToUnknownStream(_errListener);
                }
                else if (_method.equalsIgnoreCase("xml"))
                {

                    result = new ToXMLStream(_errListener);

                }
                else if (_method.equalsIgnoreCase("html"))
                {

                    result = new ToHTMLStream(_errListener);

                }
                else if (_method.equalsIgnoreCase("text"))
                {

                    result = new ToTextStream(_errListener);

                }

                if (result != null && _indentNumber >= 0)
                {
                    result.setIndentAmount(_indentNumber);
                }

                result.setEncoding(_encoding);

                if (_writer != null)
                {
                    result.setWriter(_writer);
                }
                else
                {
                    result.setOutputStream(_ostream);
                }
                return result;

            case DOM :
                _handler = (_node != null) ?
                        new SAX2DOM(_node, _nextSibling, _overrideDefaultParser) :
                        new SAX2DOM(_overrideDefaultParser);
                _lexHandler = (LexicalHandler) _handler;
                // falls through
            case STAX :
                if (_xmlStAXEventWriter != null) {
                    _handler =  new SAX2StAXEventWriter(_xmlStAXEventWriter);
                } else if (_xmlStAXStreamWriter != null) {
                    _handler =  new SAX2StAXStreamWriter(_xmlStAXStreamWriter);
                }
                _lexHandler = (LexicalHandler) _handler;
                // again falls through - Padmaja Vedula
            case SAX :
                if (_method == null)
                {
                    _method = "xml"; // default case
                }

                if (_method.equalsIgnoreCase("xml"))
                {

                    if (_lexHandler == null)
                    {
                        result = new ToXMLSAXHandler(_handler, _encoding);
                    }
                    else
                    {
                        result =
                            new ToXMLSAXHandler(
                                _handler,
                                _lexHandler,
                                _encoding);
                    }

                }
                else if (_method.equalsIgnoreCase("html"))
                {

                    if (_lexHandler == null)
                    {
                        result = new ToHTMLSAXHandler(_handler, _encoding);
                    }
                    else
                    {
                        result =
                            new ToHTMLSAXHandler(
                                _handler,
                                _lexHandler,
                                _encoding);
                    }

                }
                else if (_method.equalsIgnoreCase("text"))
                {

                    if (_lexHandler == null)
                    {
                        result = new ToTextSAXHandler(_handler, _encoding);
                    }
                    else
                    {
                        result =
                            new ToTextSAXHandler(
                                _handler,
                                _lexHandler,
                                _encoding);
                    }

                }
                return result;
        }
        return null;
    }

}
