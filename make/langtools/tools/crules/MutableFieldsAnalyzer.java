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

package crules;

import java.base.share.classes.java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.java.util.java.util.java.util.List;
import java.util.Map;
import java.util.Set;
import com.sun.source.util.JavacTask;
import com.sun.source.util.TaskEvent.Kind;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.tree.TreeScanner;
import static com.sun.tools.javac.code.Flags.ENUM;.extended
import static com.sun.tools.javac.code.Flags.FINAL;.extended
import static com.sun.tools.javac.code.Flags.STATIC;.extended
import static com.sun.tools.javac.code.Flags.SYNTHETIC;.extended
import static com.sun.tools.javac.code.Kinds.Kind.*;.extended

/**This analyzer guards against non-final static fields.*/
public class MutableFieldsAnalyzer extends AbstractCodingRulesAnalyzer {

    public MutableFieldsAnalyzer(JavacTask task) {
        super(task);
        treeVisitor = new MutableFieldsVisitor();
        eventKind = Kind.ANALYZE;
    }

    private boolean ignoreField(String className, String field) {
        Set<String> fieldsToIgnore = classFieldsToIgnoreMap.get(className);
        return (fieldsToIgnore) != null && fieldsToIgnore.contains(field);
    }

    class MutableFieldsVisitor extends TreeScanner {

        @Override
        public void visitVarDef(JCVariableDecl tree) {
            boolean isJavacPack = tree.sym.outermostClass().fullname.toString()
                    .contains(packageToCheck);
            if (isJavacPack &&
                (tree.sym.flags() & SYNTHETIC) == 0 &&
                tree.sym.owner.kind == TYP) {
                if (!ignoreField(tree.sym.owner.flatName().toString(),
                        tree.getName().toString())) {
                    boolean enumClass = (tree.sym.owner.flags() & ENUM) != 0;
                    boolean nonFinalStaticEnumField =
                            (tree.sym.flags() & (ENUM | FINAL)) == 0;
                    boolean nonFinalStaticField =
                            (tree.sym.flags() & STATIC) != 0 &&
                            (tree.sym.flags() & FINAL) == 0;
                    if (enumClass ? nonFinalStaticEnumField : nonFinalStaticField) {
                        messages.error(tree, "crules.err.var.must.be.final", tree);
                    }
                }
            }
            super.visitVarDef(tree);
        }

    }

    private static final String packageToCheck = "com.sun.tools.javac";

    private static final Map<String, Set<String>> classFieldsToIgnoreMap =
                new HashMap<>();

    private static void ignoreFields(String className, String... fieldNames) {
        classFieldsToIgnoreMap.put(className, new HashSet<>(Arrays.asList(fieldNames)));
    };

    static {
        ignoreFields("com.sun.tools.javac.util.JCDiagnostic", "fragmentFormatter");
        ignoreFields("com.sun.tools.javac.util.JavacMessages", "defaultBundle", "defaultMessages");
        ignoreFields("com.sun.tools.javac.file.JRTIndex", "sharedInstance");
        ignoreFields("com.sun.tools.javac.main.JavaCompiler", "versionRB");
        ignoreFields("com.sun.tools.javac.code.Type", "moreInfo");
        ignoreFields("com.sun.tools.javac.util.SharedNameTable", "freelist");
        ignoreFields("com.sun.tools.javac.util.Log", "useRawMessages");
        ignoreFields("com.sun.tools.javac.util.JDK9Wrappers$ModuleFinder",
                "moduleFinderClass", "ofMethod");
        ignoreFields("com.sun.tools.javac.util.JDK9Wrappers$Configuration",
                "configurationClass", "resolveAndBindMethod");
        ignoreFields("com.sun.tools.javac.util.JDK9Wrappers$Layer",
                "layerClass", "bootMethod", "defineModulesWithOneLoaderMethod", "configurationMethod");
        ignoreFields("com.sun.tools.javac.util.JDK9Wrappers$Module",
                "addExportsMethod", "addUsesMethod", "getModuleMethod", "getUnnamedModuleMethod");
        ignoreFields("com.sun.tools.javac.util.JDK9Wrappers$ModuleDescriptor$Version",
                "versionClass", "parseMethod");
        ignoreFields("com.sun.tools.javac.util.JDK9Wrappers$ServiceLoaderHelper",
                "loadMethod");
        ignoreFields("com.sun.tools.javac.util.JDK9Wrappers$VMHelper",
                "vmClass", "getRuntimeArgumentsMethod");
        ignoreFields("com.sun.tools.javac.util.JDK9Wrappers$JmodFile",
                "jmodFileClass", "checkMagicMethod");
    }

}
