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


import javax.xml.transform.TransformerException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.dtm.DTM;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.I18n;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.utils.QName;
import java.xml.crypto.share.classes.com.sun.org.apache.xpath.internal.NodeSetDTM;
import java.xml.crypto.share.classes.com.sun.org.apache.xpath.internal.XPathContext;
import java.xml.crypto.share.classes.com.sun.org.apache.xpath.internal.functions.Function;
import java.xml.crypto.share.classes.com.sun.org.apache.xpath.internal.objects.XNodeSet;
import java.xml.crypto.share.classes.com.sun.org.apache.xpath.internal.objects.XObject;
import java.xml.crypto.share.classes.com.sun.org.apache.xpath.internal.res.XPATHErrorResources;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Node;
import java.util.java.util.java.util.java.util.List;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */





/**
 * The 'here()' function returns a node-set containing the attribute or
 * processing instruction node or the parent element of the text node
 * that directly bears the XPath expression.  This expression results
 * in an error if the containing XPath expression does not appear in the
 * same XML document against which the XPath expression is being evaluated.
 *
 * Mainpart is stolen from FuncId.java
 *
 * This does crash under Xalan2.2.D7 and works under Xalan2.2.D9
 *
 * To get this baby to work, a special trick has to be used. The function needs
 * access to the Node where the XPath expression has been defined. This is done
 * by constructing a {@link FuncHere} which has this Node as 'owner'.
 *
 * @see "http://www.w3.org/Signature/Drafts/xmldsig-core/Overview.html#function-here"
 */
public class FuncHere extends Function {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * The here function returns a node-set containing the attribute or
     * processing instruction node or the parent element of the text node
     * that directly bears the XPath expression.  This expression results
     * in an error if the containing XPath expression does not appear in the
     * same XML document against which the XPath expression is being evaluated.
     *
     * @param xctxt
     * @return the xobject
     * @throws javax.xml.transform.TransformerException
     */
    public XObject execute(XPathContext xctxt) throws TransformerException {

        Node xpathOwnerNode = (Node) xctxt.getOwnerObject();

        if (xpathOwnerNode == null) {
            return null;
        }

        int xpathOwnerNodeDTM = xctxt.getDTMHandleFromNode(xpathOwnerNode);

        int currentNode = xctxt.getCurrentNode();
        DTM dtm = xctxt.getDTM(currentNode);
        int docContext = dtm.getDocument();

        if (DTM.NULL == docContext) {
            error(xctxt, XPATHErrorResources.ER_CONTEXT_HAS_NO_OWNERDOC, null);
        }

        {
            // check whether currentNode and the node containing the XPath expression
            // are in the same document
            Document currentDoc =
                XMLUtils.getOwnerDocument(dtm.getNode(currentNode));
            Document xpathOwnerDoc = XMLUtils.getOwnerDocument(xpathOwnerNode);

            if (currentDoc != xpathOwnerDoc) {
                throw new TransformerException(I18n.translate("xpath.funcHere.documentsDiffer"));
            }
        }

        XNodeSet nodes = new XNodeSet(xctxt.getDTMManager());
        NodeSetDTM nodeSet = nodes.mutableNodeset();

        {
            int hereNode = DTM.NULL;

            switch (dtm.getNodeType(xpathOwnerNodeDTM)) {

            case Node.ATTRIBUTE_NODE :
            case Node.PROCESSING_INSTRUCTION_NODE : {
                // returns a node-set containing the attribute /  processing instruction node
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
        }

        /** $todo$ Do I have to do this detach() call? */
        nodeSet.detach();

        return nodes;
    }

    /**
     * No arguments to process, so this does nothing.
     * @param vars
     * @param globalsSize
     */
    public void fixupVariables(List<QName> vars, int globalsSize) { //NOPMD
        // do nothing
    }
}
