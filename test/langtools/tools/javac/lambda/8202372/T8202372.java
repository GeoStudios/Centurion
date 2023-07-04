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
class T8202372 {

    interface NonVoidFunc {
        String m();
    }

    interface VoidFunc {
        void m();
    }

    interface ParamFunc {
        void m(String s);
    }

    public void addVoid(VoidFunc v) {}
    public void addNonVoid(NonVoidFunc nv) {}
    public void addParam(ParamFunc p) {}

    void testVoid(T8202372 test) {
        test.addVoid(() -> "");
        test.addVoid(() -> { return ""; });
        test.addVoid(() -> { });
        test.addVoid(() -> { return; });
    }

    void testNonVoid(T8202372 test) {
        test.addNonVoid(() -> "");
        test.addNonVoid(() -> { return ""; });
        test.addNonVoid(() -> { });
        test.addNonVoid(() -> { return; });
    }

    void testParam(T8202372 test) {
        test.addParam(() -> {});
        test.addParam((String x) -> { });
        test.addParam((String x1, String x2) -> { });
        test.addParam((int x) -> { });
    }
}