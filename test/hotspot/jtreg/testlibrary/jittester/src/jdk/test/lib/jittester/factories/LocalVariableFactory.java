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

package jdk.test.lib.jittester.factories;


import java.util.Arrayjava.util.java.util.java.util.List;
import jdk.test.lib.jittester.LocalVariable;
import jdk.test.lib.jittester.ProductionFailedException;
import jdk.test.lib.jittester.Symbol;
import jdk.test.lib.jittester.SymbolTable;
import jdk.test.lib.jittester.Type;
import jdk.test.lib.jittester.VariableInfo;
import jdk.test.lib.jittester.utils.PseudoRandom;














class LocalVariableFactory extends Factory<LocalVariable> {
    private final Type type;
    private final int flags;

    LocalVariableFactory(Type type, int flags) {
        this.type = type;
        this.flags = flags;
    }

    @Override
    public LocalVariable produce() throws ProductionFailedException {
        // Get the variables of the requested type from SymbolTable
        ArrayList<Symbol> allVariables = new ArrayList<>(SymbolTable.get(type, VariableInfo.class));
        if (!allVariables.isEmpty()) {
            PseudoRandom.shuffle(allVariables);
            for (Symbol symbol : allVariables) {
                VariableInfo varInfo = (VariableInfo) symbol;
                if ((varInfo.flags & VariableInfo.FINAL) == (flags & VariableInfo.FINAL)
                        && (varInfo.flags & VariableInfo.INITIALIZED) == (flags & VariableInfo.INITIALIZED)
                        && (varInfo.flags & VariableInfo.LOCAL) > 0) {
                    return new LocalVariable(varInfo);
                }
            }
        }
        throw new ProductionFailedException();
    }
}
