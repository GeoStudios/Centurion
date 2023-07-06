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

package nsk.jdi.ReferenceType.allMethods;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;
import com.sun.jdi.*;
import java.util.*;
import java.io.*;

/**
 * This test checks the method <code>allMethods()</code>
 * of the JDI interface <code>ReferenceType</code> of com.sun.jdi package
 */

public class allmethods002 extends Log {
    static java.io.PrintStream out_stream;
    static boolean verbose_mode = false;  // test argument -vbs or -verbose switches to true
                                          // - for more easy failure evaluation

    /** The main class names of the debugger & debugee applications. */
    private final static String
        package_prefix = "nsk.jdi.ReferenceType.allMethods.",
//        package_prefix = "",    //  for DEBUG without package
        thisClassName = package_prefix + "allmethods002",
        debugeeName   = thisClassName + "a";

    /** Debugee's classes for check **/
    private final static String class_for_check = package_prefix + "allmethods002aClassForCheck";

    private final static String classLoaderName = package_prefix + "allmethods002aClassLoader";
    private final static String classFieldName = "loadedClass";

    static ArgumentHandler      argsHandler;
    private static Log  logHandler;

    /**
     * Re-call to <code>run(args,out)</code>, and exit with
     * either status 95 or 97 (JCK-like exit status).
     */
    public static void main (String argv[]) {
        int exitCode = run(argv,System.out);
        System.exit(exitCode + 95/*STATUS_TEMP*/);
    }

    /**
     * JCK-like entry point to the test: perform testing, and
     * return exit code 0 (PASSED) or either 2 (FAILED).
     */
    public static int run (String argv[], PrintStream out) {
        out_stream = out;

        int v_test_result = new allmethods002().runThis(argv,out_stream);
        if ( v_test_result == 2/*STATUS_FAILED*/ ) {
            logHandler.complain("\n==> nsk/jdi/ReferenceType/allMethods/allmethods002 test FAILED");
        }
        else {
            logHandler.display("\n==> nsk/jdi/ReferenceType/allMethods/allmethods002 test PASSED");
        }
        return v_test_result;
    }

    private void print_log_on_verbose(String message) {
        logHandler.display(message);
    }

    private void print_log_anyway(String message) {
        logHandler.complain(message);
    }

    /**
     * Non-static variant of the method <code>run(args,out)</code>
     */
    private int runThis (String argv[], PrintStream out) {

        argsHandler     = new ArgumentHandler(argv);
        logHandler      = new Log(out, argsHandler);
        Binder binder   = new Binder(argsHandler, logHandler);

        Debugee debugee;

        if (argsHandler.verbose()) {
            debugee = binder.bindToDebugee(debugeeName + " -vbs");
        } else {
            debugee = binder.bindToDebugee(debugeeName);
        }

        print_log_on_verbose("==> nsk/jdi/ReferenceType/allMethods/allmethods002 test LOG:");
        print_log_on_verbose("==> test checks allMethods() method of ReferenceType interface ");
        print_log_on_verbose("    of the com.sun.jdi package for not prepared class\n");

        IOPipe pipe = new IOPipe(debugee);

        debugee.redirectStderr(out);
        print_log_on_verbose("--> allmethods002: allmethods002a debugee launched");
        debugee.resume();

        String line = pipe.readln();
        if (line == null) {
            print_log_anyway
                ("##> allmethods002: UNEXPECTED debugee's signal (not \"ready\") - " + line);
            return 2/*STATUS_FAILED*/;
        }
        if (!line.equals("ready")) {
            print_log_anyway
                ("##> allmethods002: UNEXPECTED debugee's signal (not \"ready\") - " + line);
            return 2/*STATUS_FAILED*/;
        }
        else {
            print_log_on_verbose("--> allmethods002: debugee's \"ready\" signal recieved!");
        }

        print_log_on_verbose
            ("--> allmethods002: check ReferenceType.allMethods() method for not prepared "
            + class_for_check + " class...");
        boolean class_not_found_error = false;
        boolean allMethods_method_error = false;

        while ( true ) {  // test body
            ReferenceType loaderRefType = debugee.classByName(classLoaderName);
            if (loaderRefType == null) {
                print_log_anyway("##> Could NOT FIND custom class loader: " + classLoaderName);
                class_not_found_error = true;
                break;
            }

            Field classField = loaderRefType.fieldByName(classFieldName);
            Value classValue = loaderRefType.getValue(classField);

            ClassObjectReference classObjRef = null;
            try {
                classObjRef = (ClassObjectReference)classValue;
            } catch (Exception e) {
                print_log_anyway ("##> Unexpected exception while getting ClassObjectReference : " + e);
                class_not_found_error = true;
                break;
            }

            ReferenceType refType = classObjRef.reflectedType();
            boolean isPrep = refType.isPrepared();
            if (isPrep) {
                print_log_anyway
                        ("##> allmethods002: FAILED: isPrepared() returns for " + class_for_check + " : " + isPrep);
                class_not_found_error = true;
                break;
            } else {
                print_log_on_verbose
                    ("--> allmethods002: isPrepared() returns for " + class_for_check + " : " + isPrep);
            }

            List all_methods_list = null;
            try {
                all_methods_list = refType.allMethods();
                print_log_anyway
                    ("##> allmethods002: FAILED: NO any Exception thrown!");
                print_log_anyway
                    ("##>                expected Exception - com.sun.jdi.ClassNotPreparedException");
                allMethods_method_error = true;
            }
            catch (Exception expt) {
                if (expt instanceof com.sun.jdi.ClassNotPreparedException) {
                    print_log_on_verbose
                        ("--> allmethods002: PASSED: expected Exception thrown - " + expt.toString());
                }
                else {
                    print_log_anyway
                        ("##> allmethods002: FAILED: unexpected Exception thrown - " + expt.toString());
                    print_log_anyway
                        ("##>                expected Exception - com.sun.jdi.ClassNotPreparedException");
                    allMethods_method_error = true;
                }
            }
            break;
        }
        int v_test_result = 0/*STATUS_PASSED*/;
        if ( class_not_found_error || allMethods_method_error ) {
            v_test_result = 2/*STATUS_FAILED*/;
        }

        print_log_on_verbose("--> allmethods002: waiting for debugee finish...");
        pipe.println("quit");
        debugee.waitFor();

        int status = debugee.getStatus();
        if (status != 0/*STATUS_PASSED*/ + 95/*STATUS_TEMP*/) {
            print_log_anyway
                ("##> allmethods002: UNEXPECTED Debugee's exit status (not 95) - " + status);
            v_test_result = 2/*STATUS_FAILED*/;
        }
        else {
            print_log_on_verbose
                ("--> allmethods002: expected Debugee's exit status - " + status);
        }

        return v_test_result;
    }
}
