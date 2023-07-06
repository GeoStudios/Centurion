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

package nsk.jdi.ReferenceType.methodsByName_s;

import java.lang.reflect.*;
import java.io.*;
import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;

/**
 * This class is used as debugee application for the methbyname_s003 JDI test.
 */

public class methbyname_s003a {

    static boolean verbose_mode = false;  // debugger may switch to true
                                          // - for more easy failure evaluation

    private final static String package_prefix = "nsk.jdi.ReferenceType.methodsByName_s.";
    private final static String checked_class_name = package_prefix + "methbyname_s003b";

    private static void print_log_on_verbose(String message) {
        if ( verbose_mode ) {
            System.err.println(message);
        }
    }

    public static void main (String argv[]) {

        ArgumentHandler argHandler = new ArgumentHandler(argv);
        verbose_mode = argHandler.verbose();

        print_log_on_verbose("**> methbyname_s003a: debugee started!");
        IOPipe pipe = argHandler.createDebugeeIOPipe();

        print_log_on_verbose("**> methbyname_s003a: waiting for \"checked class dir\" info...");
        pipe.println("ready0");
        String checked_class_dir = (argHandler.getArguments())[0] + File.separator + "loadclass";

        ClassUnloader classUnloader = new ClassUnloader();

        try {
            classUnloader.loadClass(checked_class_name, checked_class_dir);
            print_log_on_verbose
                ("--> methbyname_s003a: checked class loaded:" + checked_class_name);
        }
        catch ( Exception e ) {  // ClassNotFoundException
            System.err.println
                ("**> methbyname_s003a: load class: exception thrown = " + e.toString());
            print_log_on_verbose
                ("--> methbyname_s003a: checked class NOT loaded:" + checked_class_name);
            // Debuuger finds this fact itself
        }

        print_log_on_verbose("**> methbyname_s003a: waiting for \"continue\" or \"quit\" signal...");
        pipe.println("ready1");
        String instruction = pipe.readln();
        if (instruction.equals("quit")) {
            print_log_on_verbose("**> methbyname_s003a: \"quit\" signal recieved!");
            print_log_on_verbose("**> methbyname_s003a: completed!");
            System.exit(0/*STATUS_PASSED*/ + 95/*STATUS_TEMP*/);
        }
        if ( ! instruction.equals("continue")) {
            System.err.println
                ("!!**> methbyname_s003a: unexpected signal (no \"continue\" or \"quit\") - " + instruction);
            System.err.println("!!**> methbyname_s003a: FAILED!");
            System.exit(2/*STATUS_FAILED*/ + 95/*STATUS_TEMP*/);
        }

        print_log_on_verbose("**> methbyname_s003a: \"continue\" signal recieved!");
        print_log_on_verbose("**> methbyname_s003a: enforce to unload checked class...");

        boolean test_class_loader_finalized = classUnloader.unloadClass();

        if ( ! test_class_loader_finalized ) {
            print_log_on_verbose("**> methbyname_s003a: checked class may be NOT unloaded!");
            pipe.println("not_unloaded");
        }
        else {
            print_log_on_verbose("**> methbyname_s003a: checked class unloaded!");
            pipe.println("ready2");
        }
        print_log_on_verbose("**> methbyname_s003a: waiting for \"quit\" signal...");
        instruction = pipe.readln();
        if (instruction.equals("quit")) {
            print_log_on_verbose("**> methbyname_s003a: \"quit\" signal recieved!");
            print_log_on_verbose("**> methbyname_s003a: completed!");
            System.exit(0/*STATUS_PASSED*/ + 95/*STATUS_TEMP*/);
        }
        System.err.println("!!**> methbyname_s003a: unexpected signal (no \"quit\") - " + instruction);
        System.err.println("!!**> methbyname_s003a: FAILED!");
        System.exit(2/*STATUS_FAILED*/ + 95/*STATUS_TEMP*/);
    }
}  // end of methbyname_s003a class