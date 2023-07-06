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

package java.xml.share.classes.com.sun.xml.internal.stream;


import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamReader;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;















/**
 *
 *
 * This class wraps XMLInputSource and is also capable of telling whether application
 * returned XMLStreamReader or not when XMLResolver.resolveEntity
 * was called.
 */
public class StaxXMLInputSource {

    XMLStreamReader fStreamReader ;
    XMLEventReader fEventReader ;
    XMLInputSource fInputSource ;

    //indicates if the source is created by a resolver
    boolean fIsCreatedByResolver = false;

    /** Creates a new instance of StaxXMLInputSource */
    public StaxXMLInputSource(XMLStreamReader streamReader, boolean byResolver) {
        fStreamReader = streamReader ;
    }

    /** Creates a new instance of StaxXMLInputSource */
    public StaxXMLInputSource(XMLEventReader eventReader, boolean byResolver) {
        fEventReader = eventReader ;
    }

    public StaxXMLInputSource(XMLInputSource inputSource, boolean byResolver){
        fInputSource = inputSource ;
        fIsCreatedByResolver = byResolver;
    }

    public XMLStreamReader getXMLStreamReader(){
        return fStreamReader ;
    }

    public XMLEventReader getXMLEventReader(){
        return fEventReader ;
    }

    public XMLInputSource getXMLInputSource(){
        return fInputSource ;
    }

    public boolean hasXMLStreamOrXMLEventReader(){
        return (fStreamReader != null) || (fEventReader != null);
    }

    public boolean isCreatedByResolver() {
        return fIsCreatedByResolver;
    }
}
