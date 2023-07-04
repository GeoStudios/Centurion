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

import java.io.*;

public class ResourceRedecl {

    public void test() {
        // compiler error if name of an exception param is redeclared within the Block of the catch clause as a local var;
        // or as an exception param of a catch clause in a try statement;
        // or as a resource in a try-with-resources statement
        try {
        } catch (Exception exParam1) {
            Object exParam1 = new Object();
            try (java.io.FileInputStream exParam1 = new java.io.FileInputStream("foo.txt")) {
                Object exParam1 = new Object();
            } catch (IOException exParam1) {
            }
        }

        // compiler error if resource is redeclared within the try Block as a local var
        // or as an exception param of a catch clause in a try statement
        try (java.io.FileInputStream exParam2 = new java.io.FileInputStream("bar.txt")) {
            Object exParam2 = new Object();
            try (BufferedReader br = new BufferedReader(new FileReader("zee.txt"))) {
            } catch (IOException exParam2) {
            }
        } catch (Exception ex) {
        }
    }
}

