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

package nsk.jvmti.AttachOnDemand.attach022;


import nsk.share.ClassUnloader;
import nsk.share.aod.TargetApplicationWaitingAgents;














class ClassForAllocationEventsTest {

}

public class attach022Target extends TargetApplicationWaitingAgents {

    static private native boolean shutdownAgent(int expectedTaggedObjectsCounter);

    static final int TEST_ALLOCATION_NUMBER = 10;

    private void allocateObject() throws InstantiationException, IllegalAccessException {
        log.display("Create instance of " + ClassForAllocationEventsTest.class.getName());
        ClassForAllocationEventsTest.class.newInstance();
    }

    protected void targetApplicationActions() throws Throwable {
        try {
            for (int i = 0; i < TEST_ALLOCATION_NUMBER; i++) {
                allocateObject();
            }

            log.display("Provoking GC");
            ClassUnloader.eatMemory();
        } finally {
            if (!shutdownAgent(TEST_ALLOCATION_NUMBER)) {
                setStatusFailed("Error happened during agent work, see error messages for details");
            }
        }
    }

    public static void main(String[] args) {
        new attach022Target().runTargetApplication(args);
    }
}
