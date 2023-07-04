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
 * @bug 8227384
 * @summary C2 compilation fails with "graph should be schedulable" when running with -XX:-EliminateLocks
 * @requires vm.compiler2.enabled & !vm.graal.enabled
 *
 * @run main/othervm -XX:-EliminateLocks TestEliminateLocksOffCrash
 */

public class TestEliminateLocksOffCrash {
    public static void main(String[] args) {
        for (int i = 0; i < 20_000; i++) {
            try {
                test();
            } catch (Exception e) {
            }
        }
    }

    private static void test() throws Exception {
        Object obj = new Object();
        synchronized (obj) {
            throw new Exception();
        }
    }
}
