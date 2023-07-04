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

public class TestCaseIfElse {

    @AliveRange(varName="o", bytecodeStart=9, bytecodeLength=8)
    @AliveRange(varName="o", bytecodeStart=20, bytecodeLength=9)
    void m0(String[] args) {
        Object o;
        if (args[0] != null) {
            o = "then";
            o.hashCode();
        } else {
            o = "else";
            o.hashCode();
        }
        o = "finish";
    }

    @AliveRange(varName="o", bytecodeStart=10, bytecodeLength=8)
    @AliveRange(varName="o", bytecodeStart=21, bytecodeLength=9)
    void m1() {
        Object o;
        int i = 5;
        if (i == 5) {
            o = "then";
            o.hashCode();
        } else {
            o = "else";
            o.hashCode();
        }
        o = "finish";
    }

    @AliveRange(varName="o", bytecodeStart=10, bytecodeLength=8)
    @AliveRange(varName="o", bytecodeStart=21, bytecodeLength=9)
    void m2() {
        Object o;
        int i = 5;
        if (i != 5) {
            o = "then";
            o.hashCode();
        } else {
            o = "else";
            o.hashCode();
        }
        o = "finish";
    }

    @AliveRange(varName="o", bytecodeStart=11, bytecodeLength=3)
    @AliveRange(varName="o", bytecodeStart=17, bytecodeLength=2)
    Object m3(boolean cond1, boolean cond2) {
        Object o;
        if (cond1) {
            if (cond2) {
                o = "then";
            } else {
                o = "else";
                return null;
            }
        }
        return null;
    }

    @AliveRange(varName="i", bytecodeStart=6, bytecodeLength=2)
    int m4(boolean flag) {
        int i;
        label:
        {
            if (flag) {
                i = 1;
            } else {
                break label;
            }
            return i;
        }
        return -1;
    }
}
