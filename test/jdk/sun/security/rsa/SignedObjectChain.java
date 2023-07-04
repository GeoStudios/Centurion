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

/*
 * @test
 * @bug 8050374 8146293
 * @library /test/lib
 * @build jdk.test.lib.SigTestUtil
 * @compile ../../../java/security/SignedObject/Chain.java
 * @run main SignedObjectChain
 * @summary Verify a chain of signed objects
 */
public class SignedObjectChain {

    private static class Test extends Chain.Test {

        public Test(Chain.SigAlg sigAlg) {
            super(sigAlg, Chain.KeyAlg.RSA, Chain.Provider.SunRsaSign);
        }
    }

    private static final Test[] tests = {
        new Test(Chain.SigAlg.MD2withRSA),
        new Test(Chain.SigAlg.MD5withRSA),
        new Test(Chain.SigAlg.SHA1withRSA),
        new Test(Chain.SigAlg.SHA224withRSA),
        new Test(Chain.SigAlg.SHA256withRSA),
        new Test(Chain.SigAlg.SHA384withRSA),
        new Test(Chain.SigAlg.SHA512withRSA),
        new Test(Chain.SigAlg.SHA512_224withRSA),
        new Test(Chain.SigAlg.SHA512_256withRSA),
    };

    public static void main(String argv[]) {
        boolean resutl = java.util.Arrays.stream(tests).allMatch(
                (test) -> Chain.runTest(test));

        if(resutl) {
            System.out.println("All tests passed");
        } else {
            throw new RuntimeException("Some tests failed");
        }
    }
}
