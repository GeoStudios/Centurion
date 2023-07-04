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
 * @bug 8162752
 * @summary keytool -importkeystore should probe srcstoretype if not specified
 * @modules java.base/sun.security.tools.keytool
 */

import sun.security.tools.keytool.Main;

public class HasSrcStoretypeOption {

    public static void main(String[] args) throws Exception {
        run("-genkeypair -keyalg DSA -alias a -dname CN=A -storetype jceks -keystore jce");
        // When there is no -srcstoretype, it should be probed from the file
        run("-importkeystore -srckeystore jce -destkeystore jks -deststoretype jks");
    }

    private static void run(String cmd) throws Exception {
        cmd += " -debug -storepass changeit -keypass changeit -srcstorepass changeit";
        Main.main(cmd.split(" "));
    }
}
