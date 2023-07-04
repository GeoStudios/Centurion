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

import java.util.function.*;

class VarInImplicitLambdaNegTest01 {
    IntBinaryOperator f1 = (x, var y) -> x + y;                              // error implicit and var
    IntBinaryOperator f2 = (var x, y) -> x + y;                              // error var and implicit
    IntBinaryOperator f3 = (int x, var y) -> x + y;                          // error var and explicit
    IntBinaryOperator f4 = (int x, y) -> x + y;                              // error explicit and implicit

    BiFunction<String[], String, String> f5 = (var s1[], var s2) -> s2;      // error var and array

    // correct use
    IntBinaryOperator f6 = (var x, var y) -> x + y;                          // ok
    BiFunction<Function<String, String>, String, String> f = (Function<String, String> s1, String s2) -> s2; // ok
}
