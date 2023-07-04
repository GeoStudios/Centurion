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
 * @bug 8001319 8130181
 * @summary check that SecurityPermission insertProvider permission is enforced
 *          correctly
 * @run main/othervm/policy=AddProvider.policy.1 AddProvider 1
 * @run main/othervm/policy=AddProvider.policy.2 AddProvider 2
 * @run main/othervm/policy=AddProvider.policy.3 AddProvider 3
 */
import java.security.Provider;
import java.security.Security;

public class AddProvider {

    public static void main(String[] args) throws Exception {
        boolean legacy = args[0].equals("2");
        Security.addProvider(new TestProvider("Test1"));
        Security.insertProviderAt(new TestProvider("Test2"), 1);
        try {
            Security.addProvider(new TestProvider("Test3"));
            if (legacy) {
                throw new Exception("Expected SecurityException");
            }
        } catch (SecurityException se) {
            if (!legacy) {
                throw se;
            }
        }
    }

    private static class TestProvider extends Provider {
        TestProvider(String name) {
            super(name, "0.0", "Not for use in production systems!");
        }
    }
}
