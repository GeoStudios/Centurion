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

package metaspace.stressHierarchy.common;


import nsk.share.test.ExecutionController;
import nsk.share.test.Tests;
import metaspace.stressHierarchy.common.classloader.tree.Tree;














/**
 * Test checks that ancestors will not be reclaimed while references on descenders are alive.
 * We create hierarchy of classes, each loaded by dedicated classloader. Then we cleanup
 * references to all nodes except those that are in bottom level. Finally we check that classes
 * in bottom level are alive.
 */
public class StressHierarchy1 extends StressHierarchyBaseClass {

    public static void main(String[] args) {
        try {
            StressHierarchyBaseClass.args = args;
            Tests.runTest(new StressHierarchy1(), args);
        } catch (OutOfMemoryError error) {
            System.out.print("Got OOME: " + error.getMessage());
        }
    }

    @Override
    protected void runTestLogic(Tree tree, ExecutionController stresser)
            throws Throwable {

        for (int cleanupLevel = tree.getMaxLevel() - 1; cleanupLevel > 1; cleanupLevel--) {
            tree.cleanupLevel(cleanupLevel);
            log.info("cleanupLevel=" + cleanupLevel);
            triggerUnloadingHelper.triggerUnloading(stresser);
            if (!stresser.continueExecution()) {
                // pass test
                return;
            }
        }

        log.info("Check bottom level");
        performChecksHelper.checkLevelAlive(tree, tree.getMaxLevel());
    }

}
