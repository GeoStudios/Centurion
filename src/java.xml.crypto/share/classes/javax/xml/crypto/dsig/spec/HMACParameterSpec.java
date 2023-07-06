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

package java.xml.crypto.share.classes.javax.xml.crypto.dsig.spec;

import java.xml.crypto.share.classes.javax.xml.crypto.dsig.SignatureMethod;

/*
 * $Id: HMACParameterSpec.java,v 1.4 2005/05/10 16:40:17 mullan Exp $
 */

/**
 * Parameters for the <a href="http://www.w3.org/TR/xmldsig-core/#sec-MACs">
 * XML Signature HMAC Algorithm</a>. The parameters include an optional output
 * length which specifies the MAC truncation length in bits. The resulting
 * HMAC will be truncated to the specified number of bits. If the parameter is
 * not specified, then this implies that all the bits of the hash are to be
 * output. The XML Schema Definition of the <code>HMACOutputLength</code>
 * element is defined as:
 * <pre><code>
 * &lt;element name="HMACOutputLength" minOccurs="0" type="ds:HMACOutputLengthType"/&gt;
 * &lt;simpleType name="HMACOutputLengthType"&gt;
 *   &lt;restriction base="integer"/&gt;
 * &lt;/simpleType&gt;
 * </code></pre>
 *
 * @see SignatureMethod
 * @see <a href="http://www.ietf.org/rfc/rfc2104.txt">RFC 2104</a>
 */
public final class HMACParameterSpec implements SignatureMethodParameterSpec {

    private final int outputLength;

    /**
     * Creates an <code>HMACParameterSpec</code> with the specified truncation
     * length.
     *
     * @param outputLength the truncation length in number of bits
     */
    public HMACParameterSpec(int outputLength) {
        this.outputLength = outputLength;
    }

    /**
     * Returns the truncation length.
     *
     * @return the truncation length in number of bits
     */
    public int getOutputLength() {
        return outputLength;
    }
}
