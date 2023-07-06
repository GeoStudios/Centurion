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

import java.math.BigInteger;
import javax.xml.crypto.*;
import javax.xml.crypto.dom.DOMCryptoContext;
import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import java.xml.crypto.share.classes.org.w3c.dom.Node;
import java.xml.crypto.share.classes.org.w3c.dom.Text;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * A DOM-based representation of the XML {@code CryptoBinary} simple type
 * as defined in the W3C specification for XML-Signature Syntax and Processing.
 * The XML Schema Definition is defined as:
 *
 * <pre>{@code
 * <simpleType name="CryptoBinary">
 *   <restriction base = "base64Binary">
 *   </restriction>
 * </simpleType>
 * }</pre>
 *
 */
public final class DOMCryptoBinary extends DOMStructure {

    private final BigInteger bigNum;
    private final String value;

    /**
     * Create a {@code DOMCryptoBinary} instance from the specified
     * {@code BigInteger}
     *
     * @param bigNum the arbitrary-length integer
     * @throws NullPointerException if {@code bigNum} is {@code null}
     */
    public DOMCryptoBinary(BigInteger bigNum) {
        if (bigNum == null) {
            throw new NullPointerException("bigNum is null");
        }
        this.bigNum = bigNum;
        // convert to bitstring
        byte[] bytes = XMLUtils.getBytes(bigNum, bigNum.bitLength());
        value = XMLUtils.encodeToString(bytes);
    }

    /**
     * Creates a {@code DOMCryptoBinary} from a node.
     *
     * @param cbNode a CryptoBinary text node
     * @throws MarshalException if value cannot be decoded (invalid format)
     */
    public DOMCryptoBinary(Node cbNode) throws MarshalException {
        value = cbNode.getNodeValue();
        try {
            bigNum = new BigInteger(1, XMLUtils.decode(((Text) cbNode).getData()));
        } catch (Exception ex) {
            throw new MarshalException(ex);
        }
    }

    /**
     * Returns the {@code BigInteger} that this object contains.
     *
     * @return the {@code BigInteger} that this object contains
     */
    public BigInteger getBigNum() {
        return bigNum;
    }

    @Override
    public void marshal(Node parent, String prefix, DOMCryptoContext context)
        throws MarshalException {
        parent.appendChild
            (DOMUtils.getOwnerDocument(parent).createTextNode(value));
    }
}
