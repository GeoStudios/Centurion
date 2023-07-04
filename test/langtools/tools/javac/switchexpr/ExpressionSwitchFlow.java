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

public class ExpressionSwitchFlow {
    private String test1(int i) {
        return switch (i) {
            case 0 -> {}
            default -> { yield "other"; }
        };
    }
    private String test2(int i) {
        return switch (i) {
            case 0 -> {
            }
            default -> "other";
        };
    }
    private String test3(int i) {
        return switch (i) {
            case 0 -> {}
            case 1 -> "";
            default -> throw new IllegalStateException();
        };
    }
    private String test4(int i) {
        return switch (i) {
            case 0 -> { yield "other"; }
            default -> {}
        };
    }
    private String test5(int i) {
        return switch (i) {
            case 0 -> "other";
            default -> {}
        };
    }
    private String test6(int i) {
        return switch (i) {
            case 0 -> throw new IllegalStateException();
            case 1 -> "";
            default -> {}
        };
    }
    private String test7(int i) {
        return switch (i) {
            case 0: throw new IllegalStateException();
            case 1: yield "";
            default:
        };
    }
    private String test8(int i) {
        return switch (i) {
            case 1: yield "";
            case 0: i++;
            default: {
            }
        };
    }
    private String test9(int i) {
        return switch (i) {
            case 1: yield "";
            case 0:
            default:
                System.err.println();
        };
    }
}
