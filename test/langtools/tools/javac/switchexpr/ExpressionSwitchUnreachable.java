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

public class ExpressionSwitchUnreachable {

    public static void main(String[] args) {
        int z = 42;
        int i = switch (z) {
            case 0 -> {
                yield 42;
                System.out.println("Unreachable");  //Unreachable
            }
            default -> 0;
        };
        i = switch (z) {
            case 0 -> {
                yield 42;
                yield 42; //Unreachable
            }
            default -> 0;
        };
        i = switch (z) {
            case 0:
                System.out.println("0");
                yield 42;
                System.out.println("1");    //Unreachable
            default : yield 42;
        };
        i = switch (z) {
            case 0 -> 42;
            default -> {
                yield 42;
                System.out.println("Unreachable"); //Unreachable
            }
        };
        i = switch (z) {
            case 0: yield 42;
            default:
                System.out.println("0");
                yield 42;
                System.out.println("1");    //Unreachable
        };
        i = switch (z) {
            case 0:
            default:
                System.out.println("0");
                yield 42;
                System.out.println("1");    //Unreachable
        };
    }


}
