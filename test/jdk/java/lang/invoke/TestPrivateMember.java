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

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @test
 * @bug 8007035
 * @summary Test MethodHandle of a private member
 *
 * @run main/othervm -Djava.security.manager=allow TestPrivateMember
 */

public class TestPrivateMember {
    public static void main(String... args) throws Throwable {
        System.setSecurityManager(new SecurityManager());
        TestPrivateMember t = new TestPrivateMember();
        t.test();
    }

    public TestPrivateMember() {
    }

    public void test() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType mt = MethodType.methodType(void.class);
        try {
            Class<?> checkInittedHolder = TestPrivateMemberPackageSibling.class;
            // Original model:  checkInittedHolder = Class.class;
            // Not using Class.checkInitted because it could change without notice.
            MethodHandle mh = lookup.findStatic(checkInittedHolder, "checkInitted", mt);
            throw new RuntimeException("IllegalAccessException not thrown");
        } catch (IllegalAccessException e) {
            // okay
            System.out.println("Expected exception: " + e.getMessage());
        }
    }
}

class TestPrivateMemberPackageSibling {
    private static void checkInitted() { }
}
