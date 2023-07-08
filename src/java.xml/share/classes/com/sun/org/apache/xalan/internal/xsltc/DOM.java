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

package java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc;


import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import java.xml.share.classes.com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import java.util.Map;
import java.xml.share.classes.com.sun.org.w3c.dom.Node;
import java.xml.share.classes.com.sun.org.w3c.dom.Nodejava.util.java.util.java.util.List;















/**
 */
public interface DOM {
    int  FIRST_TYPE             = 0;

    int  NO_TYPE                = -1;

    // 0 is reserved for NodeIterator.END
    int NULL     = 0;

    // used by some node iterators to know which node to return
    int RETURN_CURRENT = 0;
    int RETURN_PARENT  = 1;

    // Constants used by getResultTreeFrag to indicate the types of the RTFs.
    int SIMPLE_RTF   = 0;
    int ADAPTIVE_RTF = 1;
    int TREE_RTF     = 2;

    /** returns singleton iterator containg the document root */
    DTMAxisIterator getIterator();
    String getStringValue();

    DTMAxisIterator getChildren(final int node);
    DTMAxisIterator getTypedChildren(final int type);
    DTMAxisIterator getAxisIterator(final int axis);
    DTMAxisIterator getTypedAxisIterator(final int axis, final int type);
    DTMAxisIterator getNthDescendant(int node, int n, boolean includeself);
    DTMAxisIterator getNamespaceAxisIterator(final int axis, final int ns);
    DTMAxisIterator getNodeValueIterator(DTMAxisIterator iter, int returnType,
                                             String value, boolean op);
    DTMAxisIterator orderNodes(DTMAxisIterator source, int node);
    String getNodeName(final int node);
    String getNodeNameX(final int node);
    String getNamespaceName(final int node);
    int getExpandedTypeID(final int node);
    int getNamespaceType(final int node);
    int getParent(final int node);
    int getAttributeNode(final int gType, final int element);
    String getStringValueX(final int node);
    void copy(final int node, SerializationHandler handler)
        throws TransletException;
    void copy(DTMAxisIterator nodes, SerializationHandler handler)
        throws TransletException;
    String shallowCopy(final int node, SerializationHandler handler)
        throws TransletException;
    boolean lessThan(final int node1, final int node2);
    void characters(final int textNode, SerializationHandler handler)
        throws TransletException;
    Node makeNode(int index);
    Node makeNode(DTMAxisIterator iter);
    NodeList makeNodeList(int index);
    NodeList makeNodeList(DTMAxisIterator iter);
    String getLanguage(int node);
    int getSize();
    String getDocumentURI(int node);
    void setFilter(StripFilter filter);
    void setupMapping(String[] names, String[] urisArray, int[] typesArray, String[] namespaces);
    boolean isElement(final int node);
    boolean isAttribute(final int node);
    String lookupNamespace(int node, String prefix)
        throws TransletException;
    int getNodeIdent(final int nodehandle);
    int getNodeHandle(final int nodeId);
    DOM getResultTreeFrag(int initialSize, int rtfType);
    DOM getResultTreeFrag(int initialSize, int rtfType, boolean addToDTMManager);
    SerializationHandler getOutputDomBuilder();
    int getNSType(int node);
    int getDocument();
    String getUnparsedEntityURI(String name);
    Map<String, Integer> getElementsWithIDs();
    void release();
}
