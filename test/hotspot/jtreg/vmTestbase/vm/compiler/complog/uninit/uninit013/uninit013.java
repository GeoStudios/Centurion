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

package vm.compiler.complog.uninit.uninit013;

import vm.compiler.complog.share.Constants;

/*
 * @test
 *
 * @summary converted from VM Testbase vm/compiler/complog/uninit/uninit013.
 * VM Testbase keywords: [quick, jit]
 *
 * @library /vmTestbase
 *          /test/lib
 * @build vm.compiler.complog.share.LogCompilationTest
 *        vm.compiler.complog.uninit.uninit013.uninit013
 *        vm.compiler.complog.uninit.UninitializedTrapCounter
 * @run main/othervm
 *      vm.compiler.complog.share.LogCompilationTest
 *      -testedJava ${test.jdk}/bin/java
 *      -testClass vm.compiler.complog.uninit.uninit013.uninit013
 *      -parserClass vm.compiler.complog.uninit.UninitializedTrapCounter
 *      -parserOptions "-classFilter=.*uninit.*"
 */

/**
 * Provoke OSR for loop which mutate fields of uninitialized class.
 */

public class uninit013 {

    Object field = null;
    static Object staticField = null;

    static {
        uninit013 u = new uninit013();

        for(int i = 0; i<Constants.LOOP_ITERATIONS; i++) {
            u.field = new Object();
            uninit013.staticField = new Object();
        }
    }

    public static void main(String args[]) {

    }

}