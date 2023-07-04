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

import java.security.Provider;
import java.io.PrintStream;

/**
 * An utility class to create PBEWrapper object for the TestCipherSameBuffer
 * test.
 *
 * @author Alexander Fomin
 */
public class PBEWrapperCreator {

    private static final String PBKDF2 = "PBKDF2";
    private static final String AES = "AES";

    /**
     * Create PBEWrapper for the TestCipherSameBuffer test using given
     * parameters.
     *
     * @param p security provider
     * @param algo algorithms to test
     * @param passwd a password phrase
     * @param out print stream object
     * @return PBEWrapper in accordance to requested algorithm
     * @throws Exception all exception are thrown.
     */
    public static PBEWrapper createWrapper(Provider p, String algo,
            String passwd, PrintStream out) throws Exception {
        if (algo.toUpperCase().contains(PBKDF2)) {
            return new PBKDF2Wrapper(p, algo, passwd, out);
        } else if (algo.toUpperCase().contains(AES)) {
            return new AESPBEWrapper(p, algo, passwd, out);
        } else {
            return new PBECipherWrapper(p, algo, passwd, out);
        }
    }
}
