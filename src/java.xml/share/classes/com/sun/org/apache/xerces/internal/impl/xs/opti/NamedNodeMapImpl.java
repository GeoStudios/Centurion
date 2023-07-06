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


import java.xml.share.classes.com.sun.org.w3c.dom.Node;
import java.xml.share.classes.com.sun.org.w3c.dom.Attr;
import java.xml.share.classes.com.sun.org.w3c.dom.NamedNodeMap;
import java.xml.share.classes.com.sun.org.w3c.dom.DOMException;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */





/**
 * @xerces.internal
 *
 *
 */
public class NamedNodeMapImpl implements NamedNodeMap {

        Attr[] attrs;

        public NamedNodeMapImpl(Attr[] attrs) {
                this.attrs = attrs;
        }

        public Node getNamedItem(String name) {
                for (int i=0; i<attrs.length; i++) {
                        if (attrs[i].getName().equals(name)) {
                                return attrs[i];
                        }
                }
                return null;
        }

        public Node item(int index) {
                if (index < 0 && index > getLength()) {
                        return null;
                }
                return attrs[index];
        }

        public int getLength() {
                return attrs.length;
        }

        public Node getNamedItemNS(String namespaceURI, String localName) {
                for (int i=0; i<attrs.length; i++) {
                        if (attrs[i].getName().equals(localName) && attrs[i].getNamespaceURI().equals(namespaceURI)) {
                                return attrs[i];
                        }
                }
                return null;
        }

        public Node setNamedItemNS(Node arg) throws DOMException {
                throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
        }

        public Node setNamedItem(Node arg) throws DOMException {
                throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
        }

        public Node removeNamedItem(String name) throws DOMException {
                throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
        }

        public Node removeNamedItemNS(String namespaceURI, String localName) throws DOMException {
                throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Method not supported");
        }
}
