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
public class MatchBindingScopeTest {

    static Integer i = 42;
    static String s = "Hello";
    static Object o1 = s;
    static Object o2 = i;

    public static void main(String[] args) {

        if (o1 instanceof String j && j.length() == 5) { // OK
            System.out.println(j); // OK
        } else {
            System.out.println(j); // NOT OK
        }

        // NOT OK, name reused.
        if (o1 instanceof String j && o2 instanceof Integer j) {
        }

        if (o1 instanceof String j && j.length() == 5 && o2 instanceof Integer k && k == 42) { // OK
            System.out.println(j); // OK
            System.out.println(k); // OK
        } else {
            System.out.println(j); // NOT OK
            System.out.println(k); // NOT OK
        }

        if (o1 instanceof String j || j.length() == 5) { // NOT OK
            System.out.println(j); // NOT OK
        }

        if (o1 instanceof String j || o2 instanceof Integer j) { // NOT OK, types differ
            System.out.println(j);
        } else {
            System.out.println(j); // NOT OK.
        }

        while (o1 instanceof String j && j.length() == 5) { // OK
            System.out.println(j); // OK
        }

        while (o1 instanceof String j || true) {
            System.out.println(j); // Not OK
        }

        for (; o1 instanceof String j; j.length()) { // OK
            System.out.println(j); // OK
        }

        for (; o1 instanceof String j || true; j.length()) { // NOT OK
            System.out.println(j); // Not OK
        }

        int x = o1 instanceof String j ?
                      j.length() : // OK.
                      j.length();  // NOT OK.

        x = !(o1 instanceof String j) ?
                      j.length() : // NOT OK.
                      j.length();  // OK.
    }
}
