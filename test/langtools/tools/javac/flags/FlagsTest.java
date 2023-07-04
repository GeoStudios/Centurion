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

/**
 * @test
 * @bug 8211138
 * @summary Missing Flag enum constants
 * @library /tools/javac/lib
 * @modules jdk.compiler/com.sun.tools.javac.code
 * @run main FlagsTest
 */
import com.sun.tools.javac.code.Flags;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FlagsTest {
    public static void main(String[] args) throws IllegalAccessException {
        for (Field f : Flags.class.getFields()) {
            if (!Modifier.isStatic(f.getModifiers())) {
                continue;
            }
            long flag = ((Number) f.get(null)).longValue();
            try {
                Flags.asFlagSet(flag);
            } catch (AssertionError e) {
                throw new AssertionError("missing Flags enum constant for: " + f.getName(), e);
            }
        }
    }
}
