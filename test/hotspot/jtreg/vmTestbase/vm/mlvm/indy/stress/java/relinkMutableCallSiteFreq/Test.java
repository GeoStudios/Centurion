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

package vm.mlvm.indy.stress.java.relinkMutableCallSiteFreq;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MutableCallSite;
import vm.mlvm.indy.share.INDIFY_RelinkCallSiteFreqTest;
import vm.mlvm.share.MlvmTest;

/*
 * @test
 *
 * @summary converted from VM Testbase vm/mlvm/indy/stress/java/relinkMutableCallSiteFreq.
 * VM Testbase keywords: [feature_mlvm, nonconcurrent]
 *
 * @library /vmTestbase
 *          /test/lib
 *
 * @comment build test class and indify classes
 * @build vm.mlvm.indy.stress.java.relinkMutableCallSiteFreq.Test
 * @run driver vm.mlvm.share.IndifiedClassesBuilder
 *
 * @run main/othervm vm.mlvm.indy.stress.java.relinkMutableCallSiteFreq.Test
 */

/**
 * The test creates a mutable call site and relinks it from one thread while calling the current
 * target from the other one. Currently there are 6 targets.

 * The test verifies that target changes in the call site are eventually seen by target calling
 * thread by measuring a frequency of calls for each target and comparing it with theoretical frequency.
 *
 */
public class Test extends INDIFY_RelinkCallSiteFreqTest {

    @Override
    protected CallSite createCallSite(MethodHandle mh) {
        return new MutableCallSite(mh);
    }

    /**
     * Runs the test.
     * @param args Arguments to the test.
     */
    public static void main(String[] args) {
        MlvmTest.launch(args);
    }
}
