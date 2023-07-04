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
 * @bug 7152121 8194486
 * @summary Krb5LoginModule no longer handles keyTabNames with "file:" prefix
 * @library /test/lib
 * @compile -XDignore.symbol.file FileKeyTab.java
 * @run main jdk.test.lib.FileInstaller TestHosts TestHosts
 * @run main/othervm -Djdk.net.hosts.file=TestHosts FileKeyTab
 */

import java.io.File;
import java.io.FileOutputStream;

// The basic krb5 test skeleton you can copy from
public class FileKeyTab {

    public static void main(String[] args) throws Exception {
        new OneKDC(null).writeJAASConf();
        String ktab = new File(OneKDC.KTAB).getAbsolutePath().replace('\\', '/');
        File f = new File(OneKDC.JAAS_CONF);
        try (FileOutputStream fos = new FileOutputStream(f)) {
            fos.write((
                "server {\n" +
                "    com.sun.security.auth.module.Krb5LoginModule required\n" +
                "    principal=\"" + OneKDC.SERVER + "\"\n" +
                "    useKeyTab=true\n" +
                "    keyTab=\"file:" + ktab + "\"\n" +
                "    storeKey=true;\n};\n"
                ).getBytes());
        }
        Context.fromJAAS("server");
    }
}
