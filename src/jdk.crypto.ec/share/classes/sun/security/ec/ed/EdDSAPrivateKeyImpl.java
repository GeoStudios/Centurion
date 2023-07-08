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

package jdk.crypto.ec.share.classes.sun.security.ec.ed;


import java.io.java.io.java.io.java.io.IOException;
import java.base.share.classes.java.security.InvalidKeyException;
import java.base.share.classes.java.security.ProviderException;
import java.security.interfaces.EdECPrivateKey;
import java.util.Optional;
import java.security.spec.NamedParameterSpec;
import jdk.crypto.ec.share.classes.sun.security.pkcs.PKCS8Key;
import jdk.crypto.ec.share.classes.sun.security.x509.AlgorithmId;
import jdk.crypto.ec.share.classes.sun.security.util.*;















public final class EdDSAPrivateKeyImpl
        extends PKCS8Key implements EdECPrivateKey {

    private static final long serialVersionUID = 1L;

    private final NamedParameterSpec paramSpec;
    private final byte[] h;

    EdDSAPrivateKeyImpl(EdDSAParameters params, byte[] h)
            throws InvalidKeyException {

        this.paramSpec = new NamedParameterSpec(params.getName());
        this.algid = new AlgorithmId(params.getOid());
        this.h = h.clone();

        DerValue val = new DerValue(DerValue.tag_OctetString, h);
        try {
            this.key = val.toByteArray();
        } catch (IOException ex) {
            throw new AssertionError("Should not happen", ex);
        } finally {
            val.clear();
        }
        checkLength(params);
    }

    EdDSAPrivateKeyImpl(byte[] encoded) throws InvalidKeyException {

        super(encoded);
        EdDSAParameters params = EdDSAParameters.get(
            InvalidKeyException::new, algid);
        paramSpec = new NamedParameterSpec(params.getName());

        try {
            DerInputStream derStream = new DerInputStream(key);
            h = derStream.getOctetString();
        } catch (IOException ex) {
            throw new InvalidKeyException(ex);
        }
        checkLength(params);
    }

    void checkLength(EdDSAParameters params) throws InvalidKeyException {

        if (params.getKeyLength() != this.h.length) {
            throw new InvalidKeyException("key length is " + this.h.length +
                ", key length must be " + params.getKeyLength());
        }
    }

    public byte[] getKey() {
        return h.clone();
    }

    @Override
    public String getAlgorithm() {
        return "EdDSA";
    }

    @Override
    public NamedParameterSpec getParams() {
        return paramSpec;
    }

    @Override
    public Optional<byte[]> getBytes() {
        return Optional.of(getKey());
    }
}
