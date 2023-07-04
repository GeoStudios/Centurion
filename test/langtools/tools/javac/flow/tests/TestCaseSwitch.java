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

public class TestCaseSwitch {

    @AliveRange(varName="o", bytecodeStart=31, bytecodeLength=16)
    @AliveRange(varName="o", bytecodeStart=50, bytecodeLength=15)
    @AliveRange(varName="o", bytecodeStart=68, bytecodeLength=1)
    @AliveRange(varName="oo", bytecodeStart=39, bytecodeLength=8)
    @AliveRange(varName="uu", bytecodeStart=59, bytecodeLength=6)
    void m1(String[] args) {
        Object o;
        switch (args.length) {
            case 0:
                    o = "0";
                    o.hashCode();
                    Object oo = "oo";
                    oo.hashCode();
                    break;
            case 1:
                    o = "1";
                    o.hashCode();
                    Object uu = "uu";
                    uu.hashCode();
                    break;
        }
        o = "return";
    }

    @AliveRange(varName="o", bytecodeStart=95, bytecodeLength=18)
    @AliveRange(varName="o", bytecodeStart=116, bytecodeLength=15)
    @AliveRange(varName="o", bytecodeStart=134, bytecodeLength=1)
    @AliveRange(varName="oo", bytecodeStart=104, bytecodeLength=9)
    @AliveRange(varName="uu", bytecodeStart=125, bytecodeLength=6)
    void m2(String[] args) {
        Object o;
        switch (args[0]) {
            case "string0":
                    o = "0";
                    o.hashCode();
                    Object oo = "oo";
                    oo.hashCode();
                    break;
            case "string1":
                    o = "1";
                    o.hashCode();
                    Object uu = "uu";
                    uu.hashCode();
                    break;
        }
        o = "return";
    }

    @AliveRange(varName="o", bytecodeStart=35, bytecodeLength=8)
    @AliveRange(varName="o", bytecodeStart=46, bytecodeLength=8)
    @AliveRange(varName="o", bytecodeStart=78, bytecodeLength=5)
    @AliveRange(varName="o", bytecodeStart=86, bytecodeLength=1)
    @AliveRange(varName="oo", bytecodeStart=56, bytecodeLength=16)
    void m3(int i) {
        Object o;
        switch (i) {
            case 0:
                    o = "0";
                    o.hashCode();
                    break;
            case 1:
                    o = "1";
                    o.hashCode();
                    break;
            case 2:
                int oo = i;
                if (oo > 1) {
                    System.out.println("greater");
                }
                break;
            case 3:
                int uu = i;
            default:
                    o = "default";
                    o.hashCode();
        }
        o = "finish";
    }

    @AliveRange(varName="oCache", bytecodeStart=30, bytecodeLength=6)
    @AliveRange(varName="cache", bytecodeStart=41, bytecodeLength=3)
    @AliveRange(varName="cache", bytecodeStart=54, bytecodeLength=2)
    @AliveRange(varName="service", bytecodeStart=39, bytecodeLength=5)
    Object m4(int i) {
        Object cache;
        switch (i) {
            case 0:
                Object oCache = null;
                if (oCache != null) {
                    return oCache;
                }
            case 1:
                Object service = null;
                cache = null;
                break;
            default:
                throw new AssertionError("");
            }
        return cache;
    }

}
