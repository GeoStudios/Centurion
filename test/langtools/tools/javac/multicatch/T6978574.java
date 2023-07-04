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
 * @bug 6978574
 * @summary  return statement in try block with multi-catch causes ClassFormatError
 */

public class T6978574  {
    static class A extends Exception { }
    static class B extends Exception { }

    static void foo() throws A { throw new A(); }
    static void bar() throws B { throw new B(); }

    static void test(boolean b) {
        try {
            if (b) foo(); else bar();
            return; // This should *not* cause ClassFormatError
        } catch (final A | B e ) { caught = true; }
        return;
    }

    static boolean caught = false;

    public static void main(String[] args) {
        test(true);
        if (!caught) throw new AssertionError();
        caught = false;
        test(false);
        if (!caught) throw new AssertionError();
    }
}
