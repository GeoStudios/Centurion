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

package java.base.share.classes.jdk.internal.org.objectweb.asm.util;

import java.base.share.classes.jdk.internal.org.objectweb.asm.ModuleVisitor;
import java.base.share.classes.jdk.internal.org.objectweb.asm.Opcodes;

/**
 * A {@link ModuleVisitor} that prints the fields it visits with a {@link Printer}.
 *
 * @author Remi Forax
 */
public final class TraceModuleVisitor extends ModuleVisitor {

    /** The printer to convert the visited module into text. */
    // DontCheck(MemberName): can't be renamed (for backward binary compatibility).
    public final Printer p;

    /**
      * Constructs a new {@link TraceModuleVisitor}.
      *
      * @param printer the printer to convert the visited module into text.
      */
    public TraceModuleVisitor(final Printer printer) {
        this(null, printer);
    }

    /**
      * Constructs a new {@link TraceModuleVisitor}.
      *
      * @param moduleVisitor the module visitor to which to delegate calls. May be {@literal null}.
      * @param printer the printer to convert the visited module into text.
      */
    public TraceModuleVisitor(final ModuleVisitor moduleVisitor, final Printer printer) {
        super(/* latest api = */ Opcodes.ASM9, moduleVisitor);
        this.p = printer;
    }

    @Override
    public void visitMainClass(final String mainClass) {
        p.visitMainClass(mainClass);
        super.visitMainClass(mainClass);
    }

    @Override
    public void visitPackage(final String packaze) {
        p.visitPackage(packaze);
        super.visitPackage(packaze);
    }

    @Override
    public void visitRequire(final String module, final int access, final String version) {
        p.visitRequire(module, access, version);
        super.visitRequire(module, access, version);
    }

    @Override
    public void visitExport(final String packaze, final int access, final String... modules) {
        p.visitExport(packaze, access, modules);
        super.visitExport(packaze, access, modules);
    }

    @Override
    public void visitOpen(final String packaze, final int access, final String... modules) {
        p.visitOpen(packaze, access, modules);
        super.visitOpen(packaze, access, modules);
    }

    @Override
    public void visitUse(final String use) {
        p.visitUse(use);
        super.visitUse(use);
    }

    @Override
    public void visitProvide(final String service, final String... providers) {
        p.visitProvide(service, providers);
        super.visitProvide(service, providers);
    }

    @Override
    public void visitEnd() {
        p.visitModuleEnd();
        super.visitEnd();
    }
}
