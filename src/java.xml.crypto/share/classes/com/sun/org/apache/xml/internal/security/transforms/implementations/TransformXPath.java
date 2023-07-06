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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.transforms.implementations;


import java.io.OutputStream;
import javax.xml.transform.TransformerException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityRuntimeException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.signature.NodeFilter;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.transforms.TransformSpi;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.transforms.TransformationException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.transforms.Transforms;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.Constants;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.XPathAPI;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.XPathFactory;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.DOMException;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Element;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Node;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */





/**
 * Class TransformXPath
 *
 * Implements the {@code http://www.w3.org/TR/1999/REC-xpath-19991116}
 * transform.
 *
 * @see <a href="http://www.w3.org/TR/1999/REC-xpath-19991116">XPath</a>
 *
 */
public class TransformXPath extends TransformSpi {

    /**
     * {@inheritDoc}
     */
    @Override
    protected String engineGetURI() {
        return Transforms.TRANSFORM_XPATH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected XMLSignatureInput enginePerformTransform(
        XMLSignatureInput input, OutputStream os, Element transformElement,
        String baseURI, boolean secureValidation
    ) throws TransformationException {
        try {
            /**
             * If the actual input is an octet stream, then the application MUST
             * convert the octet stream to an XPath node-set suitable for use by
             * Canonical XML with Comments. (A subsequent application of the
             * REQUIRED Canonical XML algorithm would strip away these comments.)
             *
             * ...
             *
             * The evaluation of this expression includes all of the document's nodes
             * (including comments) in the node-set representing the octet stream.
             */
            Element xpathElement =
                XMLUtils.selectDsNode(
                    transformElement.getFirstChild(), Constants._TAG_XPATH, 0);

            if (xpathElement == null) {
                Object[] exArgs = { "ds:XPath", "Transform" };

                throw new TransformationException("xml.WrongContent", exArgs);
            }
            Node xpathnode = xpathElement.getFirstChild();
            if (xpathnode == null) {
                throw new DOMException(
                    DOMException.HIERARCHY_REQUEST_ERR, "Text must be in ds:Xpath"
                );
            }
            String str = XMLUtils.getStrFromNode(xpathnode);
            input.setNeedsToBeExpanded(needsCircumvent(str));

            XPathFactory xpathFactory = getXPathFactory();
            XPathAPI xpathAPIInstance = xpathFactory.newXPathAPI();
            input.addNodeFilter(new XPathNodeFilter(xpathElement, xpathnode, str, xpathAPIInstance));
            input.setNodeSet(true);
            return input;
        } catch (DOMException ex) {
            throw new TransformationException(ex);
        }
    }

    protected XPathFactory getXPathFactory() {
        return XPathFactory.newInstance();
    }

    /**
     * @param str
     * @return true if needs to be circumvent for bug.
     */
    private boolean needsCircumvent(String str) {
        return str.indexOf("namespace") != -1 || str.indexOf("name()") != -1;
    }

    private static class XPathNodeFilter implements NodeFilter {

        private final XPathAPI xPathAPI;
        private final Node xpathnode;
        private final Element xpathElement;
        private final String str;

        XPathNodeFilter(Element xpathElement, Node xpathnode, String str, XPathAPI xPathAPI) {
            this.xpathnode = xpathnode;
            this.str = str;
            this.xpathElement = xpathElement;
            this.xPathAPI = xPathAPI;
        }

        /**
         * @see com.sun.org.apache.xml.internal.security.signature.NodeFilter#isNodeInclude(org.w3c.dom.Node)
         */
        public int isNodeInclude(Node currentNode) {
            try {
                boolean include = xPathAPI.evaluate(currentNode, xpathnode, str, xpathElement);
                if (include) {
                    return 1;
                }
                return 0;
            } catch (TransformerException e) {
                Object[] eArgs = {currentNode};
                throw new XMLSecurityRuntimeException("signature.Transform.node", eArgs, e);
            } catch (Exception e) {
                Object[] eArgs = {currentNode, currentNode.getNodeType()};
                throw new XMLSecurityRuntimeException("signature.Transform.nodeAndType",eArgs, e);
            }
        }

        public int isNodeIncludeDO(Node n, int level) {
            return isNodeInclude(n);
        }

    }
}
