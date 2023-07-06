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

package compiler.codecache;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/*
 * @test
 * @bug 8257513
 * @requires vm.debug == true
 * @summary Stress testing code buffers resulted in an assertion failure due to not taking expand calls into account
 *          which can fail more often with -XX:+StressCodeBuffers. Perform some more sanity flag testing.
 * @run main/othervm -Xcomp -XX:-TieredCompilation -XX:+StressCodeBuffers compiler.codecache.TestStressCodeBuffers
 * @run main/othervm -Xcomp -XX:+StressCodeBuffers compiler.codecache.TestStressCodeBuffers
 */

public class TestStressCodeBuffers {

    static MethodHandle permh;

    public static void main(String[] args) throws Exception {
        test();
    }

    public static void test() throws Exception {
        MethodHandles.Lookup lookup = MethodHandles.publicLookup();
        MethodHandle mh = lookup.findStatic(TestStressCodeBuffers.class, "bar",
                                            MethodType.methodType(void.class, int.class, long.class));
        permh = MethodHandles.permuteArguments(mh, mh.type(), 0, 1); // Triggers assertion failure
    }

    public static void bar(int x, long y) {}
}

