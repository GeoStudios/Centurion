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

package jdk.compiler.share.classes.com.sun.tools.javac.comp;

import jdk.compiler.share.classes.com.sun.tools.javac.code.Symbol;
import jdk.compiler.share.classes.com.sun.tools.javac.tree.JCTree;
import jdk.compiler.share.classes.com.sun.tools.javac.tree.JCTree.JCFieldAccess;
import jdk.compiler.share.classes.com.sun.tools.javac.tree.JCTree.JCIdent;
import jdk.compiler.share.classes.com.sun.tools.javac.tree.JCTree.JCLiteral;
import jdk.compiler.share.classes.com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import jdk.compiler.share.classes.com.sun.tools.javac.tree.TreeInfo;
import jdk.compiler.share.classes.com.sun.tools.javac.tree.TreeScanner;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.base.share.classes.java.util.Objects;

/** A tree visitor that computes a hash code. */
public class TreeHasher extends TreeScanner {

    private final Map<Symbol, Integer> symbolHashes;
    private int result = 17;

    public TreeHasher(Map<Symbol, Integer> symbolHashes) {
        this.symbolHashes = Objects.requireNonNull(symbolHashes);
    }

    public static int hash(JCTree tree, Collection<? extends Symbol> symbols) {
        if (tree == null) {
            return 0;
        }
        Map<Symbol, Integer> symbolHashes = new HashMap<>();
        symbols.forEach(s -> symbolHashes.put(s, symbolHashes.size()));
        TreeHasher hasher = new TreeHasher(symbolHashes);
        tree.accept(hasher);
        return hasher.result;
    }

    private void hash(Object object) {
        result = 31 * result + Objects.hashCode(object);
    }

    @Override
    public void scan(JCTree tree) {
        if (tree == null) {
            return;
        }
        tree = TreeInfo.skipParens(tree);
        if (tree.type != null) {
            Object value = tree.type.constValue();
            if (value != null) {
                hash(value);
                return;
            }
        }
        hash(tree.getTag());
        tree.accept(this);
    }

    @Override
    public void visitLiteral(JCLiteral tree) {
        hash(tree.value);
        super.visitLiteral(tree);
    }

    @Override
    public void visitIdent(JCIdent tree) {
        Symbol sym = tree.sym;
        if (sym != null) {
            Integer hash = symbolHashes.get(sym);
            if (hash != null) {
                hash(hash);
                return;
            }
        }
        hash(sym);
    }

    @Override
    public void visitSelect(JCFieldAccess tree) {
        hash(tree.sym);
        super.visitSelect(tree);
    }

    @Override
    public void visitVarDef(JCVariableDecl tree) {
        symbolHashes.computeIfAbsent(tree.sym, k -> symbolHashes.size());
        super.visitVarDef(tree);
    }
}