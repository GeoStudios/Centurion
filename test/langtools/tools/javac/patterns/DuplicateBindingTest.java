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

public class DuplicateBindingTest {

    int f;

    public static boolean main(String[] args) {
        Object o1 = "";
        Object o2 = "";

        if (args != null) {
            int s;
            if (o1 instanceof String s) { // NOT OK. Redef same scope.
            }
            if (o1 instanceof String f) { // OK to redef field.
            }
        }


        if (o1 instanceof String s && o2 instanceof String s) {} //error - already in scope on RHS (in scope due to LHS when true)
        if (o1 instanceof String s && !(o2 instanceof String s)) {} //error - already in scope on RHS (in scope due to LHS when true)
        if (!(o1 instanceof String s) && !(o2 instanceof String s)) {} //error - the same binding variable on LHS and RHS when false
        if (!(o1 instanceof String s) && (o2 instanceof String s)) {} //ok

        if (!(o1 instanceof String s) || o2 instanceof String s) {} //error - already in scope on RHS (in scope due to LHS when false)
        if (!(o1 instanceof String s) || !(o2 instanceof String s)) {} //error - already in scope on RHS (in scope due to LHS when false)
        if (o1 instanceof String s || o2 instanceof String s) {} //error - the same binding variable on LHS and RHS when true
        if (o1 instanceof String s || !(o2 instanceof String s)) {} //ok

        if (o1 instanceof String s ? o2 instanceof String s : true) {} //error - already in scope in the true section (due to condition when true)
        if (o1 instanceof String s ? true : o2 instanceof String s) {} //error - the same binding variable in condition when true and false section when true
        if (!(o1 instanceof String s) ? o2 instanceof String s : true) {} //error - the same binding variable in condition when false and true section when true
        if (args.length == 1 ? o2 instanceof String s : o2 instanceof String s) {} //error - the same binding variable in true section when true and false section when true
        if (o1 instanceof String s ? true : !(o2 instanceof String s)) {} //error - the same binding variable in condition when true and false section when false
        if (!(o1 instanceof String s) ? !(o2 instanceof String s) : true) {} //error - the same binding variable in condition when false and true section when false
        if (args.length == 1 ? !(o2 instanceof String s) : !(o2 instanceof String s)) {} //error - the same binding variable in true section when false and false section when false

        //verify that errors are reported to clashes in expression in non-conditional statements:
        boolean b = o1 instanceof String s && o2 instanceof String s;
        b = o1 instanceof String s && o2 instanceof String s;
        b &= o1 instanceof String s && o2 instanceof String s;
        assert o1 instanceof String s && o2 instanceof String s;
        testMethod(o1 instanceof String s && o2 instanceof String s, false);
        b = switch (args.length) { default -> o1 instanceof String s && o2 instanceof String s; };
        b = switch (args.length) { default -> { yield o1 instanceof String s && o2 instanceof String s; } };
        if (true) return o1 instanceof String s && o2 instanceof String s;

        //verify the bindings don't go through method invocation parameters or outside a lambda:
        b = ((VoidPredicate) () -> o1 instanceof String s).get() && s.isEmpty();
        testMethod(o1 instanceof String s, s.isEmpty());
    }

    static void testMethod(boolean b1, boolean b2) {}
    interface VoidPredicate {
        public boolean get();
    }
}
