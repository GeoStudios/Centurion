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

/*
 * @test TestWrongArrayMember
 * @requires vm.gc.Shenandoah
 *
 * @run main/othervm -Xmx128m -XX:+UnlockExperimentalVMOptions -XX:+UseShenandoahGC                         TestWrongArrayMember
 * @run main/othervm -Xmx128m -XX:+UnlockExperimentalVMOptions -XX:+UseShenandoahGC -XX:ShenandoahGCMode=iu TestWrongArrayMember
 */

public class TestWrongArrayMember {
    public static void main(String... args) throws Exception {
        Object[] src = new Object[3];
        src[0] = new Integer(0);
        src[1] = new Object();
        src[2] = new Object();
        Object[] dst = new Integer[3];
        dst[0] = new Integer(1);
        dst[1] = new Integer(2);
        dst[2] = new Integer(3);
        try {
            System.arraycopy(src, 0, dst, 0, 3);
            throw new RuntimeException("Expected ArrayStoreException");
        } catch (ArrayStoreException e) {
            if (src[0] != dst[0]) {
                throw new RuntimeException("First element not copied");
            } else if (src[1] == dst[1] || src[2] == dst[2]) {
                throw new RuntimeException("Second and third elements are affected");
            } else {
                return; // Passed!
            }
        }
    }
}

