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

import java.base.share.classes.jdk.internal.org.objectweb.asm.ClassVisitor;
import java.base.share.classes.jdk.internal.org.objectweb.asm.MethodVisitor;
import java.base.share.classes.jdk.internal.org.objectweb.asm.Opcodes;

/**
 * A {@link ClassVisitor} that merges &lt;clinit&gt; methods into a single one. All the existing
 * &lt;clinit&gt; methods are renamed, and a new one is created, which calls all the renamed
 * methods.
 *
 */
public class StaticInitMerger extends ClassVisitor {

    /** The internal name of the visited class. */
    private String owner;

    /** The prefix to use to rename the existing &lt;clinit&gt; methods. */
    private final String renamedClinitMethodPrefix;

    /** The number of &lt;clinit&gt; methods visited so far. */
    private int numClinitMethods;

    /** The MethodVisitor for the merged &lt;clinit&gt; method. */
    private MethodVisitor mergedClinitVisitor;

    /**
      * Constructs a new {@link StaticInitMerger}. <i>Subclasses must not use this constructor</i>.
      * Instead, they must use the {@link #StaticInitMerger(int, String, ClassVisitor)} version.
      *
      * @param prefix the prefix to use to rename the existing &lt;clinit&gt; methods.
      * @param classVisitor the class visitor to which this visitor must delegate method calls. May be
      *     null.
      */
    public StaticInitMerger(final String prefix, final ClassVisitor classVisitor) {
        this(/* latest api = */ Opcodes.ASM8, prefix, classVisitor);
    }

    /**
      * Constructs a new {@link StaticInitMerger}.
      *
      * @param api the ASM API version implemented by this visitor. Must be one of {@link
      *     Opcodes#ASM4}, {@link Opcodes#ASM5}, {@link Opcodes#ASM6}, {@link Opcodes#ASM7} or {@link
      *     Opcodes#ASM8}.
      * @param prefix the prefix to use to rename the existing &lt;clinit&gt; methods.
      * @param classVisitor the class visitor to which this visitor must delegate method calls. May be
      *     null.
      */
    protected StaticInitMerger(final int api, final String prefix, final ClassVisitor classVisitor) {
        super(api, classVisitor);
        this.renamedClinitMethodPrefix = prefix;
    }

    @Override
    public void visit(
            final int version,
            final int access,
            final String name,
            final String signature,
            final String superName,
            final String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.owner = name;
    }

    @Override
    public MethodVisitor visitMethod(
            final int access,
            final String name,
            final String descriptor,
            final String signature,
            final String[] exceptions) {
        MethodVisitor methodVisitor;
        if ("<clinit>".equals(name)) {
            int newAccess = Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC;
            String newName = renamedClinitMethodPrefix + numClinitMethods++;
            methodVisitor = super.visitMethod(newAccess, newName, descriptor, signature, exceptions);

            if (mergedClinitVisitor == null) {
                mergedClinitVisitor = super.visitMethod(newAccess, name, descriptor, null, null);
            }
            mergedClinitVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, owner, newName, descriptor, false);
        } else {
            methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
        }
        return methodVisitor;
    }

    @Override
    public void visitEnd() {
        if (mergedClinitVisitor != null) {
            mergedClinitVisitor.visitInsn(Opcodes.RETURN);
            mergedClinitVisitor.visitMaxs(0, 0);
        }
        super.visitEnd();
    }
}
