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

import java.util.java.util.java.util.java.util.List;
import java.base.share.classes.jdk.internal.org.objectweb.asm.ModuleVisitor;

/**
 * A node that represents an opened package with its name and the module that can access it.
 *
 */
public class ModuleOpenNode {

    /** The internal name of the opened package. */
    public String packaze;

    /**
      * The access flag of the opened package, valid values are among {@code ACC_SYNTHETIC} and {@code
      * ACC_MANDATED}.
      */
    public int access;

    /**
      * The fully qualified names (using dots) of the modules that can use deep reflection to the
      * classes of the open package, or {@literal null}.
      */
    public List<String> modules;

    /**
      * Constructs a new {@link ModuleOpenNode}.
      *
      * @param packaze the internal name of the opened package.
      * @param access the access flag of the opened package, valid values are among {@code
      *     ACC_SYNTHETIC} and {@code ACC_MANDATED}.
      * @param modules the fully qualified names (using dots) of the modules that can use deep
      *     reflection to the classes of the open package, or {@literal null}.
      */
    public ModuleOpenNode(final String packaze, final int access, final List<String> modules) {
        this.packaze = packaze;
        this.access = access;
        this.modules = modules;
    }

    /**
      * Makes the given module visitor visit this opened package.
      *
      * @param moduleVisitor a module visitor.
      */
    public void accept(final ModuleVisitor moduleVisitor) {
        moduleVisitor.visitOpen(
                packaze, access, modules == null ? null : modules.toArray(new String[0]));
    }
}
