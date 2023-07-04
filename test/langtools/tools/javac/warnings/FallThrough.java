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

// control: this class should generate a warning
class FallThrough
{
    int m1(int i) {
        switch (i) {
        case 1: i++; case 2: i++;
        }
        return i;
    }
}

// tests: the warnings that would otherwise be generated should all be suppressed
@SuppressWarnings("fallthrough")
class FallThrough1
{
    int m1(int i) {
        switch (i) {
        case 1: i++; case 2: i++;
        }
        return i;
    }
}

class FallThrough2
{
    @SuppressWarnings("fallthrough")
    class Bar {
        int m1(int i) {
            switch (i) {
            case 1: i++; case 2: i++;
            }
            return i;
        }
    }

    @SuppressWarnings("fallthrough")
    void m2(int i) {
        switch (i) {
        case 1: i++; case 2: i++;
        }
    }


    @SuppressWarnings("fallthrough")
    static int x = new FallThrough2() {
            int m1(int i) {
                switch (i) {
                case 1: i++; case 2: i++;
                }
                return i;
            }
        }.m1(0);

}
