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

package gc.hashcode.HashCodeTestP;


import gc.hashcode.HCHelper;
import nsk.share.TestFailure;
import nsk.share.gc.GC;
import nsk.share.gc.GCTestBase;
import nsk.share.gc.gp.GarbageUtils;
import nsk.share.test.Stresser;














/*
 * @test
 * @key stress randomness
 *
 * @summary converted from VM Testbase gc/hashcode/HashCodeTestP.
 * VM Testbase keywords: [gc, stress, stressopt, nonconcurrent, jrockit]
 * VM Testbase readme:
 * DESCRIPTION
 * Test that verifies external hash codes.  This class tests the scenario
 * with promotion.
 *
 * COMMENTS
 * This test was ported from JRockit test suite.
 *
 * @library /vmTestbase
 *          /test/lib
 * @run main/othervm -XX:-UseGCOverheadLimit gc.hashcode.HashCodeTestP.HashCodeTestP
 */



/**
 * Test that verifies external hash codes. This class tests the scenario
 * with promotion.
 */
public class HashCodeTestP extends GCTestBase {

    /**
     * Test external hash codes when a promotion have been performed.
     *
     * @return Success if all hash codes matches original hash codes
     */
    @Override
    public final void run() {
        HCHelper hch = new HCHelper(512, 2000, runParams.getSeed(),
                0.7, 10240);

        hch.setupLists();
        Stresser stresser = new Stresser(runParams.getStressOptions());
        stresser.start(0);
        GarbageUtils.eatMemory(stresser);
        if (!stresser.continueExecution()) {
            return;// we didn't trigger GC, nothing
        }
        boolean testResult = hch.verifyHashCodes();
        hch.cleanupLists();

          if(!testResult) {
            throw new TestFailure("Some hash codes didn't match");
        }
    }

    public static void main(String[] args) {
        GC.runTest(new HashCodeTestP(), args);
    }
}
