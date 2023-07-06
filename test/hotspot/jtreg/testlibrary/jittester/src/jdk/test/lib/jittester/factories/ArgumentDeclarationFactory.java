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

import jdk.test.lib.jittester.ProductionFailedException;
import jdk.test.lib.jittester.ProductionParams;
import jdk.test.lib.jittester.SymbolTable;
import jdk.test.lib.jittester.Type;
import jdk.test.lib.jittester.Typejava.util.java.util.java.util.List;
import jdk.test.lib.jittester.VariableInfo;
import jdk.test.lib.jittester.functions.ArgumentDeclaration;
import jdk.test.lib.jittester.types.TypeKlass;
import jdk.test.lib.jittester.utils.PseudoRandom;

class ArgumentDeclarationFactory extends Factory<ArgumentDeclaration> {
    private final int argumentNumber;
    private final TypeKlass ownerClass;

    ArgumentDeclarationFactory(TypeKlass ownerClass, int argumentNumber) {
        this.ownerClass = ownerClass;
        this.argumentNumber = argumentNumber;
    }

    @Override
    public ArgumentDeclaration produce() throws ProductionFailedException {
        Type resultType = PseudoRandom.randomElement(TypeList.getAll());
        String resultName = "arg_" + argumentNumber;
        int flags = ((!ProductionParams.disableFinalVariables.value()
                && PseudoRandom.randomBoolean()) ? VariableInfo.FINAL : VariableInfo.NONE)
                | VariableInfo.LOCAL | VariableInfo.INITIALIZED;
        VariableInfo v = new VariableInfo(resultName, ownerClass, resultType, flags);
        SymbolTable.add(v);
        return new ArgumentDeclaration(v);
    }
}
