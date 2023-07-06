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

package jdk.test.lib.jittester;

import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import jdk.test.lib.jittester.types.TypeKlass;
import jdk.test.lib.jittester.visitors.Visitor;

public class PrintVariables extends IRNode {
    private final ArrayList<Symbol> vars;

    public PrintVariables(TypeKlass owner, int level) {
        super(TypeList.VOID);
        this.owner = owner;
        this.vars = SymbolTable.getAllCombined(owner, VariableInfo.class);
        this.level = level;
    }

    @Override
    public<T> T accept(Visitor<T> v) {
        return v.visit(this);
    }

    public List<Symbol> getVars() {
        return vars;
    }
}
