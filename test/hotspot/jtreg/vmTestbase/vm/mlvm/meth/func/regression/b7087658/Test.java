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

package vm.mlvm.mixed.func.regression.b7087658;

import java.util.List;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import vm.mlvm.share.MlvmTest;

interface A {
   Iterable m(List<String> ls);
}

interface B {
   Iterable<String> m(List l);
}

interface AB extends A, B { }

interface AA extends A { }

public class Test extends MlvmTest {

    public static void main(String[] args) { MlvmTest.launch(args); }

    @Override
    public boolean run() throws Throwable {
        MethodHandle mh1 = MethodHandles.lookup().findVirtual(A.class, "m", MethodType.methodType(Iterable.class, List.class));
        MethodHandle mh2 = MethodHandles.lookup().findVirtual(B.class, "m", MethodType.methodType(Iterable.class, List.class));
        MethodHandle mh3 = MethodHandles.lookup().findVirtual(AB.class, "m", MethodType.methodType(Iterable.class, List.class));
        MethodHandle mh4 = MethodHandles.lookup().findVirtual(AA.class, "m", MethodType.methodType(Iterable.class, List.class));
        return true;
    }
}
