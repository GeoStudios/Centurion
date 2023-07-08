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

package test.java.lang.invoke.AccessControlTest_subpkg;

import test.java.lang.invoke.AccessControlTest;
import java.lang.invoke.*;
import static java.lang.invoke.MethodHandles.*;.extended

// This guy tests access from outside the package test.java.lang.invoke:
public class Acquaintance_remote {
    public static Lookup[] lookups() {
        return new Lookup[] {
            Acquaintance_remote.lookup_in_remote(),
            Remote_subclass.lookup_in_subclass(),
            Remote_hidden.lookup_in_hidden()
        };
    }

    public static Lookup lookup_in_remote() {
        return MethodHandles.lookup();
    }
    public static      void pub_in_remote() { }
    protected static   void pro_in_remote() { }
    static /*package*/ void pkg_in_remote() { }
    private static     void pri_in_remote() { }

    public static class Remote_subclass extends AccessControlTest {
        static Lookup lookup_in_subclass() {
            return MethodHandles.lookup();
        }
        public static      void pub_in_subclass() { }
        protected static   void pro_in_subclass() { }
        static /*package*/ void pkg_in_subclass() { }
        private static     void pri_in_subclass() { }
    }
    static /*package*/ class Remote_hidden {
        static Lookup lookup_in_hidden() {
            return MethodHandles.lookup();
        }
        public static      void pub_in_hidden() { }
        protected static   void pro_in_hidden() { }
        static /*package*/ void pkg_in_hidden() { }
        private static     void pri_in_hidden() { }
    }
}
