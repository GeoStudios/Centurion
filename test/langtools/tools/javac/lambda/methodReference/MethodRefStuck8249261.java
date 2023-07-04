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
class MethodRefStuck8249261 {

    void p(int padding) {}

    static boolean t() {
        return true;
    }

    private void test() {
        p(MethodRefStuck8249261::t);
        p((MethodRefStuck8249261::t));
        p(MethodRefStuck8249261::t + 1);
        p(MethodRefStuck8249261::t ? 1 : 0);
        p(true ? MethodRefStuck8249261::t : 0);
        p(switch (MethodRefStuck8249261::t) { default -> 0; });
        p(() -> true);
        p((() -> true));
        p((() -> true) + 1);
        p((() -> true) ? 1 : 0);
        p(true ? (() -> true) : 0);
        p(switch ((() -> true)) { default -> 0; });
  }
}
