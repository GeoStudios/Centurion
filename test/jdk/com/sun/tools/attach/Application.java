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
 * A simple "Application" used by the Attach API unit tests. This application is
 * launched by the test.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Application {
    public static final String READY_MSG="ready";
    public static final String SHUTDOWN_MSG="shutdown";

    public static void main(String args[]) throws Exception {
        System.out.println(READY_MSG);
        System.out.flush();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (!br.readLine().equals(SHUTDOWN_MSG));
        }
    }
}
