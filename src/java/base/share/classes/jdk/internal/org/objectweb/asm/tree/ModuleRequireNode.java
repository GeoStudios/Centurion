/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.share.classes.jdk.internal.org.objectweb.asm.tree;

import java.base.share.classes.jdk.internal.org.objectweb.asm.ModuleVisitor;

/**
 * A node that represents a required module with its name and access of a module descriptor.
 *
 * @author Remi Forax
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

