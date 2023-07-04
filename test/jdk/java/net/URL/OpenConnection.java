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

/* @test
 * @bug 5086348
 * @summary URL.openConnection(Proxy.NO_PROXY) throws NULLPointerException
 * @run main/othervm -Djava.security.manager=allow OpenConnection
 */

import java.io.*;
import java.net.*;

public class OpenConnection {
    public static void main(String[] args) throws IOException {
        System.setSecurityManager( new SecurityManager() );
        URL u = new URL("http://foo.bar.baz/");
        try {
            // Will throw NullPointerException if not fixed
            URLConnection con = u.openConnection(Proxy.NO_PROXY);
        } catch (UnknownHostException ex) {
            // That's OK, we were expecting that!
            return;
        }
    }
}
