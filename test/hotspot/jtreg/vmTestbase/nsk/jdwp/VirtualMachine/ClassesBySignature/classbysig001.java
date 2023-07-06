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

package nsk.jdwp.VirtualMachine.ClassesBySignature;

import java.io.*;
import java.util.*;
import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdwp.*;

public class classbysig001 {
    static final int JCK_STATUS_BASE = 95;
    static final int PASSED = 0;
    static final int FAILED = 2;
    static final String PACKAGE_NAME = "nsk.jdwp.VirtualMachine.ClassesBySignature";
    static final String TEST_CLASS_NAME = PACKAGE_NAME + "." + "classbysig001";
    static final String DEBUGEE_CLASS_NAME = TEST_CLASS_NAME + "a";

    static final String JDWP_COMMAND_NAME = "VirtualMachine.ClassesBySignature";
    static final int JDWP_COMMAND_ID = JDWP.Command.VirtualMachine.ClassesBySignature;

    static final String TESTED_CLASS_NAME = DEBUGEE_CLASS_NAME;
//    static final String TESTED_CLASS_SIGNATURE = "L" + TESTED_CLASS_NAME + ";";

    public static void main (String argv[]) {
        System.exit(run(argv,System.out) + JCK_STATUS_BASE);
    }

    public static int run(String argv[], PrintStream out) {
    return new classbysig001().runIt(argv, out);
    }

    public int runIt(String argv[], PrintStream out) {

        boolean success = true;

        try {
            ArgumentHandler argumentHandler = new ArgumentHandler(argv);
            Log log = new Log(out, argumentHandler);

            try {

                Binder binder = new Binder(argumentHandler, log);
                log.display("Start debugee VM");
                Debugee debugee = binder.bindToDebugee(DEBUGEE_CLASS_NAME);
                Transport transport = debugee.getTransport();
                IOPipe pipe = debugee.createIOPipe();

                log.display("Waiting for VM_INIT event");
                debugee.waitForVMInit();

                log.display("Querying for IDSizes");
                debugee.queryForIDSizes();

                log.display("Resume debugee VM");
                debugee.resume();

                log.display("Waiting for command: " + "ready");
                String cmd = pipe.readln();
                log.display("Received command: " + cmd);

                String classSignature = "L" + TESTED_CLASS_NAME.replace('.', '/') + ";";

                // begin test of JDWP command

                try {
                    log.display("Create command " + JDWP_COMMAND_NAME
                                + " with class signature: " + classSignature);
                    CommandPacket command = new CommandPacket(JDWP_COMMAND_ID);
                    command.addString(classSignature);
                    command.setLength();

                    log.display("Sending command packet:\n" + command);
                    transport.write(command);

                    log.display("Waiting for reply packet");
                    ReplyPacket reply = new ReplyPacket();
                    transport.read(reply);
                    log.display("Reply packet received:\n" + reply);

                    log.display("Checking reply packet header");
                    reply.checkHeader(command.getPacketID());

                    log.display("Parsing reply packet:");
                    reply.resetPosition();

                    int classes = reply.getInt();
                    log.display("  classes: " + classes);

                    for (int i = 0; i < classes; i++) {

                        byte refTypeTag = reply.getByte();
                        log.display("  " + i + " refTypeTag: " + refTypeTag);

                        long typeID = reply.getReferenceTypeID();
                        log.display("  " + i + " typeID:     " + typeID);

                        int status = reply.getInt();
                        log.display("  " + i + " status:     " + status);
                    }

                    if (! reply.isParsed()) {
                        log.complain("Extra bytes in reply packet at: " + reply.currentPosition());
                        success = false;
                    }

                    if (classes < 0) {
                        log.complain("Negative number of returned classes: " + classes);
                        success = false;
                    }

                    if (classes == 0) {
                        log.complain("No class returned for signature: " + classSignature);
                        success = false;
                    }

                    if (classes > 1) {
                        log.complain("Too many classes returned for signature: " + classSignature);
                        success = false;
                    }

                } catch (Exception e) {
                    log.complain("Exception catched: " + e);
                    success = false;
                }

                // end test of JDWP command

                log.display("Sending command: " + "quit");
                pipe.println("quit");

                log.display("Waiting for debugee exits");
                int code = debugee.waitFor();
                if (code == JCK_STATUS_BASE + PASSED) {
                    log.display("Debugee PASSED: " + code);
                } else {
                    log.complain("Debugee FAILED: " + code);
                    success = false;
                }

            } catch (Exception e) {
                log.complain("Unexpected exception: " + e);
                e.printStackTrace(out);
                success = false;
            }

            if (!success) {
                log.complain("TEST FAILED");
                return FAILED;
            }

        } catch (Exception e) {
            out.println("Unexpected exception: " + e);
            e.printStackTrace(out);
            out.println("TEST FAILED");
            return FAILED;
        }

        out.println("TEST PASSED");
        return PASSED;

    }

}