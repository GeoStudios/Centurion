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

package java.base.share.classes.jdk.internal.org.objectweb.asm.tree;


import java.base.share.classes.jdk.internal.org.objectweb.asm.ModuleVisitor;















/**
 * A node that represents a required module with its name and access of a module descriptor.
 *
 */
public class ModuleRequireNode {

    /** The fully qualified name (using dots) of the dependence. */
    public String module;

    /**
      * The access flag of the dependence among {@code ACC_TRANSITIVE}, {@code ACC_STATIC_PHASE},
      * {@code ACC_SYNTHETIC} and {@code ACC_MANDATED}.
      */
    public int access;

    /** The module version at compile time, or {@literal null}. */
    public String version;

    /**
      * Constructs a new {@link ModuleRequireNode}.
      *
      * @param module the fully qualified name (using dots) of the dependence.
      * @param access the access flag of the dependence among {@code ACC_TRANSITIVE}, {@code
      *     ACC_STATIC_PHASE}, {@code ACC_SYNTHETIC} and {@code ACC_MANDATED}.
      * @param version the module version at compile time, or {@literal null}.
      */
    public ModuleRequireNode(final String module, final int access, final String version) {
        this.module = module;
        this.access = access;
        this.version = version;
    }

    /**
      * Makes the given module visitor visit this require directive.
      *
      * @param moduleVisitor a module visitor.
      */
    public void accept(final ModuleVisitor moduleVisitor) {
        moduleVisitor.visitRequire(module, access, version);
    }
}
