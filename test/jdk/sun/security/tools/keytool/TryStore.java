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
 * @bug 7047200
 * @summary keytool can try save to a byte array before overwrite the file
 * @library /test/lib
 */

import jdk.test.lib.SecurityTools;
import jdk.test.lib.process.OutputAnalyzer;

public class TryStore {
    public static void main(String[] args) throws Exception {
        keytool("-genkeypair -alias a -dname CN=A -storepass changeit -keypass changeit");
        keytool("-genkeypair -alias b -dname CN=B -storepass changeit -keypass changeit");

        // We use -protected for JKS keystore. This is illegal so the command should
        // fail. Then we can check if the keystore is damaged.

        keytool("-genkeypair -protected -alias b -delete -debug")
                .shouldNotHaveExitValue(0);

        keytool("-list -storepass changeit")
                .shouldHaveExitValue(0);
    }

    static OutputAnalyzer keytool(String s) throws Exception {
        return SecurityTools.keytool(
                "-storetype jks -keystore trystore.jks -keyalg rsa " + s);
    }
}
