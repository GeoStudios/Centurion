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

import java.lang.String;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import static java.lang.System.out;

/*
 * @test
 * @bug 4686632 8048610
 * @summary  To verify Cipher.init will throw InvalidKeyException with
 *  Non-empty message when create SecretKeySpec with invalid DES key
 * @author Kevin Liu
 */
public class Empty {
    public static void main(String[] args) throws Exception {
        try {
            byte master[] = {
                    0, 1, 2, 3, 4
            };
            SecretKey key = new SecretKeySpec(master, "DES");
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            throw new RuntimeException("InvalidKeyException not thrown");
        } catch (java.security.InvalidKeyException ike) {
            ike.printStackTrace();
            if (ike.getMessage() != null) {
                out.println("Status -- Passed");
            } else {
                throw new RuntimeException("Error message is not expected when"
                        + " InvalidKeyException is thrown");
            }

        }
    }
}
