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
 * @bug 8206986
 * @summary Verify behavior when an intersection type is inferred for switch expression.
 * @compile ExpressionSwitchIntersectionTypes.java
 * @run main ExpressionSwitchIntersectionTypes
 */

public class ExpressionSwitchIntersectionTypes<X  extends java.io.Serializable & Runnable> {

    void test1(int i, X x) {
        Runnable r1 = switch (i) {
            default -> x;
        };
        r1.run();
    }

    void test2(int i, X x) {
        (switch (i) {
            default -> x;
        }).run();
    }

    public static void main(String[] args) {
        ExpressionSwitchIntersectionTypes t = new ExpressionSwitchIntersectionTypes();
        try {
            t.test1(0, "");
            throw new AssertionError("Expected exception didn't occur.");
        } catch (ClassCastException ex) {
            //good
        }
        try {
            t.test2(0, "");
            throw new AssertionError("Expected exception didn't occur.");
        } catch (ClassCastException ex) {
            //good
        }
    }

}
