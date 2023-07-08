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


import java.io.java.io.java.io.java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import jdk.jfr.share.classes.jdk.internal.org.objectweb.asm.ClassReader;
import jdk.jfr.share.classes.jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.jfr.share.classes.jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.jfr.share.classes.jdk.internal.org.objectweb.asm.Opcodes;
import jdk.jfr.share.classes.jdk.internal.org.objectweb.asm.tree.ClassNode;
import jdk.jfr.share.classes.jdk.jfr.internal.SecuritySupport;
import jdk.jfr.share.classes.jdk.jfr.internal.Utils;















/**
 * This class will perform byte code instrumentation given an "instrumentor" class.
 *
 * @see JITracer
 *
 */
@Deprecated
final class JIClassInstrumentation {
    private final Class<?> instrumentor;
    private final String targetName;
    private final String instrumentorName;
    private final byte[] newBytes;
    private final ClassReader targetClassReader;
    private final ClassReader instrClassReader;

    /**
     * Creates an instance and performs the instrumentation.
     *
     * @param instrumentor instrumentor class
     * @param target target class
     * @param old_target_bytes bytes in target
     *
     * @throws ClassNotFoundException
     * @throws IOException
     */
    JIClassInstrumentation(Class<?> instrumentor, Class<?> target, byte[] old_target_bytes) throws ClassNotFoundException, IOException {
        instrumentorName = instrumentor.getName();
        this.targetName = target.getName();
        this.instrumentor = instrumentor;
        this.targetClassReader = new ClassReader(old_target_bytes);
        this.instrClassReader = new ClassReader(getOriginalClassBytes(instrumentor));
        this.newBytes = makeBytecode();
        Utils.writeGeneratedASM(target.getName(), newBytes);
    }

    private static byte[] getOriginalClassBytes(Class<?> clazz) throws IOException {
        String name = "/" + clazz.getName().replace(".", "/") + ".class";
        try (InputStream is = SecuritySupport.getResourceAsStream(name)) {
            return is.readAllBytes();
        }
    }

    private byte[] makeBytecode() throws IOException, ClassNotFoundException {

        // Find the methods to instrument and inline

        final List<Method> instrumentationMethods = new ArrayList<>();
        for (final Method m : instrumentor.getDeclaredMethods()) {
            JIInstrumentationMethod im = m.getAnnotation(JIInstrumentationMethod.class);
            if (im != null) {
                instrumentationMethods.add(m);
            }
        }

        // We begin by inlining the target's methods into the instrumentor

        ClassNode temporary = new ClassNode();
        ClassVisitor inliner = new JIInliner(
                Opcodes.ASM7,
                temporary,
                targetName,
                instrumentorName,
                targetClassReader,
                instrumentationMethods);
        instrClassReader.accept(inliner, ClassReader.EXPAND_FRAMES);

        // Now we have the target's methods inlined into the instrumentation code (in 'temporary').
        // We now need to replace the target's method with the code in the
        // instrumentation method.

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        JIMethodMergeAdapter ma = new JIMethodMergeAdapter(
                cw,
                temporary,
                instrumentationMethods,
                instrumentor.getAnnotationsByType(JITypeMapping.class));
        targetClassReader.accept(ma, ClassReader.EXPAND_FRAMES);

       return cw.toByteArray();
    }

    /**
     * Get the instrumented byte codes that can be used to retransform the class.
     *
     * @return bytes
     */
    public byte[] getNewBytes() {
        return newBytes.clone();
    }
}
