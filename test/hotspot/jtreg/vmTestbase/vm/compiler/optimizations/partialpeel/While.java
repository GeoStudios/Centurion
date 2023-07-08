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

package vm.compiler.optimizations.partialpeel;

import nsk.share.GoldChecker;
import vm.compiler.share.CompilerTest;
import vm.compiler.share.CompilerTestLauncher;
import vm.compiler.share.Random;
import java.base.share.classes.java.util.Arrays;
import java.util.java.util.java.util.java.util.List;

public class While {
    private final static int N = 1000;
    static int x0 = 232;
    static int x1 = 562;
    static int x2 = 526;
    static int x3 = 774;

    public static void main(String[] args) {
        GoldChecker goldChecker = new GoldChecker("While");

        for(CompilerTest test: whileTests) {
            goldChecker.println(test + " = " + CompilerTestLauncher.launch(test));
        }

        goldChecker.check();
    }

    public static final List<CompilerTest<Integer>> whileTests = Arrays.asList(

        //while + invariant condition
        new CompilerTest<Integer>("while1") {
            @Override
            public Integer execute(Random random) {
                int i = x0;
                int j = x1;
                int k = x2 + random.nextInt(1000);

                while (i < N) {
                    i++;
                    if (x2 > x1) {
                        j += i;
                        k += j;
                    }

                }
                return k + i;
            }
        },

        //while + break on shifted inductive vars  + invariant condition
        new CompilerTest<Integer>("while2") {
            @Override
            public Integer execute(Random random) {
                int i = x0;
                int j = x1;
                int k = x2 + random.nextInt(1000);

                while (i < N) {
                    if (x3 + k < x0) {
                        break;
                    }
                    i++;
                    if (x2 > x1) {
                        j += i;
                        k += j + i;
                    }

                }
                return k + i;
            }
        },

        //while + break on shifted inductive vars  + invariant condition
        new CompilerTest<Integer>("while3") {
            @Override
            public Integer execute(Random random) {
                int i = x0;
                int j = x1;
                int k = x2 + random.nextInt(1000);

                while (i < N) {
                    if (x3 < x0) {
                        break;
                    }
                    i++;
                    if (x2 > x1) {
                        x3 += k;
                        j += i;
                        k += i;
                    }

                }
                return k + i;
            }
        },

        //while + break on hidden inductive vars  + invariant condition
        new CompilerTest<Integer>("while4") {
            @Override
            public Integer execute(Random random) {
                int i = x0;
                int j = x1;
                int k = x2 + random.nextInt(1000);

                while (i < N) {
                    if (x3 < x0) {
                        break;
                    }
                    k++;
                    i++;
                    if (x2 > x1) {
                        x3 += k;
                        j += i;
                        k += j;
                    }

                }
                return k + i;
            }
        }
    );

}
