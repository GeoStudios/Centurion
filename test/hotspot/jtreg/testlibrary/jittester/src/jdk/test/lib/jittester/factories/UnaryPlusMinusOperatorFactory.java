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


import jdk.test.lib.jittester.BuiltInType;
import jdk.test.lib.jittester.OperatorKind;
import jdk.test.lib.jittester.ProductionFailedException;
import jdk.test.lib.jittester.Type;
import jdk.test.lib.jittester.Typejava.util.java.util.java.util.List;
import jdk.test.lib.jittester.utils.TypeUtil;
import jdk.test.lib.jittester.UnaryOperator;
import jdk.test.lib.jittester.types.TypeKlass;
import jdk.test.lib.jittester.utils.PseudoRandom;














class UnaryPlusMinusOperatorFactory extends UnaryOperatorFactory {
    UnaryPlusMinusOperatorFactory(OperatorKind opKind, long complexityLimit, int operatorLimit,
            Type ownerClass, Type resultType, boolean exceptionSafe, boolean noconsts) {
        super(opKind, complexityLimit, operatorLimit, ownerClass, resultType, exceptionSafe, noconsts);
    }

    @Override
    protected boolean isApplicable(Type resultType) {
        if (!TypeList.isBuiltIn(resultType) || resultType.equals(TypeList.BOOLEAN)) {
            return false;
        }
        BuiltInType resType = (BuiltInType) resultType;
        return resType.equals(TypeList.INT) || resType.isMoreCapaciousThan(TypeList.INT);
    }

    @Override
    protected Type generateType() {
        if (resultType.equals(TypeList.INT)) {
            return PseudoRandom.randomElement(TypeUtil.getImplicitlyCastable(TypeList.getBuiltIn(), resultType));
        } else {
            return resultType;
        }
    }

    @Override
    protected UnaryOperator generateProduction(Type type) throws ProductionFailedException {
        return new UnaryOperator(opKind, new IRNodeBuilder()
                .setComplexityLimit(complexityLimit)
                .setOperatorLimit(operatorLimit)
                .setOwnerKlass((TypeKlass) ownerClass)
                .setResultType(type)
                .setExceptionSafe(exceptionSafe)
                .setNoConsts(noconsts)
                .getExpressionFactory()
                .produce());
    }
}
