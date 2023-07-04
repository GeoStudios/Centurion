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
package com.sun.org.apache.xml.internal.security.c14n.helper;

import com.sun.org.apache.xml.internal.security.utils.Constants;
import org.w3c.dom.Attr;
import java.io.Serializable;
import java.util.Comparator;

/**
 * Compares two attributes based on the C14n specification.
 *
 * <UL>
 * <LI>Namespace nodes have a lesser document order position than attribute
 *   nodes.
 * <LI> An element's namespace nodes are sorted lexicographically by
 *   local name (the default namespace node, if one exists, has no
 *   local name and is therefore lexicographically least).
 * <LI> An element's attribute nodes are sorted lexicographically with
 *   namespace URI as the primary key and local name as the secondary
 *   key (an empty namespace URI is lexicographically least).
 * </UL>
 *
 */
public class AttrCompare implements Comparator<Attr>, Serializable {

    private static final long serialVersionUID = -7113259629930576230L;
    private static final int ATTR0_BEFORE_ATTR1 = -1;
    private static final int ATTR1_BEFORE_ATTR0 = 1;
    private static final String XMLNS = Constants.NamespaceSpecNS;

    /**
     * Compares two attributes based on the C14n specification.
     *
     * <UL>
     * <LI>Namespace nodes have a lesser document order position than
     *   attribute nodes.
     * <LI> An element's namespace nodes are sorted lexicographically by
     *   local name (the default namespace node, if one exists, has no
     *   local name and is therefore lexicographically least).
     * <LI> An element's attribute nodes are sorted lexicographically with
     *   namespace URI as the primary key and local name as the secondary
     *   key (an empty namespace URI is lexicographically least).
     * </UL>
     *
     * @param attr0
     * @param attr1
     * @return returns a negative integer, zero, or a positive integer as
     *   obj0 is less than, equal to, or greater than obj1
     *
     */
    public int compare(Attr attr0, Attr attr1) {
        String namespaceURI0 = attr0.getNamespaceURI();
        String namespaceURI1 = attr1.getNamespaceURI();

        boolean isNamespaceAttr0 = XMLNS.equals(namespaceURI0);
        boolean isNamespaceAttr1 = XMLNS.equals(namespaceURI1);

        if (isNamespaceAttr0) {
            if (isNamespaceAttr1) {
                // both are namespaces
                String localname0 = attr0.getLocalName();
                String localname1 = attr1.getLocalName();

                if ("xmlns".equals(localname0)) {
                    localname0 = "";
                }

                if ("xmlns".equals(localname1)) {
                    localname1 = "";
                }

                return localname0.compareTo(localname1);
            }
            // attr0 is a namespace, attr1 is not
            return ATTR0_BEFORE_ATTR1;
        } else if (isNamespaceAttr1) {
            // attr1 is a namespace, attr0 is not
            return ATTR1_BEFORE_ATTR0;
        }

        // none is a namespace
        if (namespaceURI0 == null) {
            if (namespaceURI1 == null) {
                String name0 = attr0.getName();
                String name1 = attr1.getName();
                return name0.compareTo(name1);
            }
            return ATTR0_BEFORE_ATTR1;
        } else if (namespaceURI1 == null) {
            return ATTR1_BEFORE_ATTR0;
        }

        int a = namespaceURI0.compareTo(namespaceURI1);
        if (a != 0) {
            return a;
        }

        return attr0.getLocalName().compareTo(attr1.getLocalName());
    }
}
