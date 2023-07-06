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

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dom.DOMCryptoContext;
import java.xml.crypto.share.classes.org.w3c.dom.Node;
import java.util.java.util.java.util.java.util.List;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * DOM-based abstract implementation of XMLStructure.
 *
 */
public abstract class DOMStructure implements XMLStructure {

    public final boolean isFeatureSupported(String feature) {
        if (feature == null) {
            throw new NullPointerException();
        } else {
            return false;
        }
    }

    public abstract void marshal(Node parent, String dsPrefix,
        DOMCryptoContext context) throws MarshalException;

    protected boolean equalsContent(List<XMLStructure> content, List<XMLStructure> otherContent) {
        int size = content.size();
        if (size != otherContent.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            XMLStructure oxs = otherContent.get(i);
            XMLStructure xs = content.get(i);
            if (oxs instanceof javax.xml.crypto.dom.DOMStructure) {
                if (!(xs instanceof javax.xml.crypto.dom.DOMStructure)) {
                    return false;
                }
                Node otherNode = ((javax.xml.crypto.dom.DOMStructure)oxs).getNode();
                Node node = ((javax.xml.crypto.dom.DOMStructure)xs).getNode();
                if (!DOMUtils.nodesEqual(node, otherNode)) {
                    return false;
                }
            } else {
                if (!(xs.equals(oxs))) {
                    return false;
                }
            }
        }

        return true;
    }
}
