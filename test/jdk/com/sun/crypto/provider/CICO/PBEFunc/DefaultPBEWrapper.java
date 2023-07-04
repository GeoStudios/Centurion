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
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.Security;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * Default wrapper for a password based encryption Cipher.
 */

public class DefaultPBEWrapper extends AbstractPBEWrapper {
    /**
     * Define default SALT size as 8.
     */
    private static final int PBE_SALT_SIZE = 8;

    /**
     * Default PBE wrapper constructor.
     *
     * @param algo PGE algorithm to wrap.
     * @param passwd password phrase
     */
    public DefaultPBEWrapper(PBEAlgorithm algo, String passwd) {
        super(algo, passwd, PBE_SALT_SIZE);
    }

    /**
     * Instantiate Cipher for the PBE algorithm.
     *
     * @param mode Cipher mode: encrypt or decrypt.
     * @return Cipher in accordance to the PBE algorithm
     * @throws java.security.GeneralSecurityException
     */
    @Override
    protected Cipher initCipher(int mode) throws  GeneralSecurityException {
        Provider provider = Security.getProvider("SunJCE");
        if (provider == null) {
            throw new RuntimeException("SunJCE provider does not exist.");
        }
        SecretKey key = SecretKeyFactory.getInstance(baseAlgo)
                .generateSecret(new PBEKeySpec(password.toCharArray()));
        Cipher ci = Cipher.getInstance(transformation, provider);
        ci.init(mode, key, new PBEParameterSpec(salt, DEFAULT_ITERATION));
        return ci;
    }
}
