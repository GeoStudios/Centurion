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

package vm.mlvm.meth.share;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class SimpleOpMethodHandles {

    public static boolean eq(Object o1, Object o2) {
        return o1.equals(o2);
    }

    public static boolean not(boolean a) {
        return ! a;
    }

    public static MethodHandle notMH() throws NoSuchMethodException, IllegalAccessException {
        return MethodHandles.lookup().findStatic(SimpleOpMethodHandles.class, "not", MethodType.methodType(boolean.class, boolean.class));
    }

    public static MethodHandle eqMH() throws NoSuchMethodException, IllegalAccessException {
        return MethodHandles.lookup().findStatic(SimpleOpMethodHandles.class, "eq", MethodType.methodType(boolean.class, Object.class, Object.class));
    }

}
