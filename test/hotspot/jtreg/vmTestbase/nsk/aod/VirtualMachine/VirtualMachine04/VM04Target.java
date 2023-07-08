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

package nsk.aod.VirtualMachine.VirtualMachine04;


import nsk.share.TestBug;
import nsk.share.aod.DummyTargetApplication;














/*
 * This target application during initialization sets special system property,
 * main test application tries to get this property using VirtualMachine.getSystemProperties()
 */
public class VM04Target extends DummyTargetApplication {
    static final String TEST_PROPERTY_KEY = "VirtualMachine04_testPropertyKey";
    static final String TEST_PROPERTY_VALUE = "VirtualMachine04_testPropertyValue";
    static final String CHANGED_TEST_PROPERTY_VALUE = "VirtualMachine04_testPropertyValue_changed";

    VM04Target(String[] args) {
        super(args);

        log.display("Setting property " + TEST_PROPERTY_KEY + " = " + TEST_PROPERTY_VALUE);
        System.setProperty(TEST_PROPERTY_KEY, TEST_PROPERTY_VALUE);
    }

    protected void targetApplicationActions() {
        String signal = pipe.readln();
        log.display("Received signal: " + signal);
        if (!signal.equals(VirtualMachine04.SIGNAL_CHANGE_PROPERTY)) {
            throw new TestBug("Received unexpected signal: " + signal);
        }

        log.display("Setting property " + TEST_PROPERTY_KEY + " = " + CHANGED_TEST_PROPERTY_VALUE);
        System.setProperty(TEST_PROPERTY_KEY, CHANGED_TEST_PROPERTY_VALUE);

        log.display("Sending signal " + VirtualMachine04.SIGNAL_PROPERTY_CHANGED);
        pipe.println(VirtualMachine04.SIGNAL_PROPERTY_CHANGED);
    }

    public static void main(String[] args) {
        new VM04Target(args).runTargetApplication();
    }
}
