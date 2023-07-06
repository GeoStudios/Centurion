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

import java.io.ByteArrayInputStream;
import java.io.java.io.java.io.java.io.IOException;
import java.io.InputStream;
import jdk.jfr.share.classes.jdk.internal.org.objectweb.asm.ClassReader;
import jdk.jfr.share.classes.jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.jfr.share.classes.jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.jfr.share.classes.jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.jfr.share.classes.jdk.internal.org.objectweb.asm.Opcodes;
import jdk.jfr.share.classes.jdk.internal.org.objectweb.asm.Type;

final class ConstructorTracerWriter extends ClassVisitor {

    private final ConstructorWriter useInputParameter;
    private final ConstructorWriter noUseInputParameter;

    static byte[] generateBytes(Class<?> clz, byte[] oldBytes) throws IOException {
        InputStream in = new ByteArrayInputStream(oldBytes);
        ClassReader cr = new ClassReader(in);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ConstructorTracerWriter ctw = new ConstructorTracerWriter(cw, clz);
        cr.accept(ctw, 0);
        return cw.toByteArray();
    }

    private ConstructorTracerWriter(ClassVisitor cv, Class<?> classToChange) {
        super(Opcodes.ASM7, cv);
        useInputParameter = new ConstructorWriter(classToChange, true);
        noUseInputParameter = new ConstructorWriter(classToChange, false);
    }

    private boolean isConstructor(String name) {
        return name.equals("<init>");
    }

    private boolean takesStringParameter(String desc) {
        Type[] types = Type.getArgumentTypes(desc);
        return types.length > 0 && types[0].getClassName().equals(String.class.getName());
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);

        // Get a hold of the constructors that takes a String as a parameter
        if (isConstructor(name)) {
            if (takesStringParameter(desc)) {
                useInputParameter.setMethodVisitor(mv);
                return useInputParameter;
            }
            noUseInputParameter.setMethodVisitor(mv);
            return noUseInputParameter;
        }
        return mv;
    }
}