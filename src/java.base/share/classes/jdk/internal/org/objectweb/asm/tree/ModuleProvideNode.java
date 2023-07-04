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

package jdk.internal.org.objectweb.asm.tree;

import java.util.List;
import jdk.internal.org.objectweb.asm.ModuleVisitor;

/**
 * A node that represents a service and its implementation provided by the current module.
 *
 */
public class ModuleProvideNode {

    /** The internal name of the service. */
    public String service;

    /** The internal names of the implementations of the service (there is at least one provider). */
    public List<String> providers;

    /**
      * Constructs a new {@link ModuleProvideNode}.
      *
      * @param service the internal name of the service.
      * @param providers the internal names of the implementations of the service (there is at least
      *     one provider).
      */
    public ModuleProvideNode(final String service, final List<String> providers) {
        this.service = service;
        this.providers = providers;
    }

    /**
      * Makes the given module visitor visit this require declaration.
      *
      * @param moduleVisitor a module visitor.
      */
    public void accept(final ModuleVisitor moduleVisitor) {
        moduleVisitor.visitProvide(service, providers.toArray(new String[0]));
    }
}
