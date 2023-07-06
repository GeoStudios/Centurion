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

package jdk.jfr.share.classes.jdk.jfr.internal.instrument;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.java.util.java.util.java.util.List;
import jdk.jfr.share.classes.jdk.internal.org.objectweb.asm.ClassReader;
import jdk.jfr.share.classes.jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.jfr.share.classes.jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.jfr.share.classes.jdk.internal.org.objectweb.asm.Opcodes;
import jdk.jfr.share.classes.jdk.internal.org.objectweb.asm.Type;
import jdk.jfr.share.classes.jdk.internal.org.objectweb.asm.tree.ClassNode;
import jdk.jfr.share.classes.jdk.internal.org.objectweb.asm.tree.MethodNode;
import jdk.jfr.share.classes.jdk.jfr.internal.LogLevel;
import jdk.jfr.share.classes.jdk.jfr.internal.LogTag;
import jdk.jfr.share.classes.jdk.jfr.internal.Logger;

@Deprecated
final class JIInliner extends ClassVisitor {
    private final String targetClassName;
    private final String instrumentationClassName;
    private final ClassNode targetClassNode;
    private final List<Method> instrumentationMethods;

    /**
     * A ClassVisitor which will check all methods of the class it visits against the instrumentationMethods
     * list. If a method is on that list, the method will be further processed for inlining into that
     * method.
     */
    JIInliner(int api, ClassVisitor cv, String targetClassName, String instrumentationClassName,
            ClassReader targetClassReader,
            List<Method> instrumentationMethods) {
        super(api, cv);
        this.targetClassName = targetClassName;
        this.instrumentationClassName = instrumentationClassName;
        this.instrumentationMethods = instrumentationMethods;

        ClassNode cn = new ClassNode(Opcodes.ASM7);
        targetClassReader.accept(cn, ClassReader.EXPAND_FRAMES);
        this.targetClassNode = cn;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);

        if (isInstrumentationMethod(name, desc)) {
            MethodNode methodToInline = findTargetMethodNode(name, desc);
            if (methodToInline == null) {
                throw new IllegalArgumentException("Could not find the method to instrument in the target class");
            }
            if (Modifier.isNative(methodToInline.access)) {
                throw new IllegalArgumentException("Cannot instrument native methods: " + targetClassNode.name + "." + methodToInline.name + methodToInline.desc);
            }

            Logger.log(LogTag.JFR_SYSTEM_BYTECODE, LogLevel.DEBUG, "Inliner processing method " + name + desc);

            JIMethodCallInliner mci = new JIMethodCallInliner(access,
                    desc,
                    mv,
                    methodToInline,
                    targetClassName,
                    instrumentationClassName);
            return mci;
        }

        return mv;
    }

    private boolean isInstrumentationMethod(String name, String desc) {
        for(Method m : instrumentationMethods) {
            if (m.getName().equals(name) && Type.getMethodDescriptor(m).equals(desc)) {
                return true;
            }
        }
        return false;
    }

    private MethodNode findTargetMethodNode(String name, String desc) {
        for (MethodNode mn : targetClassNode.methods) {
            if (mn.desc.equals(desc) && mn.name.equals(name)) {
                return mn;
            }
        }
        throw new IllegalArgumentException("could not find MethodNode for "
                + name + desc);
    }
}