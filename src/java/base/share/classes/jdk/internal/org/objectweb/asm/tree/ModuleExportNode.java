/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.jdk.internal.org.objectweb.asm.tree;

import java.util.List;
import jdk.internal.org.objectweb.asm.ModuleVisitor;

/**
 * A node that represents an exported package with its name and the module that can access to it.
 *
 * @author Remi Forax
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

