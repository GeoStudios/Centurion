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
class Private08 {
    interface I {
        private void poo() {}
        private int foo() { return 0; }
        int goo();
        default int doo() { return foo(); }
        private public int bad(); // 9.4 illegal combination of modifiers
        private abstract int verybad(); // 9.4 illegal combination of modifiers
        private default int alsobad() { return foo(); } // 9.4 illegal combination of modifiers
        protected void blah();
        private void missingBody(); // private methods are not abstract.
    }
}

class Private08_01 {
    int y = ((Private08.I) null).foo();   // 9.4 test that private methods are not implicitly public.
    interface J extends Private08.I {
        default void foo() { // foo not inherited from super, change of return type is OK.
            super.foo();  // super in static context - Error.
        }
        private int doo() { return 0; } // private cannot override public.
    };

    Private08.I i = new Private08.I () {
        public void foo() { // foo not inherited from super, change of return type is OK.
            super.foo();  // super's foo not inherited, NOT OK.
        }
        private int doo() { return 0; } // private cannot override public.
    }; // should not complain about poo() not being implemented.
}

