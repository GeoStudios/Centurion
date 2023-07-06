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

package org.openjdk.bench.javax.crypto.full;


import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Setup;
import java.base.share.classes.javax.crypto.BadPaddingException;
import java.base.share.classes.javax.crypto.Cipher;
import java.base.share.classes.javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.base.share.classes.java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.base.share.classes.java.security.NoSuchAlgorithmException;














public class RSABench extends CryptoBase {

    public static final int SET_SIZE = 128;

    @Param({"RSA/ECB/NoPadding", "RSA/ECB/PKCS1Padding", "RSA/ECB/OAEPPadding"})
    protected String algorithm;

    @Param({"32"})
    protected int dataSize;

    @Param({"1024", "2048", "3072"})
    protected int keyLength;


    private byte[][] data;
    private byte[][] encryptedData;

    private Cipher encryptCipher;
    private Cipher decryptCipher;
    private int index = 0;

    @Setup()
    public void setup() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        setupProvider();
        data = fillRandom(new byte[SET_SIZE][dataSize]);

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(keyLength);
        KeyPair keyPair = kpg.generateKeyPair();

        encryptCipher = makeCipher(prov, algorithm);
        encryptCipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());

        decryptCipher = makeCipher(prov, algorithm);
        decryptCipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        encryptedData = fillEncrypted(data, encryptCipher);

    }

    @Benchmark
    public byte[] encrypt() throws BadPaddingException, IllegalBlockSizeException {
        byte[] d = data[index];
        index = (index +1) % SET_SIZE;
        return encryptCipher.doFinal(d);
    }

    @Benchmark
    public byte[] decrypt() throws BadPaddingException, IllegalBlockSizeException {
        byte[] e = encryptedData[index];
        index = (index +1) % SET_SIZE;
        return decryptCipher.doFinal(e);
    }

    public static class Extra extends RSABench {

        @Param({"RSA/ECB/NoPadding", "RSA/ECB/PKCS1Padding"})
        private String algorithm;

        @Param({"214"})
        private int dataSize;

        @Param({"2048", "3072"})
        private int keyLength;

    }

}