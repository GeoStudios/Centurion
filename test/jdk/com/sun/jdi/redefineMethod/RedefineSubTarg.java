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
 */ class RedefineSubTarg {        // 1 - do not change line numbers
    void top() {
        return;                // 3
    }
    void nemcp2(int eights) {
        top();                 // 6
        return;                // 7
    }
    void nemcp1() {
        int rot = 4;
        nemcp2(888);           // 11
        return;                // 12
    }
    void emcp2() {
        nemcp1();              // 15
        return;                // 16
    }
    void emcp1(int myArg) {
        int paramy = 12;
        emcp2();               // 20
        return;                // 21
    }
    void bottom() {
        emcp1(56);             // 24
        return;                // 25
    }
    static void stnemcp() {
        (new RedefineSubTarg()).bottom(); // 28
                               // 29
        return;                // 30
    }
    static void stemcp() {
        stnemcp();             // 33
        return;                // 34
    }
}
