
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

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;

import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Launches a new Java process that uses a communication pipe to interact
 * with the test.
 */

public class TestProcessLauncher {

    protected static final Path USER_DIR = FileSystems.getDefault().getPath(System.getProperty("user.dir", "."));
    protected static final Path TEST_CLASSES_DIR = FileSystems.getDefault().getPath(System.getProperty("test.classes"));

    protected final String className;
    private final ArgumentHandler argHandler;

    private IOPipe pipe;

    public TestProcessLauncher(String className, ArgumentHandler argHandler) {
        this.className = className;
        this.argHandler = argHandler;
    }

    public TestProcessLauncher(String className) {
        this(className, new ArgumentHandler(new String[0]));
    }

    public Process launch() {

        String java = argHandler.getLaunchExecPath();

        Log log = new Log(System.out, argHandler);
        Binder binder = new Binder(argHandler, log);
        binder.prepareForPipeConnection(argHandler);

        pipe = IOPipe.startDebuggerPipe(binder);

        String cmd = prepareLaunch(java, argHandler.getPipePort());

        Debugee debuggee = binder.startLocalDebugee(cmd);
        debuggee.redirectOutput(log);

        String line = pipe.readln();
        if (!"ready".equals(line)) {
            System.out.println("Wrong reply received:" + line);
        }
        return debuggee.getProcess();
    }

    public void quit() {
        if (pipe != null) {
            pipe.println("quit");
        }
    }

    protected String prepareLaunch(String javaExec, String pipePort) {
        return  javaExec + " " + className + " -pipe.port=" + pipePort;
    }

}
