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

package jdk.test.lib.jittester.loops;

import jdk.test.lib.jittester.IRNode;
import jdk.test.lib.jittester.LocalVariable;
import jdk.test.lib.jittester.Statement;
import jdk.test.lib.jittester.visitors.Visitor;

/*
 * Note: Can be theoretically subclassed from Operator and have an
 * operatorPriority field. Therefore, it can used later as a part
 * of some expression.
 */

public class CounterManipulator extends IRNode {
    LocalVariable counter;

    public CounterManipulator(Statement manipulator) {
        super(manipulator.getResultType());
        addChild(manipulator);
    }

    @Override
    public long complexity() {
        IRNode manipulator = getChild(0);
        return manipulator != null ? manipulator.complexity() : 0;
    }

    @Override
    public<T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}