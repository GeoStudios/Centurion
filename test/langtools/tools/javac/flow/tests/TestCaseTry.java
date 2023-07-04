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

import java.io.BufferedReader;
import java.io.FileReader;

public class TestCaseTry {

    @AliveRange(varName="o", bytecodeStart=3, bytecodeLength=8)
    @AliveRange(varName="o", bytecodeStart=15, bytecodeLength=1)
    void m0(String[] args) {
        Object o;
        try {
            o = "";
            o.hashCode();
        } catch (RuntimeException e) {}
        o = "";
    }

    @AliveRange(varName="o", bytecodeStart=3, bytecodeLength=16)
    @AliveRange(varName="o", bytecodeStart=23, bytecodeLength=8)
    @AliveRange(varName="o", bytecodeStart=35, bytecodeLength=11)
    void m1() {
        Object o;
        try {
            o = "";
            o.hashCode();
        } catch (RuntimeException e) {
        }
        finally {
            o = "finally";
            o.hashCode();
        }
        o = "";
    }

    @AliveRange(varName="o", bytecodeStart=3, bytecodeLength=16)
    @AliveRange(varName="o", bytecodeStart=23, bytecodeLength=16)
    @AliveRange(varName="o", bytecodeStart=43, bytecodeLength=11)
    void m2() {
        Object o;
        try {
            o = "";
            o.hashCode();
        } catch (RuntimeException e) {
            o = "catch";
            o.hashCode();
        }
        finally {
            o = "finally";
            o.hashCode();
        }
        o = "";
    }

    @AliveRange(varName="o", bytecodeStart=20, bytecodeLength=12)
    @AliveRange(varName="o", bytecodeStart=50, bytecodeLength=3)
    @AliveRange(varName="o", bytecodeStart=57, bytecodeLength=1)
    void m3() {
        Object o;
        try (BufferedReader br =
                  new BufferedReader(new FileReader("aFile"))) {
            o = "inside try";
            o.hashCode();
        } catch (Exception e) {}
        o = "";
    }

    @AliveRange(varName="o", bytecodeStart=12, bytecodeLength=43)
    @AliveRange(varName="o", bytecodeStart=59, bytecodeLength=1)
    void m4() {
        String o;
        try (BufferedReader br =
                  new BufferedReader(new FileReader(o = "aFile"))) {
            o = "inside try";
            o.hashCode();
        } catch (Exception e) {}
        o = "";
    }
}
