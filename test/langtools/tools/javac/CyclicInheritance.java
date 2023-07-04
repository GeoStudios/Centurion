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






class C1 extends C1 {}                  // ERROR - Cyclic inheritance

class C11 extends C12 {}                // ERROR - Cyclic inheritance
class C12 extends C11 {}                // error in previous line could correctly be reported here as well

interface I1 extends I1 {}              // ERROR - Cyclic inheritance

interface I11 extends I12 {}            // ERROR - Cyclic inheritance
interface I12 extends I11 {}            // error in previous line could correctly be reported here as well

//-----

class C211 implements C211.I {          // ERROR - may change pending resoluation of 4087020
        interface I {};                 // error in previous line could correctly be reported here as well
}

class C212 extends C212.C {             // ERROR - Cyclic inheritance, subclass cannot enclose superclass
        static class C {};              // error in previous line could correctly be reported here as well
}


class C221 implements C221.I {          // ERROR - Cannot access C21 (private)
        private interface I {};         // error in previous line could correctly be reported here as well
}

class C222 extends C222.C {             // ERROR - Cannot access C22 (private)
        private static class C {};      // error in previous line could correctly be reported here as well
}

//-----

class C3 {
    class A {
        class B extends A {}
    }
}

class C4 {
    class A extends B {}
    class B {
        class C extends A {}
    }
}
