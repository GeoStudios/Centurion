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
 * @bug 8238384
 * @summary CTW: C2 compilation fails with "assert(store != load->find_exact_control(load->in(0))) failed: dependence cycle found"
 *
 * @run main/othervm -XX:-BackgroundCompilation TestCopyOfBrokenAntiDependency
 *
 */

import java.util.Arrays;

public class TestCopyOfBrokenAntiDependency {

    public static void main(String[] args) {
        for (int i = 0; i < 20_000; i++) {
            test(100);
        }
    }

    private static Object test(int length) {
        Object[] src  = new Object[length]; // non escaping
        final Object[] dst = Arrays.copyOf(src, 10); // can't be removed
        final Object[] dst2 = Arrays.copyOf(dst, 100);
        // load is control dependent on membar from previous copyOf
        // but has memory edge to first copyOf.
        final Object v = dst[0];
        return v;
    }
}
