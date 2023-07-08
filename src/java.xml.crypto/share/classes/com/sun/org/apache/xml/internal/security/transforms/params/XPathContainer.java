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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.transforms.params;


import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.transforms.TransformParam;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.Constants;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.SignatureElementProxy;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Node;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Text;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




/**
 * This Object serves both as namespace prefix resolver and as container for
 * the {@code ds:XPath} Element. It implements the {@link org.w3c.dom.Element} interface
 * and can be used directly in a DOM tree.
 *
 */
public class XPathContainer extends SignatureElementProxy implements TransformParam {

    /**
     * Constructor XPathContainer
     *
     * @param doc
     */
    public XPathContainer(Document doc) {
        super(doc);
    }

    /**
     * Sets the TEXT value of the {@code ds:XPath} Element.
     *
     * @param xpath
     */
    public void setXPath(String xpath) {
        Node childNode = getElement().getFirstChild();
        while (childNode != null) {
            Node nodeToBeRemoved = childNode;
            childNode = childNode.getNextSibling();
            getElement().removeChild(nodeToBeRemoved);
        }

        Text xpathText = createText(xpath);
        appendSelf(xpathText);
    }

    /**
     * Returns the TEXT value of the {@code ds:XPath} Element.
     *
     * @return the TEXT value of the {@code ds:XPath} Element.
     */
    public String getXPath() {
        return this.getTextFromTextChild();
    }

    /** {@inheritDoc} */
    public String getBaseLocalName() {
        return Constants._TAG_XPATH;
    }
}
