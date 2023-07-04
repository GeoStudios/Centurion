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

import java.util.Objects;
import java.util.function.Function;

public class SwitchStatementArrow {
    public static void main(String... args) {
        new SwitchStatementArrow().run();
    }

    private void run() {
        runTest(this::statement1);
        runTest(this::scope);
    }

    private void runTest(Function<T, String> print) {
        check(T.A,  print, "A");
        check(T.B,  print, "B-C");
        check(T.C,  print, "B-C");
        try {
            print.apply(T.D);
            throw new AssertionError();
        } catch (IllegalStateException ex) {
            if (!Objects.equals("D", ex.getMessage()))
                throw new AssertionError(ex);
        }
        check(T.E,  print, "other");
    }

    private String statement1(T t) {
        String res;

        switch (t) {
            case A -> { res = "A"; }
            case B, C -> res = "B-C";
            case D -> throw new IllegalStateException("D");
            default -> { res = "other"; break; }
        }

        return res;
    }

    private String scope(T t) {
        String res;

        switch (t) {
            case A -> { String r = "A"; res = r; }
            case B, C -> {String r = "B-C"; res = r; }
            case D -> throw new IllegalStateException("D");
            default -> { String r = "other"; res = r; break; }
        }

        return res;
    }

    private int r;

    private void check(T t, Function<T, String> print, String expected) {
        String result = print.apply(t);
        if (!Objects.equals(result, expected)) {
            throw new AssertionError("Unexpected result: " + result);
        }
    }

    enum T {
        A, B, C, D, E;
    }
}
