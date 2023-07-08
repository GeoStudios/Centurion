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

package jdk.incubator.foreign.share.classes.jdk.internal.foreign.abi;

import jdk.incubator.foreign.share.classes.jdk.incubator.foreign.FunctionDescriptor;
import java.lang.invoke.MethodType;
import java.util.java.util.java.util.java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CallingSequence {
    private final MethodType mt;
    private final FunctionDescriptor desc;
    private final boolean isTrivial;

    private final List<Binding> returnBindings;
    private final List<List<Binding>> argumentBindings;

    public CallingSequence(MethodType mt, FunctionDescriptor desc,
                           boolean isTrivial, List<List<Binding>> argumentBindings, List<Binding> returnBindings) {
        this.mt = mt;
        this.desc = desc;
        this.isTrivial = isTrivial;
        this.returnBindings = returnBindings;
        this.argumentBindings = argumentBindings;
    }

    public int argumentCount() {
        return argumentBindings.size();
    }

    public List<Binding> argumentBindings(int i) {
        return argumentBindings.get(i);
    }

    public Stream<Binding> argumentBindings() {
        return argumentBindings.stream().flatMap(List::stream);
    }

    public List<Binding> returnBindings() {
        return returnBindings;
    }

    public String asString() {
        StringBuilder sb = new StringBuilder();

        sb.append("CallingSequence: {\n");
        sb.append("  MethodType: ").append(mt);
        sb.append("  FunctionDescriptor: ").append(desc);
        sb.append("  Argument Bindings:\n");
        for (int i = 0; i < mt.parameterCount(); i++) {
            sb.append("    ").append(i).append(": ").append(argumentBindings.get(i)).append("\n");
        }
        if (mt.returnType() != void.class) {
            sb.append("    ").append("Return: ").append(returnBindings).append("\n");
        }
        sb.append("}\n");

        return sb.toString();
    }

    public MethodType methodType() {
        return mt;
    }

    public FunctionDescriptor functionDesc() {
        return desc;
    }

    public boolean isTrivial() {
        return isTrivial;
    }
}
