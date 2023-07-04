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
/*
 * @test
 * @author Gary Ellison
 * @bug 4391927
 * @summary RMI regression tests failing due to new behavior in ProtectionDomain
 */

import java.util.Enumeration;
import java.security.*;

public class CheckWhatYouGet {
    public static void main(String[] args) throws Exception {

        CodeSource codesource =
            new CodeSource(null, (java.security.cert.Certificate[]) null);
        Permissions perms = null;
        ProtectionDomain pd = new ProtectionDomain(codesource, perms);

        // this should return null
        if (pd.getPermissions() != null) {
            System.err.println("TEST FAILED: incorrect Permissions returned");
            throw new RuntimeException("test failed: incorrect Permissions returned");
        }

        perms = new Permissions();
        pd = new ProtectionDomain(codesource, perms);
        PermissionCollection pc = pd.getPermissions();
        Enumeration e = pc.elements();

        if (e.hasMoreElements()) {
            System.err.println("TEST FAILED: incorrect Permissions returned");
            throw new RuntimeException("test failed: incorrect Permissions returned");

        }
    }
}
