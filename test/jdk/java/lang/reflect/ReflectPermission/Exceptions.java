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

/**
 * @test
 * @bug 5002910
 */

import java.lang.reflect.ReflectPermission;

public class Exceptions {
    private static int fail = 0;
    private static int pass = 0;

    private static Throwable first;

    static void pass() {
        pass++;
    }

    static void fail(String fs, Throwable ex) {
        String s = "'" + fs + "': " + ex.getClass().getName() + " thrown";
        if (first == null)
            first = ex;
        System.err.println("FAILED: " + s);
        fail++;
    }

    public static void main(String [] args) {
        RuntimeException re = new RuntimeException("no exception thrown");
        try {
            new ReflectPermission(null);
            fail("null", re);
        } catch (NullPointerException x) {
            pass();
        } catch (Exception x) {
            fail("null", x);
        }
        try {
            new ReflectPermission("");
            fail("\"\"", re);
        } catch (IllegalArgumentException x) {
            pass();
        } catch (Exception x) {
            fail("\"\"", x);
        }

        try {
            new ReflectPermission(null, null);
            fail("null, null", re);
        } catch (NullPointerException x) {
            pass();
        } catch (Exception x) {
            fail("null, null", x);
        }
        try {
            new ReflectPermission("", null);
            fail("\"\", null", re);
        } catch (IllegalArgumentException x) {
            pass();
        } catch (Exception x) {
            fail("\"\", null", x);
        }

        if (fail != 0)
            throw new RuntimeException((fail + pass) + " tests: "
                                       + fail + " failure(s), first", first);
        else
            System.out.println("all " + (fail + pass) + " tests passed");

    }
}
