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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.opti;


import java.xml.share.classes.com.sun.org.w3c.dom.Attr;
import java.xml.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.share.classes.com.sun.org.w3c.dom.NamedNodeMap;
import java.xml.share.classes.com.sun.org.w3c.dom.Node;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * @xerces.internal
 *
 *
 */
public class ElementImpl extends DefaultElement {

    SchemaDOM schemaDOM;
    Attr[] attrs;
    int row;
    int col;
    int parentRow;

    int line;
    int column;
    int charOffset;
    String fAnnotation;
    String fSyntheticAnnotation;

    public ElementImpl(int line, int column, int offset) {
        row = -1;
        col = -1;
        parentRow = -1;
        nodeType = Node.ELEMENT_NODE;

        this.line = line;
        this.column = column;
        charOffset = offset;
    }

    public ElementImpl(int line, int column) {
        this(line, column, -1);
    }


    public ElementImpl(String prefix, String localpart, String rawname,
            String uri, int line, int column, int offset) {
        super(prefix, localpart, rawname, uri, Node.ELEMENT_NODE);
        row = -1;
        col = -1;
        parentRow = -1;

        this.line = line;
        this.column = column;
        charOffset = offset;
    }

    public ElementImpl(String prefix, String localpart, String rawname,
            String uri, int line, int column) {
        this(prefix, localpart, rawname, uri, line, column, -1);
    }


    //
    // org.w3c.dom.Node methods
    //

    public Document getOwnerDocument() {
        return schemaDOM;
    }


    public Node getParentNode() {
        return schemaDOM.relations[row][0];
    }


    public boolean hasChildNodes() {
        return parentRow != -1;
    }


    public Node getFirstChild() {
        if (parentRow == -1) {
            return null;
        }
        return schemaDOM.relations[parentRow][1];
    }


    public Node getLastChild() {
        if (parentRow == -1) {
            return null;
        }
        int i=1;
        for (; i<schemaDOM.relations[parentRow].length; i++) {
            if (schemaDOM.relations[parentRow][i] == null) {
                return schemaDOM.relations[parentRow][i-1];
            }
        }
        if (i ==1) {
            i++;
        }
        return schemaDOM.relations[parentRow][i-1];
    }


    public Node getPreviousSibling() {
        if (col == 1) {
            return null;
        }
        return schemaDOM.relations[row][col-1];
    }


    public Node getNextSibling() {
        if (col == schemaDOM.relations[row].length-1) {
            return null;
        }
        return schemaDOM.relations[row][col+1];
    }


    public NamedNodeMap getAttributes() {
        return new NamedNodeMapImpl(attrs);
    }


    public boolean hasAttributes() {
        return (attrs.length != 0);
    }



    //
    // org.w3c.dom.Element methods
    //

    public String getTagName() {
        return rawname;
    }


    public String getAttribute(String name) {

        for (int i=0; i<attrs.length; i++) {
            if (attrs[i].getName().equals(name)) {
                return attrs[i].getValue();
            }
        }
        return "";
    }


    public Attr getAttributeNode(String name) {
        for (int i=0; i<attrs.length; i++) {
            if (attrs[i].getName().equals(name)) {
                return attrs[i];
            }
        }
        return null;
    }


    public String getAttributeNS(String namespaceURI, String localName) {
        for (int i=0; i<attrs.length; i++) {
            if (attrs[i].getLocalName().equals(localName) && nsEquals(attrs[i].getNamespaceURI(), namespaceURI)) {
                return attrs[i].getValue();
            }
        }
        return "";
    }


    public Attr getAttributeNodeNS(String namespaceURI, String localName) {
        for (int i=0; i<attrs.length; i++) {
            if (attrs[i].getName().equals(localName) && nsEquals(attrs[i].getNamespaceURI(), namespaceURI)) {
                return attrs[i];
            }
        }
        return null;
    }


    public boolean hasAttribute(String name) {
        for (int i=0; i<attrs.length; i++) {
            if (attrs[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }


    public boolean hasAttributeNS(String namespaceURI, String localName) {
        for (int i=0; i<attrs.length; i++) {
            if (attrs[i].getName().equals(localName) && nsEquals(attrs[i].getNamespaceURI(), namespaceURI)) {
                return true;
            }
        }
        return false;
    }


    public void setAttribute(String name, String value) {
        for (int i=0; i<attrs.length; i++) {
            if (attrs[i].getName().equals(name)) {
                attrs[i].setValue(value);
                return;
            }
        }
    }

    /** Returns the line number. */
    public int getLineNumber() {
        return line;
    }

    /** Returns the column number. */
    public int getColumnNumber() {
        return column;
    }

    /** Returns the character offset. */
    public int getCharacterOffset() {
        return charOffset;
    }

    public String getAnnotation() {
        return fAnnotation;
    }

    public String getSyntheticAnnotation() {
        return fSyntheticAnnotation;
    }

    /**
     * Compares two namespace URIs with an extra case for null entries
     */
    private static boolean nsEquals(String nsURI_1, String nsURI_2) {
        if (nsURI_1 == null) {
            return (nsURI_2 == null);
        }
        else {
            return nsURI_1.equals(nsURI_2);
        }
    }

}
