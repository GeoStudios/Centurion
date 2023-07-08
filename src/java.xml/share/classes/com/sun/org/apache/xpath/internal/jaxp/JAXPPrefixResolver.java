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

package java.xml.share.classes.com.sun.org.apache.xpath.internal.jaxp;


import java.xml.share.classes.com.sun.org.w3c.dom.Node;
import java.xml.share.classes.com.sun.org.w3c.dom.NamedNodeMap;
import java.xml.share.classes.com.sun.org.apache.xml.internal.utils.PrefixResolver;
import javax.xml.namespace.NamespaceContext;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




/**
 * <meta name="usage" content="general"/>
 * This class implements a Default PrefixResolver which
 * can be used to perform prefix-to-namespace lookup
 * for the XPath object.
 * This class delegates the resolution to the passed NamespaceContext
 */
public class JAXPPrefixResolver implements PrefixResolver
{

    private final NamespaceContext namespaceContext;


    public JAXPPrefixResolver ( NamespaceContext nsContext ) {
        this.namespaceContext = nsContext;
    }


    public String getNamespaceForPrefix( String prefix ) {
        return namespaceContext.getNamespaceURI( prefix );
    }

    /**
     * Return the base identifier.
     *
     * @return null
     */
    public String getBaseIdentifier() {
        return null;
    }

    /**
     * @see PrefixResolver#handlesNullPrefixes()
     */
    public boolean handlesNullPrefixes() {
        return false;
    }


    /**
     * The URI for the XML namespace.
     * (Duplicate of that found in com.sun.org.apache.xpath.internal.XPathContext).
     */

    public static final String S_XMLNAMESPACEURI =
        "http://www.w3.org/XML/1998/namespace";


    /**
     * Given a prefix and a Context Node, get the corresponding namespace.
     * Warning: This will not work correctly if namespaceContext
     * is an attribute node.
     * @param prefix Prefix to resolve.
     * @param namespaceContext Node from which to start searching for a
     * xmlns attribute that binds a prefix to a namespace.
     * @return Namespace that prefix resolves to, or null if prefix
     * is not bound.
     */
    public String getNamespaceForPrefix(String prefix,
                                      org.w3c.dom.Node namespaceContext) {
        Node parent = namespaceContext;
        String namespace = null;

        if (prefix.equals("xml")) {
            namespace = S_XMLNAMESPACEURI;
        } else {
            int type;

            while ((null != parent) && (null == namespace)
                && (((type = parent.getNodeType()) == Node.ELEMENT_NODE)
                    || (type == Node.ENTITY_REFERENCE_NODE))) {

                if (type == Node.ELEMENT_NODE) {
                    NamedNodeMap nnm = parent.getAttributes();

                    for (int i = 0; i < nnm.getLength(); i++) {
                        Node attr = nnm.item(i);
                        String aname = attr.getNodeName();
                        boolean isPrefix = aname.startsWith("xmlns:");

                        if (isPrefix || aname.equals("xmlns")) {
                            int index = aname.indexOf(':');
                            String p =isPrefix ?aname.substring(index + 1) :"";

                            if (p.equals(prefix)) {
                                namespace = attr.getNodeValue();
                                break;
                            }
                        }
                    }
                }

                parent = parent.getParentNode();
            }
        }
        return namespace;
    }

}
