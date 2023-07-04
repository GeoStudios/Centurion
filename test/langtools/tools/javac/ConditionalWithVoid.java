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
public class ConditionalWithVoid {
    public void test(Object o, String s) {
        // Should fail to compile since Object.wait() has a void return type. Poly case.
        System.out.println(o instanceof String ? o.hashCode() : o.wait());
        // Should fail to compile since Object.wait() has a void return type. Standalone case.
        (o instanceof String ? o.hashCode() : o.wait()).toString();
        // Should fail to compile since Object.wait() has a void return type. Poly case.
        System.out.println(switch (s) {case "" -> o.hashCode(); default -> o.wait();});
        // Should fail to compile since Object.wait() has a void return type. Standalone case.
        (switch (s) {case "" -> o.hashCode(); default -> o.wait();}).toString();
    }
}
