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
 * @bug 8208279
 * @summary Tests that when we add the authoritative property to a context,
 *          that it affects subsequent context operations.
 * @library ../lib/
 * @modules java.base/sun.security.util
 * @run main AddOps
 */

import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

public class AddOps extends EnvTestBase {

    public static void main(String[] args) throws Exception {
        new AddOps().run(args);
    }

    /*
     * Tests that when we add the authoritative property to a context,
     * that it affects subsequent context operations.
     */
    @Override public void runTest() throws Exception {
        setContext(new InitialDirContext(env()));
        addToEnvAndVerifyOldValIsNull(Context.AUTHORITATIVE, "true");
        retrieveAndVerifyAuthData();
        retrieveNonAuthData();
    }

    private void retrieveAndVerifyAuthData() throws NamingException {
        // Ensure that auth data retrieval is OK
        retrieveAndVerifyData(getFqdnUrl(), new String[] { "*" });
    }

    private void retrieveNonAuthData() throws NamingException {
        try {
            // Ensure that nonauth data retrieval cannot be retrieved
            Attributes retAttrs = context()
                    .getAttributes(getForeignFqdnUrl(), new String[] { "*" });
            DNSTestUtils.debug(context().getEnvironment());
            DNSTestUtils.debug(retAttrs);
            throw new RuntimeException(
                    "Failed: Expecting nonauth entry not found "
                            + getForeignFqdnUrl());

        } catch (NameNotFoundException e) {
            System.out.println("Got expected exception: " + e);
        }
    }
}
