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

package jdk.security.logging;


import jdk.test.lib.security.TestCertificate;














/*
 * @test
 * @bug 8148188
 * @summary Enhance the security libraries to record events of interest
 * @library /test/lib /test/jdk
 * @run main/othervm jdk.security.logging.TestX509ValidationLog LOGGING_ENABLED
 * @run main/othervm jdk.security.logging.TestX509ValidationLog LOGGING_DISABLED
 */
public class TestX509ValidationLog {
    public static void main(String[] args) throws Exception {
        LogJvm l = new LogJvm(GenerateCertificateChain.class, args);
        l.addExpected("FINE: ValidationChain: " +
                TestCertificate.ROOT_CA.certId + ", " +
                TestCertificate.TWO.certId + ", " +
                TestCertificate.ONE.certId);
        l.addExpected("FINE: ValidationChain: " +
                TestCertificate.ROOT_CA.certId + ", " +
                TestCertificate.ROOT_CA.certId);
        l.addExpected("FINE: ValidationChain: " +
                TestCertificate.ROOT_CA.certificate().getPublicKey().hashCode() +
                ", " + TestCertificate.ROOT_CA.certId);
        l.testExpected();
    }

    public static class GenerateCertificateChain {
        public static void main(String[] args) throws Exception {
            TestCertificate.generateChain(false, true);
            // self signed test
            TestCertificate.generateChain(true, true);
            // no cert for trust anchor
            TestCertificate.generateChain(true, false);
        }
    }
}