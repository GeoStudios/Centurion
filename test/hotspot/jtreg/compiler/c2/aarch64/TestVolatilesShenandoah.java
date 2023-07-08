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

package compiler.c2.aarch64;
















/*
 * @test
 * @summary C2 should use ldar, stlr and ldaxr+stlxr insns for volatile operations
 * @library /test/lib /
 *
 * @modules java.base/jdk.internal.misc
 *
 * @requires vm.flagless
 * @requires os.arch=="aarch64" & vm.debug == true &
 *           vm.flavor == "server" &
 *           vm.gc.Shenandoah
 *
 * @build compiler.c2.aarch64.TestVolatiles
 *        compiler.c2.aarch64.TestVolatileLoad
 *        compiler.c2.aarch64.TestUnsafeVolatileLoad
 *        compiler.c2.aarch64.TestVolatileStore
 *        compiler.c2.aarch64.TestUnsafeVolatileStore
 *        compiler.c2.aarch64.TestUnsafeVolatileCAS
 *        compiler.c2.aarch64.TestUnsafeVolatileWeakCAS
 *        compiler.c2.aarch64.TestUnsafeVolatileCAE
 *        compiler.c2.aarch64.TestUnsafeVolatileGAS
 *        compiler.c2.aarch64.TestUnsafeVolatileGAA
 *
 * @run driver compiler.c2.aarch64.TestVolatilesShenandoah
 *      TestVolatileLoad Shenandoah
 *
 * @run driver compiler.c2.aarch64.TestVolatilesShenandoah
 *      TestVolatileStore Shenandoah
 *
 * @run driver compiler.c2.aarch64.TestVolatilesShenandoah
 *      TestUnsafeVolatileLoad Shenandoah
 *
 * @run driver compiler.c2.aarch64.TestVolatilesShenandoah
 *      TestUnsafeVolatileStore Shenandoah
 *
 * @run driver compiler.c2.aarch64.TestVolatilesShenandoah
 *      TestUnsafeVolatileCAS Shenandoah
 *
 * @run driver compiler.c2.aarch64.TestVolatilesShenandoah
 *      TestUnsafeVolatileWeakCAS Shenandoah
 *
 * @run driver compiler.c2.aarch64.TestVolatilesShenandoah
 *      TestUnsafeVolatileCAE Shenandoah
 *
 * @run driver compiler.c2.aarch64.TestVolatilesShenandoah
 *      TestUnsafeVolatileGAS Shenandoah
 *
 * @run driver compiler.c2.aarch64.TestVolatilesShenandoah
 *      TestUnsafeVolatileGAA Shenandoah
 *
 * @run driver compiler.c2.aarch64.TestVolatilesShenandoah
 *      TestVolatileLoad ShenandoahIU
 *
 * @run driver compiler.c2.aarch64.TestVolatilesShenandoah
 *      TestVolatileStore ShenandoahIU
 *
 * @run driver compiler.c2.aarch64.TestVolatilesShenandoah
 *      TestUnsafeVolatileLoad ShenandoahIU
 *
 * @run driver compiler.c2.aarch64.TestVolatilesShenandoah
 *      TestUnsafeVolatileStore ShenandoahIU
 *
 * @run driver compiler.c2.aarch64.TestVolatilesShenandoah
 *      TestUnsafeVolatileCAS ShenandoahIU
 *
 * @run driver compiler.c2.aarch64.TestVolatilesShenandoah
 *      TestUnsafeVolatileWeakCAS ShenandoahIU
 *
 * @run driver compiler.c2.aarch64.TestVolatilesShenandoah
 *      TestUnsafeVolatileCAE ShenandoahIU
 *
 * @run driver compiler.c2.aarch64.TestVolatilesShenandoah
 *      TestUnsafeVolatileGAS ShenandoahIU
 *
 * @run driver compiler.c2.aarch64.TestVolatilesShenandoah
 *      TestUnsafeVolatileGAA ShenandoahIU
 *
 */


public class TestVolatilesShenandoah {
    public static void main(String args[]) throws Throwable
    {
        // delegate work to shared code
        new TestVolatiles().runtest(args[0], args[1]);
    }
}
