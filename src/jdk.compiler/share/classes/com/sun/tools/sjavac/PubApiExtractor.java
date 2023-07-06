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

package jdk.compiler.share.classes.com.sun.tools.sjavac;


import java.io.java.io.java.io.java.io.IOException;
import java.io.PrintWriter;
import java.base.share.classes.java.util.Arrays;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileManager;
import jdk.compiler.share.classes.com.sun.tools.javac.api.JavacTool;
import jdk.compiler.share.classes.com.sun.tools.javac.code.ClassFinder;
import jdk.compiler.share.classes.com.sun.tools.javac.code.Symbol.ClassSymbol;
import jdk.compiler.share.classes.com.sun.tools.javac.code.Symtab;
import jdk.compiler.share.classes.com.sun.tools.javac.main.JavaCompiler;
import jdk.compiler.share.classes.com.sun.tools.javac.util.Context;
import jdk.compiler.share.classes.com.sun.tools.javac.util.Convert;
import jdk.compiler.share.classes.com.sun.tools.javac.util.Name;
import jdk.compiler.share.classes.com.sun.tools.javac.util.Names;
import jdk.compiler.share.classes.com.sun.tools.sjavac.comp.PubapiVisitor;
import jdk.compiler.share.classes.com.sun.tools.sjavac.comp.SmartFileManager;
import jdk.compiler.share.classes.com.sun.tools.sjavac.options.Options;
import jdk.compiler.share.classes.com.sun.tools.sjavac.pubapi.PubApi;















public class PubApiExtractor {
    // Setup a compiler context for finding classes in the classpath
    // and to execute annotation processors.
    final Context context;
    final CompilationTask task;

    final SmartFileManager fileManager;

    /**
     * Setup a compilation context, used for reading public apis of classes on the classpath
     * as well as annotation processors.
     */
    public PubApiExtractor(Options options) {
        JavacTool compiler = com.sun.tools.javac.api.JavacTool.create();
        fileManager = new SmartFileManager(compiler.getStandardFileManager(null, null, null));
        context = new com.sun.tools.javac.util.Context();
        String[] args = options.prepJavacArgs();
        task = compiler.getTask(new PrintWriter(System.err),
                                fileManager,
                                null,
                                Arrays.asList(args),
                                null,
                                null,
                                context);
        // Trigger a creation of the JavaCompiler, necessary to get a sourceCompleter for ClassFinder.
        // The sourceCompleter is used for build situations where a classpath class references other classes
        // that happens to be on the sourcepath.
        JavaCompiler.instance(context);

//        context.put(JavaFileManager.class, fileManager);
    }

    public PubApi getPubApi(String fullyQualifiedClassName) {
        Symtab syms = Symtab.instance(context);
        ClassFinder cr = ClassFinder.instance(context);
        Names ns = Names.instance(context);
        Name n = ns.fromString(fullyQualifiedClassName);
        ClassSymbol cs = cr.loadClass(syms.inferModule(Convert.packagePart(n)), n);
        PubapiVisitor v = new PubapiVisitor();
        v.visit(cs);
        return v.getCollectedPubApi();
    }

    public void close() throws IOException {
        fileManager.close();
    }
}
