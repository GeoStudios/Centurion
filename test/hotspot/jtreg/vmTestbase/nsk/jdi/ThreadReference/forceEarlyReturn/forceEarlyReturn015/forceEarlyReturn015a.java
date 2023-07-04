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
package nsk.jdi.ThreadReference.forceEarlyReturn.forceEarlyReturn015;

import nsk.share.jdi.*;

public class forceEarlyReturn015a extends AbstractJDIDebuggee {

    public static void main(String args[]) {
        new forceEarlyReturn015a().doTest(args);
    }

    public static final String COMMAND_START_NEW_THREAD = "startNewThread";

    public boolean parseCommand(String command) {
        if (super.parseCommand(command))
            return true;

        if (command.equals(COMMAND_START_NEW_THREAD)) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    log.display("Thread exit");
                }
            });

            thread.setName("forceEarlyReturn015a_NewThread");
            thread.start();

            return true;
        }

        return false;
    }
}
