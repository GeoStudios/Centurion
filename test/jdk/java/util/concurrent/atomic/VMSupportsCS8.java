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
 * @bug 4992443 4994819
 * @modules java.base/java.util.concurrent.atomic:open
 * @run main VMSupportsCS8
 * @summary Checks that the value of VMSupportsCS8 matches system properties.
 */

import java.lang.reflect.Field;

public class VMSupportsCS8 {
    public static void main(String[] args) throws Exception {
        String isalist = System.getProperty("sun.cpu.isalist");
        if (isalist != null && isalist.matches
            (".*\\b(pentium_pro|ia64|amd64).*")
            ||
            System.getProperty("os.arch").matches
            (".*\\b(ia64|amd64).*")) {

            System.out.println("This system is known to have hardware CS8");

            Class klass = Class.forName("java.util.concurrent.atomic.AtomicLong");
            Field field = klass.getDeclaredField("VM_SUPPORTS_LONG_CAS");
            field.setAccessible(true);
            boolean VMSupportsCS8 = field.getBoolean(null);
            if (! VMSupportsCS8)
                throw new Exception("Unexpected value for VMSupportsCS8");
        }
    }
}
