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
 *
 * @bug 4099970
 *
 * @summary this tests that the temp dir variable, java.io.tmpdir is
 * properly initialized.
 *
 * @author Benjamin Renaud
 */

import java.io.File;
import java.io.IOException;

public class CheckTempDir {

    public static void main (String[] args) {
        String tmpdir = null;
        if ((tmpdir = System.getProperty("java.io.tmpdir")) == null) {
            throw new RuntimeException("java.io.tmpdir is not initialized");
        } else {
            System.out.println("checked tmpdir is not null: " + tmpdir);
        }
    }
}
