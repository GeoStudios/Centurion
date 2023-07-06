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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.util;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.util.SimpleLocator;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.jaxp.validation.WrappedSAXException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.QName;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLLocator;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLString;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource;
import java.xml.share.classes.com.sun.org.xml.sax.Attributes;
import java.xml.share.classes.com.sun.org.xml.sax.ContentHandler;
import java.xml.share.classes.com.sun.org.xml.sax.Locator;
import java.xml.share.classes.com.sun.org.xml.sax.SAXException;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Receves SAX {@link ContentHandler} events
 * and produces the equivalent {@link XMLDocumentHandler} events.
 *
 *     Kohsuke Kawaguchi
 */
public class SAX2XNI implements ContentHandler, XMLDocumentSource {
    public SAX2XNI( XMLDocumentHandler core ) {
        this.fCore = core;
    }

    private XMLDocumentHandler fCore;

    private final NamespaceSupport nsContext = new NamespaceSupport();
    private final SymbolTable symbolTable = new SymbolTable();

    public void setDocumentHandler(XMLDocumentHandler handler) {
        fCore = handler;
    }

    public XMLDocumentHandler getDocumentHandler() {
        return fCore;
    }

    //
    //
    // ContentHandler implementation
    //
    //
    public void startDocument() throws SAXException {
        try {
            nsContext.reset();

            XMLLocator xmlLocator;
            if(locator==null)
                // some SAX source doesn't provide a locator,
                // in which case we assume no line information is available
                // and use a dummy locator. With this, downstream components
                // can always assume that they will get a non-null Locator.
                xmlLocator=new SimpleLocator(null,null,-1,-1);
            else
                xmlLocator=new LocatorWrapper(locator);

            fCore.startDocument(
                    xmlLocator,
                    null,
                    nsContext,
                    null);
        } catch( WrappedSAXException e ) {
            throw e.exception;
        }
    }

    public void endDocument() throws SAXException {
        try {
            fCore.endDocument(null);
        } catch( WrappedSAXException e ) {
            throw e.exception;
        }
    }

    public void startElement( String uri, String local, String qname, Attributes att ) throws SAXException {
        try {
            fCore.startElement(createQName(uri,local,qname),createAttributes(att),null);
        } catch( WrappedSAXException e ) {
            throw e.exception;
        }
    }

    public void endElement( String uri, String local, String qname ) throws SAXException {
        try {
            fCore.endElement(createQName(uri,local,qname),null);
        } catch( WrappedSAXException e ) {
            throw e.exception;
        }
    }

    public void characters( char[] buf, int offset, int len ) throws SAXException {
        try {
            fCore.characters(new XMLString(buf,offset,len),null);
        } catch( WrappedSAXException e ) {
            throw e.exception;
        }
    }

    public void ignorableWhitespace( char[] buf, int offset, int len ) throws SAXException {
        try {
            fCore.ignorableWhitespace(new XMLString(buf,offset,len),null);
        } catch( WrappedSAXException e ) {
            throw e.exception;
        }
    }

    public void startPrefixMapping( String prefix, String uri ) {
        nsContext.pushContext();
        nsContext.declarePrefix(prefix,uri);
    }

    public void endPrefixMapping( String prefix ) {
        nsContext.popContext();
    }

    public void processingInstruction( String target, String data ) throws SAXException {
        try {
            fCore.processingInstruction(
                    symbolize(target),createXMLString(data),null);
        } catch( WrappedSAXException e ) {
            throw e.exception;
        }
    }

    public void skippedEntity( String name ) {
    }

    private Locator locator;
    public void setDocumentLocator( Locator _loc ) {
        this.locator = _loc;
    }

    /** Creates a QName object. */
    private QName createQName(String uri, String local, String raw) {

        int idx = raw.indexOf(':');

        if( local.length()==0 ) {
            // if naemspace processing is turned off, local could be "".
            // in that case, treat everything to be in the no namespace.
            uri = "";
            if(idx<0)
                local = raw;
            else
                local = raw.substring(idx+1);
        }

        String prefix;
        if (idx < 0)
            prefix = null;
        else
            prefix = raw.substring(0, idx);

        if (uri != null && uri.length() == 0)
            uri = null; // XNI uses null whereas SAX uses the empty string

        return new QName(symbolize(prefix), symbolize(local), symbolize(raw), symbolize(uri));
    }

    /** Symbolizes the specified string. */
    private String symbolize(String s) {
        if (s == null)
            return null;
        else
            return symbolTable.addSymbol(s);
    }

    private XMLString createXMLString(String str) {
        // with my patch
        // return new XMLString(str);

        // for now
        return new XMLString(str.toCharArray(), 0, str.length());
    }

    /** only one instance of XMLAttributes is used. */
    private final XMLAttributes xa = new XMLAttributesImpl();

    /** Creates an XMLAttributes object. */
    private XMLAttributes createAttributes(Attributes att) {
        xa.removeAllAttributes();
        int len = att.getLength();
        for (int i = 0; i < len; i++)
            xa.addAttribute(
                    createQName(att.getURI(i), att.getLocalName(i), att.getQName(i)),
                    att.getType(i),
                    att.getValue(i));
        return xa;
    }
}
