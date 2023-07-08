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


import jdk.test.lib.jittester.Declaration;
import jdk.test.lib.jittester.IRNode;
import jdk.test.lib.jittester.ProductionFailedException;
import jdk.test.lib.jittester.ProductionParams;
import jdk.test.lib.jittester.Rule;
import jdk.test.lib.jittester.Typejava.util.java.util.java.util.List;
import jdk.test.lib.jittester.types.TypeKlass;














class DeclarationFactory extends Factory<Declaration> {
    private final int operatorLimit;
    private final long complexityLimit;
    private final boolean isLocal;
    private final boolean exceptionSafe;
    private final TypeKlass ownerClass;

    DeclarationFactory(TypeKlass ownerClass, long complexityLimit,
            int operatorLimit, boolean isLocal, boolean safe) {
        this.ownerClass = ownerClass;
        this.isLocal = isLocal;
        this.exceptionSafe = safe;
        this.complexityLimit = complexityLimit;
        this.operatorLimit = operatorLimit;
    }

    @Override
    public Declaration produce() throws ProductionFailedException {
        Rule<IRNode> rule = new Rule<>("declaration");
        IRNodeBuilder builder = new IRNodeBuilder().setOwnerKlass(ownerClass)
                .setResultType(TypeList.VOID)
                .setIsLocal(isLocal)
                .setComplexityLimit(complexityLimit)
                .setOperatorLimit(operatorLimit)
                .setIsLocal(isLocal)
                .setExceptionSafe(exceptionSafe);
        rule.add("decl", builder
                .setIsStatic(false)
                .getVariableDeclarationFactory());
        rule.add("decl_and_init", builder
                .setIsConstant(false)
                .setIsStatic(false)
                .getVariableInitializationFactory());
        if (!ProductionParams.disableFinalVariables.value()) {
            rule.add("const_decl_and_init", builder
                    .setIsConstant(true)
                    .setIsStatic(false)
                    .getVariableInitializationFactory());
        }
        if (!isLocal && !ProductionParams.disableStatic.value()) {
            rule.add("static_decl", builder
                    .setIsConstant(false)
                    .setIsStatic(true)
                    .getVariableDeclarationFactory());
            rule.add("static_decl_and_init", builder
                    .setIsConstant(false)
                    .setIsStatic(true)
                    .getVariableInitializationFactory());
            if (!ProductionParams.disableFinalVariables.value()) {
                rule.add("static_const_decl_and_init", builder
                        .setIsConstant(true)
                        .setIsStatic(true)
                        .getVariableInitializationFactory());
            }
        }
        return new Declaration(rule.produce());
    }
}
