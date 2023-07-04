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
 * @bug 7152176 8194486 8201627
 * @summary More krb5 tests
 * @library /test/lib
 * @compile -XDignore.symbol.file Basic.java
 * @run main jdk.test.lib.FileInstaller TestHosts TestHosts
 * @run main/othervm -Djdk.net.hosts.file=TestHosts Basic
 * @run main/othervm -Djdk.net.hosts.file=TestHosts
 *      -Dsun.security.krb5.acceptor.sequence.number.nonmutual=zero
 *      Basic
 */

import sun.security.jgss.GSSUtil;

// The basic krb5 test skeleton you can copy from
public class Basic {

    public static void main(String[] args) throws Exception {

        new OneKDC(null).writeJAASConf();

        Context c, s, s2, b;
        c = Context.fromJAAS("client");
        s = Context.fromJAAS("server");
        b = Context.fromJAAS("backend");

        c.startAsClient(OneKDC.SERVER, GSSUtil.GSS_KRB5_MECH_OID);
        c.x().requestCredDeleg(true);
        c.x().requestMutualAuth(false);
        s.startAsServer(GSSUtil.GSS_KRB5_MECH_OID);

        Context.handshake(c, s);

        Context.transmit("i say high --", c, s);
        Context.transmit("   you say low", s, c);

        s2 = s.delegated();
        s.dispose();
        c.dispose();

        s2.startAsClient(OneKDC.BACKEND, GSSUtil.GSS_KRB5_MECH_OID);
        b.startAsServer(GSSUtil.GSS_KRB5_MECH_OID);

        Context.handshake(s2, b);
    }
}
