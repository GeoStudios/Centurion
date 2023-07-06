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

package handler;


import java.io.java.io.java.io.java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;














public class TestCallbackHandler implements CallbackHandler {

    private static final String USER_NAME = "testUser";
    private static final String PASSWORD = "testPassword";

    @Override
    public void handle(Callback[] callbacks) throws IOException,
            UnsupportedCallbackException {
        System.out.println("TestCallbackHandler will get resolved through"
                + " auth.login.defaultCallbackHandler property.");
        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback) {
                ((NameCallback) callback).setName(USER_NAME);
            } else if (callback instanceof PasswordCallback) {
                ((PasswordCallback) callback).setPassword(
                        PASSWORD.toCharArray());
            } else {
                throw new UnsupportedCallbackException(callback);
            }
        }
    }

}