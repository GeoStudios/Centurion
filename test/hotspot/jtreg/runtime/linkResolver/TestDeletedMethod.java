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
 * @bug 8211065
 * @summary Test that deleting a subclass method implementation results in
 * execution of a superclass implementation - if it is accessible.
 * @compile TestDeletedMethod.java
 * @compile TestDeletedMethod_Sub.jcod TestDeletedMethod_Super.jcod
 * @run main/othervm -XX:+RelaxAccessControlCheck TestDeletedMethod
 */

// The access control relaxation was originally done to ensure an assertion
// in the nestmate logic would not trigger unintentionally because it
// assumed only nestmates could be involved. The assertion no longer exists
// but we keep the test as a non-nestmate version of the situation.

/* package */ class TestDeletedMethod_Super {
    public static final int ID = 2;
    private static int m() {
        System.out.println("Super.m");
        return ID;
    }
}

/* package */ class TestDeletedMethod_Sub extends TestDeletedMethod_Super {
    public static final int ID = 1;
    // At runtime this implementation is not present
    private static int m() {
        System.out.println("Sub.m");
        return ID;
    }
    public static int test() {
        return TestDeletedMethod_Sub.m();
    }
}

public class TestDeletedMethod {
    public static void main(String[] args) {
        int x = TestDeletedMethod_Sub.test();
        if (x != TestDeletedMethod_Super.ID)
            throw new RuntimeException("Wrong method invoked: " + x);
    }
}
