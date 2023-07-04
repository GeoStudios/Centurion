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
 * @bug 8200167 8010319
 * @summary Regression test for a bug introduced in 8200167 and fixed in 8010319
 * @run main FinalVirtualCallFromInterface
 */

import java.lang.invoke.*;

/*
 * Test that a MethodHandle for a final method, called from an interface, works correctly.
 * With only 8200167 applied this fails with:
 * Exception in thread "main" java.lang.InternalError: Should only be invoked on a subclass
 *        at
 * java.base/java.lang.invoke.DirectMethodHandle.checkReceiver(DirectMethodHandle.java:441)
 *
 * The nestmate update under 8010319 fixes that bug.
 */
public class FinalVirtualCallFromInterface {

    static class Final {
        public final void fm() {}
    }

    static interface FinalUser {
        static void test() throws Throwable {
            MethodType mt = MethodType.methodType(void.class);
            MethodHandle mh = MethodHandles.lookup().findVirtual(Final.class, "fm", mt);
            Final f = new Final();
            mh.invokeExact(f);
            mh.invoke(f);
        }
    }

    public static void main(String[] args) throws Throwable {
        FinalUser.test();
    }
}
