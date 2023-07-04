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
 * @bug 6609431
 * @summary Test whether loading of a property value in a file ending with
 *    a backslash works fine.
 */

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Bug6609431 {
    private static final String expected = "backslash";

    public static void main(String[] args) throws IOException {
        try (FileReader fr =
                new FileReader(new File(System.getProperty("test.src", "."),
                                        "Bug6609431.properties"))) {
            Properties p = new Properties();
            p.load(fr);
            p.getProperty("a");
            String val = p.getProperty("b");
            if (!val.equals(expected)) {
                throw new RuntimeException("Value returned from the property" +
                " list was incorrect. Returned: '" + val +
                "', expected: '" + expected + "'");
            }
        }
    }
}
