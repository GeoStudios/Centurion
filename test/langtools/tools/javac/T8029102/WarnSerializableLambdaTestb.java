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

import java.io.Serializable;

public class WarnSerializableLambdaTestb {
     public void foo(Secret1 secret) {
         Object o = (Runnable & java.io.Serializable) () -> { secret.test(); };
     }

     public void bar(Secret2 secret) {
         Object o = (Runnable & java.io.Serializable) () -> { secret.test(); };
     }

     private class Secret1 {
         public void test() {}
     }

     static private class Secret2 {
         public void test() {}
     }

     class TestInner {
        private int j = 0;
        void m() {
            Serializable s = new Serializable() {
                int i;
                void m() {
                    i = 0;  // don't warn
                    System.out.println(j); //warn
                }
            };
        }
    }

    class TestInner2 {
        class W implements Serializable {
            public int p = 0;
            class I {
                public int r = 0;
                class K implements Serializable {
                    void m() {
                        p = 1;  // don't warn owner is serializable
                        r = 2;  // warn owner is not serializable
                    }
                }
            }
        }
    }
}
