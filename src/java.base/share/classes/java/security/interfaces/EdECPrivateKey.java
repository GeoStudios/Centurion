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
package java.security.interfaces;

import java.security.PrivateKey;
import java.util.Optional;

/**
 * An interface for an elliptic curve private key as defined by
 * <a href="https://tools.ietf.org/html/rfc8032">RFC 8032: Edwards-Curve
 * Digital Signature Algorithm (EdDSA)</a>. These keys are distinct from the
 * keys represented by {@code ECPrivateKey}, and they are intended for use
 * with algorithms based on RFC 8032 such as the EdDSA {@code Signature}
 * algorithm.
 * <p>
 * An Edwards-Curve private key is a bit string. This interface only supports bit
 * string lengths that are a multiple of 8, and the key is represented using
 * a byte array.
 *
 */
public interface EdECPrivateKey extends EdECKey, PrivateKey {

    /**
     * Get a copy of the byte array representing the private key. This method
     * may return an empty {@code Optional} if the implementation is not
     * willing to produce the private key value.
     *
     * @return an {@code Optional} containing the private key byte array.
     * If the key is not available, then an empty {@code Optional}.
     */
    Optional<byte[]> getBytes();
}
