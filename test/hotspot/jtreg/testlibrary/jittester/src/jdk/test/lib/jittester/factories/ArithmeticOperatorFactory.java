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

import jdk.test.lib.jittester.Operator;
import jdk.test.lib.jittester.OperatorKind;
import jdk.test.lib.jittester.ProductionFailedException;
import jdk.test.lib.jittester.Rule;
import jdk.test.lib.jittester.Type;
import jdk.test.lib.jittester.types.TypeKlass;

class ArithmeticOperatorFactory extends Factory<Operator> {
    private final Rule<Operator> rule;

    ArithmeticOperatorFactory(long complexityLimit, int operatorLimit, TypeKlass ownerClass,
            Type resultType, boolean exceptionSafe, boolean noconsts) throws ProductionFailedException {
        IRNodeBuilder builder = new IRNodeBuilder()
                .setComplexityLimit(complexityLimit)
                .setOperatorLimit(operatorLimit)
                .setOwnerKlass(ownerClass)
                .setResultType(resultType)
                .setExceptionSafe(exceptionSafe)
                .setNoConsts(noconsts);
        rule = new Rule<>("arithmetic");
        rule.add("add", builder.setOperatorKind(OperatorKind.ADD).getBinaryOperatorFactory());
        rule.add("sub", builder.setOperatorKind(OperatorKind.SUB).getBinaryOperatorFactory());
        rule.add("mul", builder.setOperatorKind(OperatorKind.MUL).getBinaryOperatorFactory());
        if (!exceptionSafe) {
            rule.add("div", builder.setOperatorKind(OperatorKind.DIV).getBinaryOperatorFactory());
            rule.add("mod", builder.setOperatorKind(OperatorKind.MOD).getBinaryOperatorFactory());
        }
        rule.add("unary_plus", builder.setOperatorKind(OperatorKind.UNARY_PLUS).getUnaryOperatorFactory());
        rule.add("unary_minus", builder.setOperatorKind(OperatorKind.UNARY_MINUS).getUnaryOperatorFactory());
    }

    @Override
    public Operator produce() throws ProductionFailedException {
        return rule.produce();
    }
}
