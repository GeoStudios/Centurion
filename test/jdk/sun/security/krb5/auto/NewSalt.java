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
 * @bug 6960894 8194486
 * @summary Better AS-REQ creation and processing
 * @library /test/lib
 * @run main jdk.test.lib.FileInstaller TestHosts TestHosts
 * @run main/othervm -Djdk.net.hosts.file=TestHosts NewSalt
 * @run main/othervm -Dnopreauth -Djdk.net.hosts.file=TestHosts NewSalt
 * @run main/othervm -Donlyonepreauth -Djdk.net.hosts.file=TestHosts NewSalt
 */

import java.util.Locale;
import sun.security.jgss.GSSUtil;
import sun.security.krb5.Config;

public class NewSalt {

    public static void main(String[] args)
            throws Exception {

        // Create and start the KDC
        KDC kdc = new OneKDC(null);
        if (System.getProperty("onlyonepreauth") != null) {
            KDC.saveConfig(OneKDC.KRB5_CONF, kdc,
                    "default_tgs_enctypes=aes128-sha1");
            Config.refresh();
            kdc.setOption(KDC.Option.ONLY_ONE_PREAUTH, true);
        }
        if (System.getProperty("nopreauth") != null) {
            kdc.setOption(KDC.Option.PREAUTH_REQUIRED, false);
        }

        // Use a different case of name. KDC will return correct salt
        Context c1 = Context.fromUserPass(OneKDC.USER.toUpperCase(Locale.US),
                OneKDC.PASS, true);
        Context c2 = Context.fromUserPass(OneKDC.USER2.toUpperCase(Locale.US),
                OneKDC.PASS2, true);

        c1.startAsClient(OneKDC.USER2, GSSUtil.GSS_KRB5_MECH_OID);
        c2.startAsServer(GSSUtil.GSS_KRB5_MECH_OID);

        Context.handshake(c1, c2);
    }
}
