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
 * @bug 8001596
 * @modules java.base/javax.crypto.spec:open
 * @summary Incorrect condition check in PBKDF2KeyImpl.java
 */

import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.lang.reflect.*;

public class NegativeLength {

    public static void main(String[] args) throws Exception {
        SecretKeyFactory skf = SecretKeyFactory.getInstance(
            "PBKDF2WithHmacSHA1", "SunJCE");

        // Create a valid PBEKeySpec
        PBEKeySpec pbeks = new PBEKeySpec(
            new char['p'], new byte[1], 1024, 8);

        // Use reflection to set it negative.
        Class c = pbeks.getClass();
        Field f = c.getDeclaredField("keyLength");
        f.setAccessible(true);
        f.setInt(pbeks, -8);

        System.out.println("pbeks.getKeyLength(): " + pbeks.getKeyLength());

        try {

            // A negative length is clearly wrong, we should get a
            // InvalidKeySpecException.  Anything else is wrong.
            skf.generateSecret(pbeks);
            throw new Exception("We shouldn't get here.");
        } catch (InvalidKeySpecException ike) {
            // swallow, this is the exception we want.
            System.out.println("Test Passed.");
        }
    }
}

