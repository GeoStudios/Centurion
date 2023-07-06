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

package nsk.jdi.Connector.name;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;
import com.sun.jdi.*;
import com.sun.jdi.connect.Connector;
import java.io.PrintStream;
import java.util.*;

/**
 * Test for control of
 *
 *      Interface:      com.sun.jdi.connect.Connector
 *      Method:         public java.lang.String name()
 *      Assertion:      "Returns a short identifier for the connector."
 */

public class name001 {
    private static Log log;

    public static void main( String argv[] ) {
        System.exit(run(argv, System.out)+95); // JCK-compatible exit status
    }

    public static int run(String argv[], PrintStream out) {
        ArgumentHandler argHandler = new ArgumentHandler(argv);
        log = new Log(out, argHandler);
        VirtualMachineManager vmm = Bootstrap.virtualMachineManager();

        List acl = vmm.allConnectors();
        if (acl.size() > 0) {
            log.display("Number of all known JDI connectors: " + acl.size());
        } else {
            log.complain("FAILURE: no JDI connectors found!");
            return 2;
        }

        Iterator aci = acl.iterator();
        for (int i = 1; aci.hasNext(); i++) {
            Connector c = (Connector) aci.next();
            String cnm = c.name();
            if (cnm == null) {
                log.complain("FAILURE: connector name is null.");
                log.complain("         Description: " + c.description());
                log.complain("         Transport: " + c.transport().name());
                return 2;
            }

            if (cnm.length() == 0) {
                log.complain("FAILURE: connector with empty-name is found.");
                log.complain("         Description: " + c.description());
                log.complain("         Transport: " + c.transport().name());
                return 2;
            }

            log.display("Next (" + i + ") connector's name is: " + cnm);
        };
        log.display("Test PASSED!");
        return 0;
    }
}
