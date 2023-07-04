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

//
// SunJSSE does not support dynamic system properties, no way to re-use
// system properties in samevm/agentvm mode.
//

/*
 * @test
 * @bug 6405536
 * @summary Verify that all ciphersuites work (incl. ECC using NSS crypto)
 * @author Andreas Sterbenz
 * @library /test/lib .. ../../../../javax/net/ssl/TLSCommon
 * @library ../../../../java/security/testlibrary
 * @modules jdk.crypto.cryptoki
 * @run main/othervm -Djdk.tls.namedGroups="secp256r1,sect193r1"
 *      ClientJSSEServerJSSE
 * @run main/othervm -Djdk.tls.namedGroups="secp256r1,sect193r1"
 *      ClientJSSEServerJSSE sm policy
 */

import java.security.Provider;
import java.security.Security;

public class ClientJSSEServerJSSE extends PKCS11Test {

    private static String[] cmdArgs;

    public static void main(String[] args) throws Exception {
        // reset security properties to make sure that the algorithms
        // and keys used in this test are not disabled.
        Security.setProperty("jdk.tls.disabledAlgorithms", "");
        Security.setProperty("jdk.certpath.disabledAlgorithms", "");

        cmdArgs = args;
        main(new ClientJSSEServerJSSE(), args);
    }

    @Override
    public void main(Provider p) throws Exception {
        if (p.getService("KeyFactory", "EC") == null) {
            System.out.println("Provider does not support EC, skipping");
            return;
        }
        Providers.setAt(p, 1);
        CipherTest.main(new JSSEFactory(), cmdArgs);
        Security.removeProvider(p.getName());
    }

    private static class JSSEFactory extends CipherTest.PeerFactory {

        @Override
        String getName() {
            return "Client JSSE - Server JSSE";
        }

        @Override
        CipherTest.Client newClient(CipherTest cipherTest) throws Exception {
            return new JSSEClient(cipherTest);
        }

        @Override
        CipherTest.Server newServer(CipherTest cipherTest) throws Exception {
            return new JSSEServer(cipherTest);
        }
    }
}
