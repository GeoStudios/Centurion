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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.transforms;

import java.io.java.io.java.io.java.io.IOException;
import java.io.OutputStream;
import javax.xml.parsers.ParserConfigurationException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.c14n.CanonicalizationException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.c14n.InvalidCanonicalizerException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Element;
import java.xml.crypto.share.classes.com.sun.org.xml.sax.SAXException;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Base class which all Transform algorithms extend. The common methods that
 * have to be overridden are the
 * {@link #enginePerformTransform(XMLSignatureInput, OutputStream, Element, String, boolean)} method.
 *
 * Extensions of this class must be thread-safe.
 */
public abstract class TransformSpi {

    /**
     * The mega method which MUST be implemented by the Transformation Algorithm.
     *
     * @param input {@link XMLSignatureInput} as the input of transformation
     * @param os where to output this transformation.
     * @param transformElement the Transform element
     * @param baseURI The baseURI
     * @param secureValidation Whether secure validation is enabled
     * @return {@link XMLSignatureInput} as the result of transformation
     * @throws CanonicalizationException
     * @throws IOException
     * @throws InvalidCanonicalizerException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws TransformationException
     */
    protected abstract XMLSignatureInput enginePerformTransform(
        XMLSignatureInput input, OutputStream os, Element transformElement,
        String baseURI, boolean secureValidation
    ) throws IOException, CanonicalizationException, InvalidCanonicalizerException,
        TransformationException, ParserConfigurationException, SAXException;

    /**
     * Returns the URI representation of {@code Transformation algorithm}
     *
     * @return the URI representation of {@code Transformation algorithm}
     */
    protected abstract String engineGetURI();
}
