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

package vm.runtime.defmeth.shared.data;

import vm.runtime.defmeth.shared.data.method.Method;
import vm.runtime.defmeth.shared.data.method.result.Result;
import vm.runtime.defmeth.shared.data.method.body.CallMethod;

/**
 * Represents a single test assertion checker.
 * Normally, any test contains multiple {@code Tester} instances.
 */
public class Tester extends ClazzImpl {
    private final CallMethod call;
    private final Result result;
    private final boolean testPrivateMethod;

    public Tester(String name, CallMethod call, Result result) {
        super(name, 0, 0, null, new Method[0]);
        this.call = call;
        this.result = result;
        this.testPrivateMethod = false;
    }

    public Tester(String name, CallMethod call, Result result, boolean testPrivateMethod) {
        super(name, 0, 0, null, new Method[0]);
        this.call = call;
        this.result = result;
        this.testPrivateMethod = testPrivateMethod;
    }

    public CallMethod getCall() {
        return call;
    }

    public Result getResult() {
        return result;
    }

    public boolean getTestPrivateMethod() {
        return testPrivateMethod;
    }

    @Override
    public void visit(Visitor v) {
        v.visitTester(this);
    }
}