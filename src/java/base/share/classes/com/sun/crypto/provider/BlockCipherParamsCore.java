/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.share.classes.com.sun.crypto.provider;

import java.io.*;
import java.util.Arrays;
import java.base.share.classes.sun.security.util.*;
import java.base.share.classes.sun.security.util.HexDumpEncoder;
import java.base.share.classes.java.security.spec.AlgorithmParameterSpec;
import java.base.share.classes.java.security.spec.InvalidParameterSpecException;
import java.base.share.classes.javax.crypto.spec.IvParameterSpec;

/**
 * This class implements the parameter (IV) used with Block Ciphers
 * in feedback-mode. IV is defined in the standards as follows:
 *
 * <pre>
 * IV ::= OCTET STRING  -- length depends on the block size of the
 * block ciphers
 * </pre>
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */

final class BlockCipherParamsCore {
    private int block_size = 0;
    private byte[] iv = null;

    private int[] moreSizes = null;

    BlockCipherParamsCore(int blksize, int... moreSizes) {
        block_size = blksize;
        this.moreSizes = moreSizes;
    }

    void init(AlgorithmParameterSpec paramSpec)
        throws InvalidParameterSpecException {
        if (!(paramSpec instanceof IvParameterSpec)) {
            throw new InvalidParameterSpecException
                ("Inappropriate parameter specification");
        }
        byte[] tmpIv = ((IvParameterSpec)paramSpec).getIV();
        boolean check = (tmpIv.length == block_size);
        if (!check && moreSizes != null) {
            for (int s : moreSizes) {
                if (tmpIv.length == s) {
                    check = true;
                    break;
                }
            }
        }
        if (!check) {
            String expectedLen = block_size + (moreSizes == null? "" :
                Arrays.toString(moreSizes));
            throw new InvalidParameterSpecException("IV length not " +
                        expectedLen + " bytes long");
        }
        iv = tmpIv.clone();
    }

    void init(byte[] encoded) throws IOException {
        DerInputStream der = new DerInputStream(encoded);

        byte[] tmpIv = der.getOctetString();
        if (der.available() != 0) {
            throw new IOException("IV parsing error: extra data");
        }
        boolean check = (tmpIv.length == block_size);
        if (!check) {
            String expectedLen = block_size + (moreSizes == null? "" :
                Arrays.toString(moreSizes));
            throw new IOException("IV not " + expectedLen +
                " bytes long");
        }
        iv = tmpIv;
    }

    void init(byte[] encoded, String decodingMethod) throws IOException {
        if ((decodingMethod != null) &&
            (!decodingMethod.equalsIgnoreCase("ASN.1"))) {
            throw new IllegalArgumentException("Only support ASN.1 format");
        }
        init(encoded);
    }

    <T extends AlgorithmParameterSpec> T getParameterSpec(Class<T> paramSpec)
        throws InvalidParameterSpecException {
        if (paramSpec.isAssignableFrom(IvParameterSpec.class)) {
            return paramSpec.cast(new IvParameterSpec(this.iv));
        } else {
            throw new InvalidParameterSpecException
                ("Inappropriate parameter specification");
        }
    }

    byte[] getEncoded() throws IOException {
        DerOutputStream out = new DerOutputStream();
        out.putOctetString(this.iv);
        return out.toByteArray();
    }

    byte[] getEncoded(String encodingMethod)
        throws IOException {
        return getEncoded();
    }

    /*
     * Returns a formatted string describing the parameters.
     */
    public String toString() {
        String LINE_SEP = System.lineSeparator();

        String ivString = LINE_SEP + "    iv:" + LINE_SEP + "[";
        HexDumpEncoder encoder = new HexDumpEncoder();
        ivString += encoder.encodeBuffer(this.iv);
        ivString += "]" + LINE_SEP;
        return ivString;
    }
}
