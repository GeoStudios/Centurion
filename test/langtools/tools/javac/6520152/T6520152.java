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
 * @bug     6520152
 * @summary ACC_FINAL flag for anonymous classes shouldn't be set
 * @compile T.java
 * @run main/othervm T6520152
 */

import java.lang.reflect.Method;
import static java.lang.reflect.Modifier.*;

public class T6520152 {
    public static void main(String [] args) throws Exception {
        Class clazz = Class.forName("T$1");
        if ((clazz.getModifiers() & FINAL) != 0) {
            throw new RuntimeException("Failed: " + clazz.getName() + " shouldn't be marked final.");
        }
    }
}
