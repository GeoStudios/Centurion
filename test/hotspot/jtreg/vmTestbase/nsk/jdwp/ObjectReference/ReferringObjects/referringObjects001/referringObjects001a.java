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
package nsk.jdwp.ObjectReference.ReferringObjects.referringObjects001;

import java.util.*;
import nsk.share.ReferringObjectSet;
import nsk.share.jdi.HeapwalkingDebuggee;
import nsk.share.jdwp.*;

public class referringObjects001a extends AbstractJDWPDebuggee {
    public static Object testInstance;

    public static final String COMMAND_CREATE_TEST_INSTANCES = "COMMAND_CREATE_TEST_INSTANCES";

    public static final int expectedCount = HeapwalkingDebuggee.includedIntoReferrersCountTypes.size() + 1;

    private ArrayList<ReferringObjectSet> referrers = new ArrayList<ReferringObjectSet>();

    public boolean parseCommand(String command) {
        if (super.parseCommand(command))
            return true;

        if (command.equals(COMMAND_CREATE_TEST_INSTANCES)) {
            testInstance = new Object();

            // create objects refering to 'testInstance' via references with types which should be supported by command ObjectReference.ReferringObjects
            for (String referenceType : HeapwalkingDebuggee.includedIntoReferrersCountTypes) {
                referrers.add(new ReferringObjectSet(testInstance, 1, referenceType));
            }

            return true;
        }

        return false;
    }

    public static void main(String args[]) {
        new referringObjects001a().doTest(args);
    }
}
