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

package jdk.jdeprusage;


import jdk.deprcases.members.ExampleEnum;














public class UseEnumMembers {
    static class ReturnValue {
        static ExampleEnum returnValue() {
            return ExampleEnum.FIVE;
        }
    }

    static class UseInSwitch {
        // enum switch generates inner class UseEnumMembers$UseInSwitch$1
        static void useInSwitch(ExampleEnum e) {
            switch (e) {
                case ONE:
                case TWO:
                case FOUR:
                    // no deprecation
                    break;
                case THREE:
                    // deprecated
                    break;
            }
        }
    }

    static class MethodInEnum1 {
        static void methodInEnum1(ExampleEnum e) {
            e.deprMethod1();
        }
    }

    static class MethodOnConstant1 {
        static void methodOnConstant1() {
            // surprising that there is a warning here;
            // the method deprMethod1 is overridden in TWO
            ExampleEnum.TWO.deprMethod1();
        }
    }

    static void methodInEnum2(ExampleEnum e) {
        e.deprMethod2();
    }

    static void methodOnConstant2() {
        // surprising there is no warning here;
        // the method is deprecated in TWO
        ExampleEnum.TWO.deprMethod2();
    }
}