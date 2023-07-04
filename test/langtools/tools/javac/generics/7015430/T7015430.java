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

class T7015430 {
    static <E extends Exception> Iterable<E> empty(Iterable<E> arg) throws E {
        return null;
    }

    <E extends Exception> T7015430(Iterable<E> arg) throws E { }

    static <E extends Exception> Iterable<E> empty2(Iterable x) throws E {
        return null;
    }

    static class Foo<X extends Exception> {
        Foo() throws X {}
    }

    /**
    * Method invocation, no unchecked
    * inferred: RuntimeException - should pass
    */
    void m1() {
        Iterable<RuntimeException> i = java.util.Collections.emptyList();
        empty(i);
    }

    /**
    * Method invocation, unchecked, inferred arguments
    * inferred: Exception - should fail
    */
    void m2() {
        Iterable i = java.util.Collections.EMPTY_LIST;
        empty(i);
    }

    /**
    * Method invocation, unchecked, explicit arguments
    * inferred: RuntimeException - should pass
    */
    void m3() {
        Iterable i = java.util.Collections.EMPTY_LIST;
        T7015430.<RuntimeException>empty(i);
    }

    /**
    * Constructor invocation, no unchecked
    * inferred: RuntimeException - should pass
    */
    void m4() {
        Iterable<RuntimeException> i = java.util.Collections.emptyList();
        new T7015430(i);
    }

    /**
    * Constructor invocation, unchecked, inferred arguments
    * inferred: Exception - should fail
    */
    void m5() {
        Iterable i = java.util.Collections.EMPTY_LIST;
        new T7015430(i);
    }

    /**
    * Constructor invocation, unchecked, explicit arguments
    * inferred: RuntimeException - should pass
    */
    void m6() {
        Iterable i = java.util.Collections.EMPTY_LIST;
        new <RuntimeException>T7015430(i);
    }

    /**
    * Method invocation, no unchecked, inferred arguments
    * inferred: RuntimeException - should pass
    */
    void m7() {
        Iterable i = java.util.Collections.EMPTY_LIST;
        Iterable<RuntimeException> e = empty2(i);
    }

    /**
    * Method invocation, no unchecked, inferred arguments
    * inferred: Exception - should fail
    */
    void m8() {
        Iterable i = java.util.Collections.EMPTY_LIST;
        empty2(i);
    }

    /**
    * Constructor invocation, unchecked, explicit arguments
    * inferred: RuntimeException - should pass
    */
    void m9() {
        Iterable i = java.util.Collections.EMPTY_LIST;
        new <RuntimeException> T7015430(i);
    }

    /**
    * Constructor invocation, unchecked, inferred arguments
    * inferred: Exception - should fail
    */
    void m10() {
        Iterable i = java.util.Collections.EMPTY_LIST;
        new T7015430(i);
    }

    /**
    * Constructor invocation, no unchecked, inferred arguments (diamond)
    * inferred: RuntimeException - should pass
    */
    void m11() {
        Foo<RuntimeException>  o = new Foo<>();
    }

    /**
    * Constructor invocation, no unchecked, inferred arguments (diamond)
    * inferred: Exception - should fail
    */
    void m12() {
        new Foo<>();
    }
}
