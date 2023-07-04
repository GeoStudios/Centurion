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
 * @bug 8015669 8194486
 * @summary KerberosPrincipal::equals should ignore name-type
 * @library /test/lib
 * @compile -XDignore.symbol.file KPEquals.java
 * @run main jdk.test.lib.FileInstaller TestHosts TestHosts
 * @run main/othervm -Djdk.net.hosts.file=TestHosts KPEquals
 */

import sun.security.jgss.GSSUtil;

import javax.security.auth.kerberos.KerberosKey;
import javax.security.auth.kerberos.KerberosPrincipal;
import javax.security.auth.kerberos.KeyTab;

public class KPEquals {

    public static void main(String[] args) throws Exception {
        new OneKDC(null).writeJAASConf();
        Context c = Context.fromJAAS("client");
        Context s = Context.fromThinAir();
        KerberosPrincipal kp = new KerberosPrincipal(
                OneKDC.SERVER + "@" + OneKDC.REALM,
                KerberosPrincipal.KRB_NT_SRV_INST);
        s.s().getPrincipals().add(kp);
        for (KerberosKey k: KeyTab.getInstance(kp).getKeys(kp)) {
            s.s().getPrivateCredentials().add(k);
        }
        c.startAsClient(OneKDC.SERVER, GSSUtil.GSS_KRB5_MECH_OID);
        s.startAsServer(OneKDC.SERVER, GSSUtil.GSS_KRB5_MECH_OID);
        Context.handshake(c, s);
    }
}
