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

class LambdaScope04 {

    interface SAM {
        void m(Object o);
    }

    static SAM field1 = field1->{}; //ok
    static SAM field2 = param->{ Object field2 = null; }; //ok

    SAM field3 = field3->{}; //ok
    SAM field4 = param->{ Object field4 = null; }; //ok

    {
        Object local = null;
        SAM s1 = local->{}; //error
        SAM s2 = param->{ Object local = null; }; //error
    }

    static {
        Object local = null;
        SAM s1 = local->{}; //error
        SAM s2 = param->{ Object local = null; }; //error
        SAM s3 = field1->{ Object field_2 = null; }; //ok
    }

    void testLocalInstance() {
        Object local = null;
        SAM s1 = local->{}; //error
        SAM s2 = param->{ Object local = null; }; //error
        SAM s3 = field1->{ Object field_2 = null; }; //ok
    }

    static void testLocalStatic() {
        Object local = null;
        SAM s1 = local->{}; //error
        SAM s2 = param->{ Object local = null; }; //error
        SAM s3 = field1->{ Object field_2 = null; }; //ok
    }

    void testParamInstance(Object local) {
        SAM s1 = local->{}; //error
        SAM s2 = param->{ Object local = null; }; //error
        SAM s3 = field1->{ Object field_2 = null; }; //ok
    }

    static void testParamStatic(Object local) {
        SAM s1 = local->{}; //error
        SAM s2 = param->{ Object local = null; }; //error
        SAM s3 = field1->{ Object field_2 = null; }; //ok
    }

    void testForInstance() {
        for (int local = 0; local != 0 ; local++) {
            SAM s1 = local->{}; //error
            SAM s2 = param->{ Object local = null; }; //error
            SAM s3 = field1->{ Object field_2 = null; }; //ok
        }
    }

    static void testForStatic(Iterable<Object> elems) {
        for (int local = 0; local != 0 ; local++) {
            SAM s1 = local->{}; //error
            SAM s2 = param->{ Object local = null; }; //error
            SAM s3 = field1->{ Object field_2 = null; }; //ok
        }
    }

    void testForEachInstance(Iterable<Object> elems) {
        for (Object local : elems) {
            SAM s1 = local->{}; //error
            SAM s2 = param->{ Object local = null; }; //error
            SAM s3 = field1->{ Object field_2 = null; }; //ok
        }
    }

    static void testForEachStatic(Iterable<Object> elems) {
        for (Object local : elems) {
            SAM s1 = local->{}; //error
            SAM s2 = param->{ Object local = null; }; //error
            SAM s3 = field1->{ Object field_2 = null; }; //ok
        }
    }

    void testCatchInstance() {
        try { } catch (Throwable local) {
            SAM s1 = local->{}; //error
            SAM s2 = param->{ Object local = null; }; //error
            SAM s3 = field1->{ Object field_2 = null; }; //ok
        }
    }

    static void testCatchStatic(Iterable<Object> elems) {
        try { } catch (Throwable local) {
            SAM s1 = local->{}; //error
            SAM s2 = param->{ Object local = null; }; //error
            SAM s3 = field1->{ Object field_2 = null; }; //ok
        }
    }

    void testTWRInstance(AutoCloseable res) {
        try (AutoCloseable local = res) {
            SAM s1 = local->{}; //error
            SAM s2 = param->{ Object local = null; }; //error
            SAM s3 = field1->{ Object field_2 = null; }; //ok
        } finally { }
    }

    static void testTWRStatic(AutoCloseable res) {
        try (AutoCloseable local = res) {
            SAM s1 = local->{}; //error
            SAM s2 = param->{ Object local = null; }; //error
            SAM s3 = field1->{ Object field_2 = null; }; //ok
        } finally { }
    }

    void testBlockLocalInstance() {
        Object local = null;
        {
            SAM s1 = local->{}; //error
            SAM s2 = param->{ Object local = null; }; //error
            SAM s3 = field1->{ Object field_2 = null; }; //ok
        }
    }

    static void testBlockLocalStatic() {
        Object local = null;
        {
            SAM s1 = local->{}; //error
            SAM s2 = param->{ Object local = null; }; //error
            SAM s3 = field1->{ Object field_2 = null; }; //ok
        }
    }

    void testSwitchLocalInstance(int i) {
        switch (i) {
            case 0: Object local = null;
            default: {
                SAM s1 = local->{}; //error
                SAM s2 = param->{ Object local = null; }; //error
                SAM s3 = field1->{ Object field_2 = null; }; //ok
            }
        }
    }

    static void testSwitchLocalStatic(int i) {
        switch (i) {
            case 0: Object local = null;
            default: {
                SAM s1 = local->{}; //error
                SAM s2 = param->{ Object local = null; }; //error
                SAM s3 = field1->{ Object field_2 = null; }; //ok
            }
        }
    }
}
