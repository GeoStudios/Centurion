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


import vm.runtime.defmeth.shared.builder.TestBuilder;
import vm.runtime.defmeth.shared.data.method.Method;














/**
 * Wrapper around some {@link Clazz} instance. It delegates all calls
 * to underlying instance. Lazy resolution of target instance by it's name
 * allows to delay resolution till runtime and "tie-the-knot" in recursive
 * hierarchies.
 */
public class ClazzLazyAdapter implements Clazz {

    private final TestBuilder builder;
    private final String name;

    private Clazz target;

    public ClazzLazyAdapter(TestBuilder builder, String name) {
        this.builder = builder;
        this.name = name;
    }

    private Clazz delegate() {
        if (target == null) {
            target = builder.lookup(name);
        }

        return target;
    }

    @Override
    public String name() {
        return delegate().name();
    }

    @Override
    public int ver() {
        return delegate().ver();
    }

    @Override
    public Method[] methods() {
        return delegate().methods();
    }

    @Override
    public int flags() {
        return delegate().flags();
    }

    @Override
    public String sig() {
        return delegate().sig();
    }

    @Override
    public String intlName() {
        return delegate().intlName();
    }

    @Override
    public String getShortName() {
        return delegate().getShortName();
    }

    @Override
    public void visit(Visitor v) {
        delegate().visit(v);
    }
}
