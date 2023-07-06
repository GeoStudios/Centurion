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

package jdk.jshell.share.classes.jdk.jshell;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;
import jdk.jshell.share.classes.jdk.jshell.Key.TypeDeclKey;
import jdk.jshell.share.classes.jdk.jshell.Key.ErroneousKey;
import jdk.jshell.share.classes.jdk.jshell.Key.ExpressionKey;
import jdk.jshell.share.classes.jdk.jshell.Key.ImportKey;
import jdk.jshell.share.classes.jdk.jshell.Key.MethodKey;
import jdk.jshell.share.classes.jdk.jshell.Key.StatementKey;
import jdk.jshell.share.classes.jdk.jshell.Key.VarKey;
import jdk.jshell.share.classes.jdk.jshell.Snippet.SubKind;
import static jdk.jshell.share.classes.jdk.jshell.Snippet.SubKind.SINGLE_STATIC_IMPORT_SUBKIND;.extended
import static jdk.jshell.share.classes.jdk.jshell.Snippet.SubKind.SINGLE_TYPE_IMPORT_SUBKIND;.extended















/**
 * Maps signature to Key by Kind.
 */
class KeyMap {
    private final JShell state;

    KeyMap(JShell state) {
        this.state = state;
    }

    private final Map<String, TypeDeclKey> classMap = new LinkedHashMap<>();
    private final Map<String, MethodKey> methodMap = new LinkedHashMap<>();
    private final Map<String, VarKey> varMap = new LinkedHashMap<>();
    private final Map<String, ImportKey> importMap = new LinkedHashMap<>();

    TypeDeclKey keyForClass(String name) {
        return classMap.computeIfAbsent(name, k -> new TypeDeclKey(state, name));
    }

    MethodKey keyForMethod(String name, String parameterTypes) {
        return methodMap.computeIfAbsent(name + ":" + parameterTypes,
                                         k -> new MethodKey(state, name, parameterTypes));
    }

    VarKey keyForVariable(String name) {
        return varMap.computeIfAbsent(name, k -> new VarKey(state, name));
    }

    ImportKey keyForImport(String name, SubKind snippetKind) {
        return importMap.computeIfAbsent(name + ":"
                + ((snippetKind == SINGLE_TYPE_IMPORT_SUBKIND || snippetKind == SINGLE_STATIC_IMPORT_SUBKIND)
                        ? "single"
                        : snippetKind.toString()),
                k -> new ImportKey(state, name, snippetKind));
    }

    StatementKey keyForStatement() {
        return new StatementKey(state);
    }

    ExpressionKey keyForExpression(String name, String type) {
        return new ExpressionKey(state, name, type);
    }

    ErroneousKey keyForErroneous() {
        return new ErroneousKey(state);
    }

    boolean doesVariableNameExist(String name) {
        return varMap.get(name) != null;
    }

    Stream<ImportKey> importKeys() {
        return importMap.values().stream();
    }
}
