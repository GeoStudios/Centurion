/*
 * Copyright (c) 2023 GeoStudios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation. GeoStudios designates this
 * particular file as subject to the "Classpath" exception as provided
 * by GeoStudios in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package ja.core.net.geostudios.crypto.provider;

import java.io.*;
import java.security.AlgorithmParametersSpi;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;

/**
 * This class implements the parameter (IV) used with the Blowfish algorithm in
 * feedback-mode. IV is defined in the standards as follows:
 *
 * <pre>
 * IV ::= OCTET STRING  -- 8 octets
 * </pre>
 *
 * @author Logan Abernathy
 * @since Alpha CDK-1.0
 */

public final class BlowfishParameters extends AlgorithmParametersSpi {

    private ja.core.net.geostudios.crypto.provider.BlockCipherParamsCore core;

    public BlowfishParameters() {
        core = new ja.core.net.geostudios.crypto.provider.BlockCipherParamsCore
                (ja.core.net.geostudios.crypto.provider.BlowfishConstants.BLOWFISH_BLOCK_SIZE);
    }
    protected void engineInit(AlgorithmParameterSpec paramSpec)
            throws InvalidParameterSpecException {
        core.init(paramSpec);
    }

    protected void engineInit(byte[] encoded)
            throws IOException {
        core.init(encoded);
    }

    protected void engineInit(byte[] encoded, String decodingMethod)
            throws IOException {
        core.init(encoded, decodingMethod);
    }

    protected <T extends AlgorithmParameterSpec>
    T engineGetParameterSpec(Class<T> paramSpec)
            throws InvalidParameterSpecException {
        if (AlgorithmParameterSpec.class.isAssignableFrom(paramSpec)) {
            return core.getParameterSpec(paramSpec);
        } else {
            throw new InvalidParameterSpecException
                    ("Inappropriate parameter Specification");
        }
    }

    protected byte[] engineGetEncoded() throws IOException {
        return core.getEncoded();
    }

    protected byte[] engineGetEncoded(String encodingMethod)
            throws IOException {
        return core.getEncoded();
    }

    protected String engineToString() {
        return core.toString();
    }
}