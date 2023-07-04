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

class TargetType24 {

    interface F<A, B> {
        B f(A a);
    }

    interface FSub<A, B> extends F<A,B> { }

    static class Array<A> {
        boolean forAll(final F<A, Boolean> f) {
            return false;
        }

        String forAll(final FSub<A, String> f) {
            return "";
        }

        String forAll2(final FSub<A, String> f) {
            return "";
        }
    }

    void test(Array<String> as, final Array<Character> ac) {
        final boolean b1 = as.forAll((String s) -> ac.forAll((Character c) -> false)); //ok
        final boolean b2 = as.forAll(s -> ac.forAll(c -> false)); //ambiguous
        final boolean b3 = as.forAll((String s) -> ac.forAll(c -> false)); //ambiguous
        final boolean b4 = as.forAll(s -> ac.forAll((Character c) -> false)); //ambiguous
        final String s1 = as.forAll2(s -> ac.forAll2(c -> "")); //ok
        final boolean b5 = as.forAll(s -> ac.forAll(c -> "" )); //fail
        final String s2 = as.forAll2(s -> ac.forAll2(c -> false)); //fail
        final boolean b6 = as.forAll((F<String, Boolean>)s -> ac.forAll((F<Character, Boolean>)c -> "")); //fail
        final String s3 = as.forAll((FSub<String, String>)s -> ac.forAll((FSub<Character, String>)c -> false)); //fail
    }
}
