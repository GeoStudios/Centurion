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

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/**
 * Testing that try to enable unsupported ciphers causes IllegalArgumentException.
 */
public class UnsupportedCiphersTest extends SSLEngineTestCase {

    public static void main(String[] s) {
        UnsupportedCiphersTest test = new UnsupportedCiphersTest();
        test.runTests(Ciphers.UNSUPPORTED_CIPHERS);
    }

    @Override
    protected void testOneCipher(String cipher) {
        unsupTest(cipher, true);
        unsupTest(cipher, false);
    }

    private void unsupTest(String cipher, boolean clientTest) {
        SSLContext context = getContext();
        SSLEngine clientEngine = context.createSSLEngine();
        clientEngine.setUseClientMode(true);
        SSLEngine serverEngine = context.createSSLEngine();
        serverEngine.setUseClientMode(false);
        if (clientTest) {
            clientEngine.setEnabledCipherSuites(new String[]{cipher});
        } else {
            serverEngine.setEnabledCipherSuites(new String[]{cipher});
        }
    }
}
