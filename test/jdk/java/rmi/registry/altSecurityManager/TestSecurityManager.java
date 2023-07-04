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

/**/

public class TestSecurityManager extends SecurityManager {
    public static final int EXIT_VALUE = 123;

    public TestSecurityManager() {
    }

    public void checkListen(int port) {
        // 4269910: ok, now the registry will *really* go away...
        //
        // the registry needs to listen on sockets so they
        // will exit when they try to do so... this is used as a sign
        // by the main test process to detect that the proper security
        // manager has been installed in the relevant VMs.
        //
        System.exit(EXIT_VALUE);
    }

    public void checkExit(int status) {
        // permit check exit for all code
    }
}
