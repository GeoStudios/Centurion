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
 * @bug 8186831 8194486
 * @summary Kerberos ignores PA-DATA with a non-null s2kparams
 * @library /test/lib
 * @compile -XDignore.symbol.file DiffSaltParams.java
 * @run main jdk.test.lib.FileInstaller TestHosts TestHosts
 * @run main/othervm -Dsun.security.krb5.debug=true
 *      -Djdk.net.hosts.file=TestHosts DiffSaltParams
 */

public class DiffSaltParams {

    public static void main(String[] args) throws Exception {

        OneKDC kdc = new OneKDC(null).writeJAASConf();
        kdc.addPrincipal("user1", "user1pass".toCharArray(),
                "hello", new byte[]{0, 0, 1, 0});
        kdc.addPrincipal("user2", "user2pass".toCharArray(),
                "hello", null);
        kdc.addPrincipal("user3", "user3pass".toCharArray(),
                null, new byte[]{0, 0, 1, 0});
        kdc.addPrincipal("user4", "user4pass".toCharArray());

        Context.fromUserPass("user1", "user1pass".toCharArray(), true);
        Context.fromUserPass("user2", "user2pass".toCharArray(), true);
        Context.fromUserPass("user3", "user3pass".toCharArray(), true);
        Context.fromUserPass("user4", "user4pass".toCharArray(), true);
    }
}
