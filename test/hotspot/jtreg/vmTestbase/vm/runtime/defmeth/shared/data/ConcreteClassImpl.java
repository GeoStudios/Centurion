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

/**
 * Represents "concrete" class (not interface).
 */
public class ConcreteClassImpl extends ClazzImpl implements ConcreteClass {

    ConcreteClass parent;
    Interface[] interfaces;

    public ConcreteClassImpl(String name, int flags, int ver, String sig, ConcreteClass parent,
            Interface[] interfaces, Method... methods)
    {
        super(name, ver, flags, sig, methods);
        this.parent = parent;
        this.interfaces = interfaces;
    }

    @Override
    public ConcreteClass parent() {
        return parent;
    }

    @Override
    public Interface[] interfaces() {
        return interfaces;
    }

    @Override
    public void visit(Visitor v) {
        v.visitConcreteClass(this);
    }
}
