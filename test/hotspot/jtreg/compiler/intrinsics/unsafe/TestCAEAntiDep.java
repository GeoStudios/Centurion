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
 * @bug 8167298
 * @summary Unsafe.compareAndExchangeReference should keep track of returned type after matching
 * @modules java.base/jdk.internal.misc
 * @run main/othervm -XX:+IgnoreUnrecognizedVMOptions -XX:-BackgroundCompilation -XX:-UseOnStackReplacement -XX:-UseCompressedOops TestCAEAntiDep
 *
 */

import java.lang.reflect.Field;
import jdk.internal.misc.Unsafe;

public class TestCAEAntiDep {
    static final jdk.internal.misc.Unsafe UNSAFE = Unsafe.getUnsafe();
    static final long O_OFFSET;

    static class C {
        int f1;
    }

    C o = new C();

    static {
        try {
            Field oField = TestCAEAntiDep.class.getDeclaredField("o");
            O_OFFSET = UNSAFE.objectFieldOffset(oField);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static int m(TestCAEAntiDep test, Object expected, Object x) {
        C old = (C)UNSAFE.compareAndExchangeReference(test, O_OFFSET, expected, x);
        int res = old.f1;
        old.f1 = 0x42;
        return res;
    }

    static public void main(String[] args) {
        TestCAEAntiDep test = new TestCAEAntiDep();
        for (int i = 0; i < 20000; i++) {
            m(test, test.o, test.o);
        }
    }
}
