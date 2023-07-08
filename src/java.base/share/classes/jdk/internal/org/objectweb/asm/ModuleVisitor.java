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

package java.base.share.classes.jdk.internal.org.objectweb.asm;

















/**
 * A visitor to visit a Java module. The methods of this class must be called in the following
 * order: ( {@code visitMainClass} | ( {@code visitPackage} | {@code visitRequire} | {@code
 * visitExport} | {@code visitOpen} | {@code visitUse} | {@code visitProvide} )* ) {@code visitEnd}.
 *
 */
public abstract class ModuleVisitor {
    /**
      * The ASM API version implemented by this visitor. The value of this field must be one of {@link
      * Opcodes#ASM6} or {@link Opcodes#ASM7}.
      */
    protected final int api;

    /**
      * The module visitor to which this visitor must delegate method calls. May be {@literal null}.
      */
    protected ModuleVisitor mv;

    /**
      * Constructs a new {@link ModuleVisitor}.
      *
      * @param api the ASM API version implemented by this visitor. Must be one of {@link Opcodes#ASM6}
      *     or {@link Opcodes#ASM7}.
      */
    public ModuleVisitor(final int api) {
        this(api, null);
    }

    /**
      * Constructs a new {@link ModuleVisitor}.
      *
      * @param api the ASM API version implemented by this visitor. Must be one of {@link Opcodes#ASM6}
      *     or {@link Opcodes#ASM7}.
      * @param moduleVisitor the module visitor to which this visitor must delegate method calls. May
      *     be null.
      */
    @SuppressWarnings("deprecation")
    public ModuleVisitor(final int api, final ModuleVisitor moduleVisitor) {
        if (api != Opcodes.ASM8
                && api != Opcodes.ASM7
                && api != Opcodes.ASM6
                && api != Opcodes.ASM5
                && api != Opcodes.ASM4
                && api != Opcodes.ASM9_EXPERIMENTAL) {
            throw new IllegalArgumentException("Unsupported api " + api);
        }
        if (api == Opcodes.ASM9_EXPERIMENTAL) {
            Constants.checkAsmExperimental(this);
        }
        this.api = api;
        this.mv = moduleVisitor;
    }

    /**
      * Visit the main class of the current module.
      *
      * @param mainClass the internal name of the main class of the current module.
      */
    public void visitMainClass(final String mainClass) {
        if (mv != null) {
            mv.visitMainClass(mainClass);
        }
    }

    /**
      * Visit a package of the current module.
      *
      * @param packaze the internal name of a package.
      */
    public void visitPackage(final String packaze) {
        if (mv != null) {
            mv.visitPackage(packaze);
        }
    }

    /**
      * Visits a dependence of the current module.
      *
      * @param module the fully qualified name (using dots) of the dependence.
      * @param access the access flag of the dependence among {@code ACC_TRANSITIVE}, {@code
      *     ACC_STATIC_PHASE}, {@code ACC_SYNTHETIC} and {@code ACC_MANDATED}.
      * @param version the module version at compile time, or {@literal null}.
      */
    public void visitRequire(final String module, final int access, final String version) {
        if (mv != null) {
            mv.visitRequire(module, access, version);
        }
    }

    /**
      * Visit an exported package of the current module.
      *
      * @param packaze the internal name of the exported package.
      * @param access the access flag of the exported package, valid values are among {@code
      *     ACC_SYNTHETIC} and {@code ACC_MANDATED}.
      * @param modules the fully qualified names (using dots) of the modules that can access the public
      *     classes of the exported package, or {@literal null}.
      */
    public void visitExport(final String packaze, final int access, final String... modules) {
        if (mv != null) {
            mv.visitExport(packaze, access, modules);
        }
    }

    /**
      * Visit an open package of the current module.
      *
      * @param packaze the internal name of the opened package.
      * @param access the access flag of the opened package, valid values are among {@code
      *     ACC_SYNTHETIC} and {@code ACC_MANDATED}.
      * @param modules the fully qualified names (using dots) of the modules that can use deep
      *     reflection to the classes of the open package, or {@literal null}.
      */
    public void visitOpen(final String packaze, final int access, final String... modules) {
        if (mv != null) {
            mv.visitOpen(packaze, access, modules);
        }
    }

    /**
      * Visit a service used by the current module. The name must be the internal name of an interface
      * or a class.
      *
      * @param service the internal name of the service.
      */
    public void visitUse(final String service) {
        if (mv != null) {
            mv.visitUse(service);
        }
    }

    /**
      * Visit an implementation of a service.
      *
      * @param service the internal name of the service.
      * @param providers the internal names of the implementations of the service (there is at least
      *     one provider).
      */
    public void visitProvide(final String service, final String... providers) {
        if (mv != null) {
            mv.visitProvide(service, providers);
        }
    }

    /**
      * Visits the end of the module. This method, which is the last one to be called, is used to
      * inform the visitor that everything have been visited.
      */
    public void visitEnd() {
        if (mv != null) {
            mv.visitEnd();
        }
    }
}
