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

package nsk.jdi.BooleanArgument.stringValueOf;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.java.util.java.util.java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;
import com.sun.jdi.VirtualMachineManager;
import com.sun.jdi.Bootstrap;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.Connector.Argument;
import com.sun.jdi.connect.Connector.BooleanArgument;

/**
 * The test for the implementation of an object of the type <BR>
 * Connector.BooleanArgument. <BR>
 * <BR>
 * The test checks up that results of the method <BR>
 * <code>com.sun.jdi.connect.Connector.BooleanArgument.stringValueOf()</code> <BR>
 * complies with the following statement in its specification: <BR>
 * "Does not set the value of the argument." <BR>
 * <BR>
 * In case of the method does set the value, <BR>
 * the test produces the return value 97 and <BR>
 * a corresponding error message. <BR>
 * Otherwise, the test is passed and produces <BR>
 * the return value 95 and no message. <BR>
 */

public class stringvalueof002 {

    public static void main(String argv[]) {
        System.exit(run(argv, System.out) + 95); // JCK-compatible exit status
    }

    public static int run(String argv[], PrintStream out) {

        int exitCode  = 0;
        int exitCode0 = 0;
        int exitCode2 = 2;
//
        String sErr1 =  "WARNING\n" +
                        "Method tested: " +
                        "jdi.Connector.BooleanArgument.stringValueOf\n" ;
//
        String sErr2 =  "ERROR\n" +
                        "Method tested: " +
                        "jdi.Connector.BooleanArgument.stringValueOf\n" ;

        VirtualMachineManager vmm = Bootstrap.virtualMachineManager();

        List connectorsList = vmm.allConnectors();
        Iterator connectorsListIterator = connectorsList.iterator();
//
        Connector.BooleanArgument argument = null;

        for ( ; ; ) {
            try {
                Connector connector =
                (Connector) connectorsListIterator.next();

                Map defaultArguments = connector.defaultArguments();
                Set keyset     = defaultArguments.keySet();
                int keysetSize = defaultArguments.size();
                Iterator  keysetIterator = keyset.iterator();

                for ( ; ; ) {
                    try {
                        String argName = (String) keysetIterator.next();

                        try {
//
                            argument = (Connector.BooleanArgument)
                                       defaultArguments.get(argName);
                            break ;
                        } catch ( ClassCastException e) {
                        }
                    } catch ( NoSuchElementException e) {
                        break ;
                    }
                }
                if (argument != null) {
                    break ;
                }
            } catch ( NoSuchElementException e) {
                out.println(sErr1 +
                    "no Connector with BooleanArgument found\n");
                return exitCode0;
            }
        }

        String argumentValue;
        // 1)
        argument.setValue(true);
        argumentValue = argument.stringValueOf(true);
        if (argument.booleanValue() != true) {
            exitCode = 2;
            out.println(sErr2 +
                      "case: stringValueOf(true) doesn't change value true\n" +
                      "error: argument's value != true\n");
        }

        // 2)
        argument.setValue(true);
        argumentValue = argument.stringValueOf(false);
        if (argument.booleanValue() != true) {
            exitCode = 2;
            out.println(sErr2 +
                      "case: stringValueOf(false) doesn't change value true\n" +
                      "error: argument's value != true\n");
        }

        // 3)
        argument.setValue(false);
        argumentValue = argument.stringValueOf(true);
        if (argument.booleanValue() != false) {
            exitCode = 2;
            out.println(sErr2 +
                      "case: stringValueOf(true) doesn't change value false\n" +
                      "error: a returned value != false\n");
        }

        // 4)
        argument.setValue(false);
        argumentValue = argument.stringValueOf(false);
        if (argument.booleanValue() != false) {
            exitCode = 2;
            out.println(sErr2 +
                      "case: stringValueOf(false) doesn't change value false\n" +
                      "error: a returned value != false\n");
        }

        if (exitCode != exitCode0)
            out.println("TEST FAILED");

        return exitCode;
    }
}
