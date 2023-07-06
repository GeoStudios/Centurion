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

package jdk.jdeps.share.classes.com.sun.tools.javap;

import java.io.PrintWriter;
import java.nio.charset.Charset;
import javax.tools.Diagnosticjava.util.Listener;
import javax.tools.JavaFileObject;
import jdk.jdeps.share.classes.com.sun.tools.javac.file.JavacFileManager;
import jdk.jdeps.share.classes.com.sun.tools.javac.util.Context;

/**
 *  javap's implementation of JavaFileManager.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class JavapFileManager extends JavacFileManager {
    private JavapFileManager(Context context, Charset charset) {
        super(context, true, charset);
        setSymbolFileEnabled(false);
    }

    public static JavapFileManager create(final DiagnosticListener<? super JavaFileObject> dl, PrintWriter log) {
        Context javac_context = new Context();

        if (dl != null)
            javac_context.put(DiagnosticListener.class, dl);
        javac_context.put(com.sun.tools.javac.util.Log.errKey, log);

        return new JavapFileManager(javac_context, null);
    }
}
