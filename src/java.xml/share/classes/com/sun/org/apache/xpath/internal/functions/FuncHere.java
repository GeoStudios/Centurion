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

package java.xml.share.classes.com.sun.org.apache.xpath.internal.functions;

import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTM;
import java.xml.share.classes.com.sun.org.apache.xml.internal.utils.QName;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.NodeSetDTM;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.XPathContext;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.objects.XNodeSet;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.objects.XObject;
import java.xml.share.classes.com.sun.org.apache.xpath.internal.res.XPATHErrorResources;
import java.util.java.util.java.util.java.util.List;
import javax.xml.transform.TransformerException;
import java.xml.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.share.classes.com.sun.org.w3c.dom.Node;

/**
 * Execute the XML Signature here() function.
 *
 * @LastModified: Oct 2017
 */
public final class FuncHere extends Function {

    private static final long serialVersionUID = 4328660760070034592L;

    @Override
    public XObject execute(XPathContext xctxt) throws TransformerException {
        Node xpathOwnerNode = (Node)xctxt.getOwnerObject();
        if (xpathOwnerNode == null) {
            return null;
        }

        int xpathOwnerNodeDTM = xctxt.getDTMHandleFromNode(xpathOwnerNode);
        int currentNode = xctxt.getCurrentNode();
        DTM dtm = xctxt.getDTM(currentNode);
        int docContext = dtm.getDocument();

        if (docContext == DTM.NULL) {
            error(xctxt, XPATHErrorResources.ER_CONTEXT_HAS_NO_OWNERDOC, null);
        }

        // check whether currentNode and the node containing the XPath
        // expression are in the same document
        Document currentDoc = getOwnerDocument(dtm.getNode(currentNode));
        Document xpathOwnerDoc = getOwnerDocument(xpathOwnerNode);

        if (currentDoc != xpathOwnerDoc) {
            throw new TransformerException("Owner documents differ");
        }

        XNodeSet nodes = new XNodeSet(xctxt.getDTMManager());
        NodeSetDTM nodeSet = nodes.mutableNodeset();

        int hereNode = DTM.NULL;

        switch (dtm.getNodeType(xpathOwnerNodeDTM)) {

            case Node.ATTRIBUTE_NODE:
            case Node.PROCESSING_INSTRUCTION_NODE: {
                // returns a node-set containing the attribute /  processing
                // instruction node
                hereNode = xpathOwnerNodeDTM;
                nodeSet.addNode(hereNode);
                break;
            }
            case Node.TEXT_NODE : {
                // returns a node-set containing the parent element of the
                // text node that directly bears the XPath expression
                hereNode = dtm.getParent(xpathOwnerNodeDTM);
                nodeSet.addNode(hereNode);
                break;
            }
            default :
                break;
        }

        /** $todo$ Do I have to do this detach() call? */
        nodeSet.detach();

        return nodes;
    }

    private static Document getOwnerDocument(Node node) {
        if (node.getNodeType() == Node.DOCUMENT_NODE) {
            return (Document)node;
        }
        return node.getOwnerDocument();
    }

    @Override
    public void fixupVariables(List<QName> vars, int globalsSize) { }
}
