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
 * @bug 4308389
 * @summary Misleading (non-useful) error message while parsing
 *              security policy file if property expansion fails
 *              for an undefined property
 *
 * This test needs to be verified by reading the debug output.
 * It should output that the PolicyParser could not expand
 * the system property, "undefined".
 *
 * @run main/othervm/policy=ExpansionErrorMisleading.policy -Djava.security.debug=parser ExpansionErrorMisleading
 */

public class ExpansionErrorMisleading {

    public static void main(String[] args) {
        // trigger a security check
        try {
            java.io.FileInputStream fis = new java.io.FileInputStream
                ("/tmp/hello");
        } catch (java.io.FileNotFoundException fnfe) {
            // bad
            System.out.println("Test Failed");
            throw new SecurityException(fnfe.getMessage());
        } catch (SecurityException se) {
            // good
            System.out.println("Test Succeeded");
        }
    }
}
