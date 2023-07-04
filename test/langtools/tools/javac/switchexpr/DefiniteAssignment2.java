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
public class DefiniteAssignment2 {

    public static void main(String[] args) {
        int a = 0;
        boolean b = true;
        boolean t;

        {
            int x;

            t = (b && switch(a) {
                case 0: yield (x = 1) == 1 || true;
                default: yield false;
            }) || x == 1;
        }

        {
            int x;

            t = (switch(a) {
                case 0: yield (x = 1) == 1;
                default: yield false;
            }) || x == 1;
        }

        {
            int x;

            t = (switch(a) {
                case 0: x = 1; yield true;
                case 1: yield (x = 1) == 1;
                default: yield false;
            }) || x == 1;
        }

        {
            int x;

            t = (switch(a) {
                case 0: yield true;
                case 1: yield (x = 1) == 1;
                default: yield false;
            }) && x == 1;
        }

        {
            int x;

            t = (switch(a) {
                case 0: yield false;
                case 1: yield isTrue() || (x = 1) == 1;
                default: yield false;
            }) && x == 1;
        }

        {
            int x;

            t = (switch(a) {
                case 0: yield false;
                case 1: yield isTrue() ? true : (x = 1) == 1;
                default: yield false;
            }) && x == 1;
        }

        {
            final int x;

            t = (switch(a) {
                case 0: yield false;
                case 1: yield isTrue() ? true : (x = 1) == 1;
                default: yield false;
            }) && (x = 1) == 1;
        }
    }

    private static boolean isTrue() {
        return true;
    }

}
