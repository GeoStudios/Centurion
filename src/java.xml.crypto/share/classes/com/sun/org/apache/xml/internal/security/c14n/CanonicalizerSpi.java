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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.c14n;


import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.util.Set;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.parser.XMLParserException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Node;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




/**
 * Base class which all Canonicalization algorithms extend.
 *
 */
public abstract class CanonicalizerSpi {

    /**
     * Method canonicalize
     *
     * @param inputBytes
     * @param writer OutputStream to write the canonicalization result
     * @param secureValidation Whether secure validation is enabled
     *
     * @throws XMLParserException
     * @throws java.io.IOException
     * @throws javax.xml.parsers.ParserConfigurationException
     */
    public void engineCanonicalize(byte[] inputBytes, OutputStream writer, boolean secureValidation)
        throws XMLParserException, java.io.IOException, CanonicalizationException {

        Document document = null;
        try (java.io.InputStream bais = new ByteArrayInputStream(inputBytes)) {
            document = XMLUtils.read(bais, secureValidation);
        }
        this.engineCanonicalizeSubTree(document, writer);
    }

    /**
     * Returns the URI of this engine.
     * @return the URI
     */
    public abstract String engineGetURI();

    /**
     * C14n a nodeset
     *
     * @param xpathNodeSet
     * @param writer OutputStream to write the canonicalization result
     * @throws CanonicalizationException
     */
    public abstract void engineCanonicalizeXPathNodeSet(Set<Node> xpathNodeSet, OutputStream writer)
        throws CanonicalizationException;

    /**
     * C14n a nodeset
     *
     * @param xpathNodeSet
     * @param inclusiveNamespaces
     * @param writer OutputStream to write the canonicalization result
     * @throws CanonicalizationException
     */
    public abstract void engineCanonicalizeXPathNodeSet(
        Set<Node> xpathNodeSet, String inclusiveNamespaces, OutputStream writer
    ) throws CanonicalizationException;

    /**
     * C14n a node tree.
     *
     * @param rootNode
     * @param writer OutputStream to write the canonicalization result
     * @throws CanonicalizationException
     */
    public abstract void engineCanonicalizeSubTree(Node rootNode, OutputStream writer)
        throws CanonicalizationException;

    /**
     * C14n a node tree.
     *
     * @param rootNode
     * @param inclusiveNamespaces
     * @param writer OutputStream to write the canonicalization result
     * @throws CanonicalizationException
     */
    public abstract void engineCanonicalizeSubTree(Node rootNode, String inclusiveNamespaces, OutputStream writer)
        throws CanonicalizationException;

    /**
     * C14n a node tree.
     *
     * @param rootNode
     * @param inclusiveNamespaces
     * @param propagateDefaultNamespace If true the default namespace will be propagated to the c14n-ized root element
     * @param writer OutputStream to write the canonicalization result
     * @throws CanonicalizationException
     */
    public abstract void engineCanonicalizeSubTree(
            Node rootNode, String inclusiveNamespaces, boolean propagateDefaultNamespace, OutputStream writer)
            throws CanonicalizationException;


}
