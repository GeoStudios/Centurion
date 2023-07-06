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

package java.xml.crypto.share.classes.org.jcp.xml.dsig.internal.dom;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.java.io.java.io.java.io.IOException;
import java.util.*;
import javax.xml.crypto.XMLCryptoContext;
import java.xml.crypto.share.classes.org.w3c.dom.NamedNodeMap;
import java.xml.crypto.share.classes.org.w3c.dom.Node;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * Miscellaneous static utility methods for use in JSR 105 RI.
 *
 */
public final class Utils {

    private Utils() {}

    public static byte[] readBytesFromStream(InputStream is)
        throws IOException
    {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            while (true) {
                int read = is.read(buf);
                if (read == -1) { // EOF
                    break;
                }
                baos.write(buf, 0, read);
                if (read < 1024) {
                    break;
                }
            }
            return baos.toByteArray();
        }
    }

    /**
     * Converts an Iterator to a Set of Nodes, according to the XPath
     * Data Model.
     *
     * @param i the Iterator
     * @return the Set of Nodes
     */
    static Set<Node> toNodeSet(Iterator<?> i) {
        Set<Node> nodeSet = new HashSet<>();
        while (i.hasNext()) {
            Node n = (Node)i.next();
            nodeSet.add(n);
            // insert attributes nodes to comply with XPath
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                NamedNodeMap nnm = n.getAttributes();
                for (int j = 0, length = nnm.getLength(); j < length; j++) {
                    nodeSet.add(nnm.item(j));
                }
            }
        }
        return nodeSet;
    }

    /**
     * Returns the ID from a same-document URI (ex: "#id")
     */
    public static String parseIdFromSameDocumentURI(String uri) {
        if (uri.length() == 0) {
            return null;
        }
        String id = uri.substring(1);
        if (id.startsWith("xpointer(id(")) {
            int i1 = id.indexOf('\'');
            int i2 = id.indexOf('\'', i1+1);
            id = id.substring(i1+1, i2);
        }
        return id;
    }

    /**
     * Returns true if uri is a same-document URI, false otherwise.
     */
    public static boolean sameDocumentURI(String uri) {
        return uri != null && (uri.length() == 0 || uri.charAt(0) == '#');
    }

    static boolean secureValidation(XMLCryptoContext xc) {
        if (xc == null) {
            return false;
        }
        return getBoolean(xc, "org.jcp.xml.dsig.secureValidation");
    }

    private static boolean getBoolean(XMLCryptoContext xc, String name) {
        Boolean value = (Boolean)xc.getProperty(name);
        return value != null && value;
    }
}
