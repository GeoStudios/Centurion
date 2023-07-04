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

class MostSpecific09 {

    interface I {
        String xoo(String x);
    }

    interface J {
        void xoo(int x);
    }

    static void foo(I i) {}
    static void foo(J j) {}

    static void moo(I i) {}
    static void moo(J j) {}

    void m() {
        foo((x) -> { return x += 1; });
        foo((x) -> { return ""; });
        foo((x) -> { System.out.println(""); });
        foo((x) -> { return ""; System.out.println(""); });
        foo((x) -> { throw new RuntimeException(); });
        foo((x) -> { while (true); });

        foo((x) -> x += 1);
        foo((x) -> "");
    }

    /* any return statement that is not in the body of the lambda but in an
     * inner class or another lambda should be ignored for value void compatibility
     * determination.
     */
    void m1() {
        boolean cond = true;
        foo((x) -> {
            if (cond) {
                return "";
            }
            System.out.println("");
        });

        foo((x)->{
            class Bar {
                String m() {
                    return "from Bar.m()";
                }
            }
            class Boo {
                Bar b = new Bar (){
                    String m() {
                        return "from Bar$1.m()";
                    }
                };
            }
            moo((y) -> { return ""; });
            return;
        });

        foo((x)->{
            class Bar {
                void m() {}
            }
            class Boo {
                Bar b = new Bar (){
                    void m() {
                        return;
                    }
                };
            }
            moo((y) -> { System.out.println(""); });
            return "";
        });
    }
}
