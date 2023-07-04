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
package com.sun.org.apache.xml.internal.security.utils;

import javax.xml.XMLConstants;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFactoryConfigurationException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * An implementation for XPath evaluation that uses the JDK API.
 */
class JDKXPathAPI implements XPathAPI {

    private XPathFactory xpf;

    private String xpathStr;

    private XPathExpression xpathExpression;

    /**
     *  Use an XPath string to select a nodelist.
     *  XPath namespace prefixes are resolved from the namespaceNode.
     *
     *  @param contextNode The node to start searching from.
     *  @param xpathnode
     *  @param str
     *  @param namespaceNode The node from which prefixes in the XPath will be resolved to namespaces.
     *  @return A NodeIterator, should never be null.
     *
     * @throws TransformerException
     */
    public NodeList selectNodeList(
        Node contextNode, Node xpathnode, String str, Node namespaceNode
    ) throws TransformerException {
        if (!str.equals(xpathStr) || xpathExpression == null) {
            if (xpf == null) {
                xpf = XPathFactory.newInstance();
                try {
                    xpf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, Boolean.TRUE);
                } catch (XPathFactoryConfigurationException ex) {
                    throw new TransformerException(ex);
                }
            }
            XPath xpath = xpf.newXPath();
            xpath.setNamespaceContext(new DOMNamespaceContext(namespaceNode));
            xpathStr = str;
            try {
                xpathExpression = xpath.compile(xpathStr);
            } catch (XPathExpressionException ex) {
                throw new TransformerException(ex);
            }
        }
        try {
            return (NodeList)xpathExpression.evaluate(contextNode, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            throw new TransformerException(ex);
        }
    }

    /**
     * Evaluate an XPath string and return true if the output is to be included or not.
     *  @param contextNode The node to start searching from.
     *  @param xpathnode The XPath node
     *  @param str The XPath expression
     *  @param namespaceNode The node from which prefixes in the XPath will be resolved to namespaces.
     */
    public boolean evaluate(Node contextNode, Node xpathnode, String str, Node namespaceNode)
        throws TransformerException {
        if (!str.equals(xpathStr) || xpathExpression == null) {
            if (xpf == null) {
                xpf = XPathFactory.newInstance();
                try {
                    xpf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, Boolean.TRUE);
                } catch (XPathFactoryConfigurationException ex) {
                    throw new TransformerException(ex);
                }
            }
            XPath xpath = xpf.newXPath();
            xpath.setNamespaceContext(new DOMNamespaceContext(namespaceNode));
            xpathStr = str;
            try {
                xpathExpression = xpath.compile(xpathStr);
            } catch (XPathExpressionException ex) {
                throw new TransformerException(ex);
            }
        }
        try {
            return (Boolean)xpathExpression.evaluate(contextNode, XPathConstants.BOOLEAN);
        } catch (XPathExpressionException ex) {
            throw new TransformerException(ex);
        }
    }

    /**
     * Clear any context information from this object
     */
    public void clear() {
        xpathStr = null;
        xpathExpression = null;
        xpf = null;
    }

}
