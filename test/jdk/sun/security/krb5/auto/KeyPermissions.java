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
 * @bug 8004488 8194486
 * @summary wrong permissions checked in krb5
 * @library /test/lib
 * @compile -XDignore.symbol.file KeyPermissions.java
 * @run main jdk.test.lib.FileInstaller TestHosts TestHosts
 * @run main/othervm -Djava.security.manager=allow -Djdk.net.hosts.file=TestHosts KeyPermissions
 */

import java.security.AccessControlException;
import java.security.Permission;
import javax.security.auth.PrivateCredentialPermission;
import sun.security.jgss.GSSUtil;

public class KeyPermissions extends SecurityManager {

    @Override
    public void checkPermission(Permission perm) {
        if (perm instanceof PrivateCredentialPermission) {
            if (!perm.getName().startsWith("javax.security.auth.kerberos.")) {
                throw new AccessControlException(
                        "I don't like this", perm);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setSecurityManager(new KeyPermissions());
        new OneKDC(null).writeJAASConf();
        Context s = Context.fromJAAS("server");
        s.startAsServer(GSSUtil.GSS_KRB5_MECH_OID);
    }
}

