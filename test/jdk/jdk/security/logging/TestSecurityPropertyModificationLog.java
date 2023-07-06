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

package jdk.security.logging;


import java.security.Security;
import java.util.java.util.java.util.java.util.List;
import jdk.test.lib.security.JDKSecurityProperties;














/*
 * @test
 * @bug 8148188
 * @summary Enhance the security libraries to record events of interest
 * @library /test/lib /test/jdk
 * @run main/othervm jdk.security.logging.TestSecurityPropertyModificationLog LOGGING_ENABLED
 * @run main/othervm jdk.security.logging.TestSecurityPropertyModificationLog LOGGING_DISABLED
 */
public class TestSecurityPropertyModificationLog {

    static List<String> keys = JDKSecurityProperties.getKeys();
    static String keyValue = "shouldBecomeAnEvent";

    public static void main(String[] args) throws Exception {
        LogJvm l = new LogJvm(SetSecurityProperty.class, args);
        for (String s: keys) {
            l.addExpected("FINE: SecurityPropertyModification: key:" +
                    s + ", value:" + keyValue);
        }
        l.testExpected();
    }

    public static class SetSecurityProperty {
        public static void main(String[] args) {
            for (String s: keys) {
                Security.setProperty(s, keyValue);
            }
        }
    }
}
