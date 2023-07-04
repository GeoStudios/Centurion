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

package vm.mlvm.mixed.func.regression.b7129034;

import java.util.List;

import java.lang.invoke.*;
import java.lang.reflect.*;

import vm.mlvm.share.MlvmTest;

public class Test extends MlvmTest {

    public Test obj;
    public String str;

    public static void main(String[] args) { MlvmTest.launch(args); }

    @Override
    public boolean run() throws Throwable {
        MethodHandles.Lookup l = MethodHandles.publicLookup();
        Field field = Test.class.getField("str");
        MethodHandle mh = l.unreflectSetter(field);
        MethodHandle filter = l.unreflectGetter(Test.class.getField("obj"));
        mh = MethodHandles.filterArguments(mh, 0, filter);
        try {
            mh.invokeExact(new Test(), "hello");
        } catch (NullPointerException ignore) {
            System.out.println("PASSED: Expected NPE thrown, no crash");
        }
        return true;
    }
}
