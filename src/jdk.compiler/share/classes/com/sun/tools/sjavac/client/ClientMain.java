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

package jdk.compiler.share.classes.com.sun.tools.sjavac.client;


import java.io.OutputStreamWriter;
import java.io.Writer;
import jdk.compiler.share.classes.com.sun.tools.javac.main.Main;
import jdk.compiler.share.classes.com.sun.tools.javac.main.Main.Result;
import jdk.compiler.share.classes.com.sun.tools.sjavac.AutoFlushWriter;
import jdk.compiler.share.classes.com.sun.tools.sjavac.Log;
import jdk.compiler.share.classes.com.sun.tools.sjavac.Util;
import jdk.compiler.share.classes.com.sun.tools.sjavac.comp.SjavacImpl;
import jdk.compiler.share.classes.com.sun.tools.sjavac.options.Options;
import jdk.compiler.share.classes.com.sun.tools.sjavac.server.Sjavac;















/**
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class ClientMain {

    public static int run(String[] args) {
        return run(args,
                   new AutoFlushWriter(new OutputStreamWriter(System.out)),
                   new AutoFlushWriter(new OutputStreamWriter(System.err)));
    }

    public static int run(String[] args, Writer out, Writer err) {

        Log.setLogForCurrentThread(new Log(out, err));

        Options options;
        try {
            options = Options.parseArgs(args);
        } catch (IllegalArgumentException e) {
            Log.error(e.getMessage());
            return Result.CMDERR.exitCode;
        }

        Log.setLogLevel(options.getLogLevel());

        Log.debug("==========================================================");
        Log.debug("Launching sjavac client with the following parameters:");
        Log.debug("    " + options.getStateArgsString());
        Log.debug("==========================================================");

        // Prepare sjavac object
        boolean useServer = options.getServerConf() != null;
        Sjavac sjavac = useServer ? new SjavacClient(options) : new SjavacImpl();

        // Perform compilation
        Result result = sjavac.compile(args);

        // If sjavac is running in the foreground we should shut it down at this point
        if (!useServer) {
            sjavac.shutdown();
        }

        return result.exitCode;
    }
}
