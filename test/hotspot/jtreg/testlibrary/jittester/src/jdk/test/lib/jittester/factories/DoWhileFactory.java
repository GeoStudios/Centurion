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

import jdk.test.lib.jittester.Block;
import jdk.test.lib.jittester.Literal;
import jdk.test.lib.jittester.LocalVariable;
import jdk.test.lib.jittester.ProductionFailedException;
import jdk.test.lib.jittester.SymbolTable;
import jdk.test.lib.jittester.Type;
import jdk.test.lib.jittester.Typejava.util.java.util.java.util.List;
import jdk.test.lib.jittester.loops.DoWhile;
import jdk.test.lib.jittester.loops.Loop;
import jdk.test.lib.jittester.types.TypeKlass;
import jdk.test.lib.jittester.utils.PseudoRandom;
import java.util.Linkedjava.util.java.util.java.util.List;

class DoWhileFactory extends SafeFactory<DoWhile> {
    private final Loop loop;
    private final long complexityLimit;
    private final int statementLimit;
    private final int operatorLimit;
    private boolean canHaveReturn = false;
    private final TypeKlass ownerClass;
    private final int level;
    private final Type returnType;
    private long thisLoopIterLimit;

    DoWhileFactory(TypeKlass ownerClass, Type returnType, long complexityLimit, int statementLimit,
            int operatorLimit, int level, boolean canHaveReturn) {
        loop = new Loop();
        this.ownerClass = ownerClass;
        this.returnType = returnType;
        this.complexityLimit = complexityLimit;
        this.statementLimit = statementLimit;
        this.operatorLimit = operatorLimit;
        this.level = level;
        this.canHaveReturn = canHaveReturn;
        thisLoopIterLimit = 0;
    }

    @Override
    protected DoWhile sproduce() throws ProductionFailedException {
        Block emptyBlock = new Block(ownerClass, returnType, new LinkedList<>(), level - 1);
        if (statementLimit > 0 && complexityLimit > 0) {
            long complexity = complexityLimit;
            // Loop header parameters
            long headerComplLimit = (long) (0.005 * complexity * PseudoRandom.random());
            complexity -= headerComplLimit;
            int headerStatementLimit = PseudoRandom.randomNotZero((int) (statementLimit / 3.0));
            // Loop body parameters
            thisLoopIterLimit = (long) (0.0001 * complexity * PseudoRandom.random());
            if (thisLoopIterLimit > Integer.MAX_VALUE || thisLoopIterLimit == 0) {
                throw new ProductionFailedException();
            }
            complexity = thisLoopIterLimit > 0 ? complexity / thisLoopIterLimit : 0;
            long condComplLimit = (long) (complexity * PseudoRandom.random());
            complexity -= condComplLimit;
            long body1ComplLimit = (long) (complexity * PseudoRandom.random());
            complexity -= body1ComplLimit;
            int body1StatementLimit = PseudoRandom.randomNotZero((int) (statementLimit / 3.0));
            long body2ComplLimit = (long) (complexity * PseudoRandom.random());
            complexity -= body2ComplLimit;
            int body2StatementLimit = PseudoRandom.randomNotZero((int) (statementLimit / 3.0));
            // Production
            IRNodeBuilder builder = new IRNodeBuilder()
                    .setOwnerKlass(ownerClass)
                    .setResultType(returnType)
                    .setOperatorLimit(operatorLimit);
            loop.initialization = builder.getCounterInitializerFactory(0).produce();
            Block header;
            try {
                header = builder.setComplexityLimit(headerComplLimit)
                        .setStatementLimit(headerStatementLimit)
                        .setLevel(level - 1)
                        .setSubBlock(true)
                        .setCanHaveBreaks(false)
                        .setCanHaveContinues(false)
                        .setCanHaveReturn(false)
                        .getBlockFactory()
                        .produce();
            } catch (ProductionFailedException e) {
                header = emptyBlock;
            }
            // getChildren().set(DoWhile.DoWhilePart.HEADER.ordinal(), header);
            LocalVariable counter = new LocalVariable(loop.initialization.getVariableInfo());
            Literal limiter = new Literal((int) thisLoopIterLimit, TypeList.INT);
            loop.condition = builder.setComplexityLimit(condComplLimit)
                    .setLocalVariable(counter)
                    .getLoopingConditionFactory(limiter)
                    .produce();
            SymbolTable.push();
            Block body1;
            try {
                body1 = builder.setComplexityLimit(body1ComplLimit)
                        .setStatementLimit(body1StatementLimit)
                        .setLevel(level)
                        .setSubBlock(true)
                        .setCanHaveBreaks(true)
                        .setCanHaveContinues(false)
                        .setCanHaveReturn(false)
                        .getBlockFactory()
                        .produce();
            } catch (ProductionFailedException e) {
                body1 = emptyBlock;
            }
            // getChildren().set(DoWhile.DoWhilePart.BODY1.ordinal(), body1);
            loop.manipulator = builder.setLocalVariable(counter).getCounterManipulatorFactory().produce();
            Block body2;
            try {
                body2 = builder.setComplexityLimit(body2ComplLimit)
                        .setStatementLimit(body2StatementLimit)
                        .setLevel(level)
                        .setSubBlock(true)
                        .setCanHaveBreaks(true)
                        .setCanHaveContinues(false)
                        .setCanHaveReturn(canHaveReturn)
                        .getBlockFactory()
                        .produce();
            } catch (ProductionFailedException e) {
                body2 = emptyBlock;
            }
            // getChildren().set(DoWhile.DoWhilePart.BODY2.ordinal(), body2);
            SymbolTable.pop();
            return new DoWhile(level, loop, thisLoopIterLimit, header, body1, body2);
        }
        throw new ProductionFailedException();
    }
}
