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

public class TestCaseIf {

    @AliveRange(varName="o", bytecodeStart=9, bytecodeLength=5)
    @AliveRange(varName="o", bytecodeStart=17, bytecodeLength=1)
    void m0(String[] args) {
        Object o;
        if (args[0] != null) {
            o = "";
            o.hashCode();
        }
        o = "";
    }

    @AliveRange(varName="o", bytecodeStart=10, bytecodeLength=5)
    @AliveRange(varName="o", bytecodeStart=18, bytecodeLength=1)
    void m1() {
        Object o;
        int i = 5;
        if (i == 5) {
            o = "";
            o.hashCode();
        }
        o = "";
    }

    @AliveRange(varName="o", bytecodeStart=10, bytecodeLength=5)
    @AliveRange(varName="o", bytecodeStart=18, bytecodeLength=1)
    void m2() {
        Object o;
        int i = 5;
        if (!(i == 5)) {
            o = "";
            o.hashCode();
        }
        o = "";
    }

    @AliveRange(varName="o", bytecodeStart=15, bytecodeLength=5)
    @AliveRange(varName="o", bytecodeStart=23, bytecodeLength=1)
    void m3(String[] args) {
        Object o;
        if (args[0] != null && args[1] != null) {
            o = "";
            o.hashCode();
        }
        o = "";
    }

    @AliveRange(varName="o", bytecodeStart=15, bytecodeLength=5)
    @AliveRange(varName="o", bytecodeStart=23, bytecodeLength=1)
    void m4(String[] args) {
        Object o;
        if (args[0] != null || args[1] != null) {
            o = "";
            o.hashCode();
        }
        o = "";
    }

    @AliveRange(varName="finalLocal", bytecodeStart=11, bytecodeLength=6)
    @AliveRange(varName="used", bytecodeStart=13, bytecodeLength=4)
    void m5(Object o) {
        if (o != null) {
            Object notUsed;
            Object used;
            if (o != null) {
                final Object finalLocal = null;
                used = null;
                if (o == null) {}
            }
        }
    }
}
