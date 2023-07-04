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
import java.lang.ref.Reference;
import java.net.Authenticator;
import java.net.NetPermission;
import java.net.PasswordAuthentication;
import java.security.AccessControlException;

/**
 * @test
 * @bug 8169068
 * @summary  Basic test for Authenticator.getDefault()
 * @run main/othervm -Djava.security.manager=allow GetAuthenticatorTest
 */
public class GetAuthenticatorTest {

    static final class MyAuthenticator extends Authenticator {

        MyAuthenticator () {
            super ();
        }

        @Override
        public PasswordAuthentication getPasswordAuthentication () {
            System.out.println ("Auth called");
            return (new PasswordAuthentication ("user",
                        "passwordNotCheckedAnyway".toCharArray()));
        }

    }

    public static void main (String args[]) throws Exception {
        Authenticator defaultAuth = Authenticator.getDefault();
        if (defaultAuth != null) {
            throw new RuntimeException("Unexpected authenticator: null expected");
        }
        MyAuthenticator auth = new MyAuthenticator();
        Authenticator.setDefault(auth);
        defaultAuth = Authenticator.getDefault();
        if (defaultAuth != auth) {
            throw new RuntimeException("Unexpected authenticator: auth expected");
        }
        System.setSecurityManager(new SecurityManager());
        try {
            defaultAuth = Authenticator.getDefault();
            throw new RuntimeException("Expected security exception not raised");
        } catch (AccessControlException s) {
            System.out.println("Got expected exception: " + s);
            if (!s.getPermission().equals(new NetPermission("requestPasswordAuthentication"))) {
                throw new RuntimeException("Unexpected permission check: " + s.getPermission());
            }
        }
        System.out.println("Test passed with default authenticator "
                           + defaultAuth);
    }
}
