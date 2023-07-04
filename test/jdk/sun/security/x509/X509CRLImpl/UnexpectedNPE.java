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
 * @bug 5052433
 * @summary NullPointerException for generateCRL and generateCRLs methods.
 */
import java.security.NoSuchProviderException;
import java.security.cert.*;
import java.io.ByteArrayInputStream;

public class UnexpectedNPE {
    CertificateFactory cf = null ;

    public UnexpectedNPE() {}

    public static void main( String[] av ) {
        byte[] encoded_1 = { 0x00, 0x00, 0x00, 0x00 };
        byte[] encoded_2 = { 0x30, 0x01, 0x00, 0x00 };
        byte[] encoded_3 = { 0x30, 0x01, 0x00 };

        UnexpectedNPE unpe = new UnexpectedNPE() ;

        if(!unpe.run(encoded_1)) {
            throw new SecurityException("CRLException has not been thrown");
        }

        if(!unpe.run(encoded_2)) {
            throw new SecurityException("CRLException has not been thrown");
        }

        if(!unpe.run(encoded_2)) {
            throw new SecurityException("CRLException has not been thrown");
        }
    }

    private boolean run(byte[] buf) {
        if (cf == null) {
            try {
                cf = CertificateFactory.getInstance("X.509", "SUN");
            } catch (CertificateException e) {
                throw new SecurityException("Cannot get CertificateFactory");
            } catch (NoSuchProviderException npe) {
                throw new SecurityException("Cannot get CertificateFactory");
            }
        }
        try {
            cf.generateCRL(new ByteArrayInputStream(buf));
        } catch (CRLException ce) {
            System.out.println("NPE checking passed");
            return true;
        }

        System.out.println("CRLException has not been thrown");
        return false;
    }
}
