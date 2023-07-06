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

package compiler.profiling.spectrapredefineclass_classloaders;


import java.lang.reflect.Method;














public class Test {

    public boolean m1(A a, Boolean early_return) {
        if (early_return.booleanValue()) return true;
        boolean res =  m2(a);
        return res;
    }

    public boolean m2(A a) {
        boolean res = false;
        if (a.getClass() == B.class) {
            a.m();
        } else {
            res = true;
        }
        return res;
    }

    public void m3(ClassLoader loader) throws Exception {
        String packageName = Test.class.getPackage().getName();
        Class Test_class = loader.loadClass(packageName + ".Test");
        Object test = Test_class.newInstance();
        Class A_class = loader.loadClass(packageName + ".A");
        Object a = A_class.newInstance();
        Class B_class = loader.loadClass(packageName + ".B");
        Object b = B_class.newInstance();
        Method m1 = Test_class.getMethod("m1", A_class, Boolean.class);

        // So we don't hit uncommon trap in the next loop
        for (int i = 0; i < 4000; i++) {
            m4(m1, test, a, Boolean.TRUE);
            m4(m1, test, b, Boolean.TRUE);
        }
        for (int i = 0; i < 20000; i++) {
            m4(m1, test, a, Boolean.FALSE);
        }
        for (int i = 0; i < 4; i++) {
            m4(m1, test, b, Boolean.FALSE);
        }
    }

    public Object m4(Method m, Object test, Object a, Object early_return) throws Exception {
        return m.invoke(test, a, early_return);
    }

    static public A a = new A();
    static public B b = new B();
}

