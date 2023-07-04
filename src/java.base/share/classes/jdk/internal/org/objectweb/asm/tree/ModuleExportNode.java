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
 * A node that represents an exported package with its name and the module that can access to it.
 *
 */
public class ModuleExportNode {

    /** The internal name of the exported package. */
    public String packaze;

    /**
      * The access flags (see {@link jdk.internal.org.objectweb.asm.Opcodes}). Valid values are {@code
      * ACC_SYNTHETIC} and {@code ACC_MANDATED}.
      */
    public int access;

    /**
      * The list of modules that can access this exported package, specified with fully qualified names
      * (using dots). May be {@literal null}.
      */
    public List<String> modules;

    /**
      * Constructs a new {@link ModuleExportNode}.
      *
      * @param packaze the internal name of the exported package.
      * @param access the package access flags, one or more of {@code ACC_SYNTHETIC} and {@code
      *     ACC_MANDATED}.
      * @param modules a list of modules that can access this exported package, specified with fully
      *     qualified names (using dots).
      */
    public ModuleExportNode(final String packaze, final int access, final List<String> modules) {
        this.packaze = packaze;
        this.access = access;
        this.modules = modules;
    }

    /**
      * Makes the given module visitor visit this export declaration.
      *
      * @param moduleVisitor a module visitor.
      */
    public void accept(final ModuleVisitor moduleVisitor) {
        moduleVisitor.visitExport(
                packaze, access, modules == null ? null : modules.toArray(new String[0]));
    }
}
