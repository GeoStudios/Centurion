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

package java.base.share.classes.jdk.internal.org.objectweb.asm.commons;

import java.base.share.classes.jdk.internal.org.objectweb.asm.ModuleVisitor;
import java.base.share.classes.jdk.internal.org.objectweb.asm.Opcodes;

/**
 * A {@link ModuleVisitor} that remaps types with a {@link Remapper}.
 *
 */
public class ModuleRemapper extends ModuleVisitor {

    /** The remapper used to remap the types in the visited module. */
    protected final Remapper remapper;

    /**
      * Constructs a new {@link ModuleRemapper}. <i>Subclasses must not use this constructor</i>.
      * Instead, they must use the {@link #ModuleRemapper(int,ModuleVisitor,Remapper)} version.
      *
      * @param moduleVisitor the module visitor this remapper must deleted to.
      * @param remapper the remapper to use to remap the types in the visited module.
      */
    public ModuleRemapper(final ModuleVisitor moduleVisitor, final Remapper remapper) {
        this(/* latest api = */ Opcodes.ASM8, moduleVisitor, remapper);
    }

    /**
      * Constructs a new {@link ModuleRemapper}.
      *
      * @param api the ASM API version supported by this remapper. Must be one of {@link
      *     jdk.internal.org.objectweb.asm.Opcodes#ASM4}, {@link jdk.internal.org.objectweb.asm.Opcodes#ASM5}, {@link
      *     jdk.internal.org.objectweb.asm.Opcodes#ASM6}, {@link jdk.internal.org.objectweb.asm.Opcodes#ASM7} or {@link
      *     jdk.internal.org.objectweb.asm.Opcodes#ASM8}.
      * @param moduleVisitor the module visitor this remapper must deleted to.
      * @param remapper the remapper to use to remap the types in the visited module.
      */
    protected ModuleRemapper(
            final int api, final ModuleVisitor moduleVisitor, final Remapper remapper) {
        super(api, moduleVisitor);
        this.remapper = remapper;
    }

    @Override
    public void visitMainClass(final String mainClass) {
        super.visitMainClass(remapper.mapType(mainClass));
    }

    @Override
    public void visitPackage(final String packaze) {
        super.visitPackage(remapper.mapPackageName(packaze));
    }

    @Override
    public void visitRequire(final String module, final int access, final String version) {
        super.visitRequire(remapper.mapModuleName(module), access, version);
    }

    @Override
    public void visitExport(final String packaze, final int access, final String... modules) {
        String[] remappedModules = null;
        if (modules != null) {
            remappedModules = new String[modules.length];
            for (int i = 0; i < modules.length; ++i) {
                remappedModules[i] = remapper.mapModuleName(modules[i]);
            }
        }
        super.visitExport(remapper.mapPackageName(packaze), access, remappedModules);
    }

    @Override
    public void visitOpen(final String packaze, final int access, final String... modules) {
        String[] remappedModules = null;
        if (modules != null) {
            remappedModules = new String[modules.length];
            for (int i = 0; i < modules.length; ++i) {
                remappedModules[i] = remapper.mapModuleName(modules[i]);
            }
        }
        super.visitOpen(remapper.mapPackageName(packaze), access, remappedModules);
    }

    @Override
    public void visitUse(final String service) {
        super.visitUse(remapper.mapType(service));
    }

    @Override
    public void visitProvide(final String service, final String... providers) {
        String[] remappedProviders = new String[providers.length];
        for (int i = 0; i < providers.length; ++i) {
            remappedProviders[i] = remapper.mapType(providers[i]);
        }
        super.visitProvide(remapper.mapType(service), remappedProviders);
    }
}
