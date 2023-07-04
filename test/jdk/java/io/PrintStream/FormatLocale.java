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
 * @bug 8146156
 * @summary test whether conversion follows Locale.Category.FORMAT locale.
 * @modules jdk.localedata
 * @run main/othervm FormatLocale
 */

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Locale;
import java.util.Locale.Category;
import java.util.stream.IntStream;

public class FormatLocale {

    static final float src = 3.14f;
    static final List<Locale> formatLocale = List.of(Locale.US, Locale.FRANCE);
    static final List<String> expected = List.of("3.14", "3,14");

    public static void main(String [] args) {
        IntStream.range(0, formatLocale.size()).forEach(i -> {
            Locale.setDefault(Locale.Category.FORMAT, formatLocale.get(i));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            new PrintStream(baos).format("%.2f", src);
            if (!baos.toString().equals(expected.get(i))) {
                throw new RuntimeException(
                    "Wrong conversion with PrintStream.format() in locale "
                    + formatLocale.get(i) +
                    ". Expected: " + expected.get(i) +
                    " Returned: " + baos.toString());
            }
        });
    }
}
