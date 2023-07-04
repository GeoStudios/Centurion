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

import java.security.Permission;

/**
 * @test
 * @summary String concatenation fails with a custom SecurityManager that uses concatenation
 * @bug 8155090 8158851 8222895
 * @requires !vm.graal.enabled
 *
 * @compile WithSecurityManager.java
 *
 * @run main/othervm -Xverify:all -Djava.security.manager=allow WithSecurityManager
 * @run main/othervm -Xverify:all --limit-modules=java.base -Djava.security.manager=allow WithSecurityManager
*/
public class WithSecurityManager {
    public static void main(String[] args) throws Throwable {
        // First time should succeed to bootstrap everything
        {
            SecurityManager sm = new SecurityManager() {
                @Override
                public void checkPermission(Permission perm) {
                    String abc = "abc";
                    int ival = perm.hashCode();
                    String full = abc + "abc";
                    // Contorted to avoid sweeping cases where we've
                    // pre-generated commonly used species under the rug
                    full = "abc" + ival + "def" + abc + "def" + abc + "def" +
                           abc + "def" + ival + "def" + abc + "def" +
                           abc + "def" + abc + "def" + abc + "def";
                }
            };
            System.setSecurityManager(sm);
            ClassLoader cl = new ClassLoader() {};
        }

        // Second time should succeed to run after bootstrapping
        {
            SecurityManager sm = new SecurityManager() {
                @Override
                public void checkPermission(Permission perm) {
                    String abc = "abc";
                    int ival = perm.hashCode();
                    String full = abc + "abc";
                    // Contorted variant to avoid sweeping cases where we've
                    // pre-generated commonly used species under the rug
                    full = "abc" + ival + "def" + abc + "def" + abc + "def" +
                            abc + "def" + ival + "def" + abc + "def" +
                            abc + "def" + abc + "def" + abc + "def";
                }
            };

            // Provoke checkPermission invocation
            System.setSecurityManager(sm);
            ClassLoader cl = new ClassLoader() {};
        }
    }
}
