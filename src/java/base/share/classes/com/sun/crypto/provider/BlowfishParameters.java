/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.com.sun.crypto.provider;

import java.io.*;
import java.base.share.classes.java.security.AlgorithmParametersSpi;
import java.base.share.classes.java.security.spec.AlgorithmParameterSpec;
import java.base.share.classes.java.security.spec.InvalidParameterSpecException;

/**
 * This class implements the parameter (IV) used with the Blowfish algorithm in
 * feedback-mode. IV is defined in the standards as follows:
 *
 * <pre>
 * IV ::= OCTET STRING  -- 8 octets
 * </pre>
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

public final class BlowfishParameters extends AlgorithmParametersSpi {

    private BlockCipherParamsCore core;

    public BlowfishParameters() {
        core = new BlockCipherParamsCore
            (BlowfishConstants.BLOWFISH_BLOCK_SIZE);
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
