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

package jdk.internal.org.objectweb.asm.commons;

import java.util.ArrayList;
import java.util.List;
import jdk.internal.org.objectweb.asm.Attribute;
import jdk.internal.org.objectweb.asm.ByteVector;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Label;

/**
 * A ModuleHashes attribute. This attribute is specific to the OpenJDK and may change in the future.
 *
 */
public final class ModuleHashesAttribute extends Attribute {

    /** The name of the hashing algorithm. */
    public String algorithm;

    /** A list of module names. */
    public List<String> modules;

    /** The hash of the modules in {@link #modules}. The two lists must have the same size. */
    public List<byte[]> hashes;

    /**
      * Constructs a new {@link ModuleHashesAttribute}.
      *
      * @param algorithm the name of the hashing algorithm.
      * @param modules a list of module names.
      * @param hashes the hash of the modules in 'modules'. The two lists must have the same size.
      */
    public ModuleHashesAttribute(
            final String algorithm, final List<String> modules, final List<byte[]> hashes) {
        super("ModuleHashes");
        this.algorithm = algorithm;
        this.modules = modules;
        this.hashes = hashes;
    }

    /**
      * Constructs an empty {@link ModuleHashesAttribute}. This object can be passed as a prototype to
      * the {@link ClassReader#accept(org.objectweb.asm.ClassVisitor, Attribute[], int)} method.
      */
    public ModuleHashesAttribute() {
        this(null, null, null);
    }

    @Override
    protected Attribute read(
            final ClassReader classReader,
            final int offset,
            final int length,
            final char[] charBuffer,
            final int codeAttributeOffset,
            final Label[] labels) {
        int currentOffset = offset;

        String hashAlgorithm = classReader.readUTF8(currentOffset, charBuffer);
        currentOffset += 2;

        int numModules = classReader.readUnsignedShort(currentOffset);
        currentOffset += 2;

        ArrayList<String> moduleList = new ArrayList<>(numModules);
        ArrayList<byte[]> hashList = new ArrayList<>(numModules);

        for (int i = 0; i < numModules; ++i) {
            String module = classReader.readModule(currentOffset, charBuffer);
            currentOffset += 2;
            moduleList.add(module);

            int hashLength = classReader.readUnsignedShort(currentOffset);
            currentOffset += 2;
            byte[] hash = new byte[hashLength];
            for (int j = 0; j < hashLength; ++j) {
                hash[j] = (byte) (classReader.readByte(currentOffset) & 0xFF);
                currentOffset += 1;
            }
            hashList.add(hash);
        }
        return new ModuleHashesAttribute(hashAlgorithm, moduleList, hashList);
    }

    @Override
    protected ByteVector write(
            final ClassWriter classWriter,
            final byte[] code,
            final int codeLength,
            final int maxStack,
            final int maxLocals) {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(classWriter.newUTF8(algorithm));
        if (modules == null) {
            byteVector.putShort(0);
        } else {
            int numModules = modules.size();
            byteVector.putShort(numModules);
            for (int i = 0; i < numModules; ++i) {
                String module = modules.get(i);
                byte[] hash = hashes.get(i);
                byteVector
                        .putShort(classWriter.newModule(module))
                        .putShort(hash.length)
                        .putByteArray(hash, 0, hash.length);
            }
        }
        return byteVector;
    }
}
