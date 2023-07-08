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

package java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.java.io.java.io.java.io.IOException;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * base class for parameter annotations
 *
 */
public abstract class ParameterAnnotations extends Attribute {

    /** Table of parameter annotations */
    private ParameterAnnotationEntry[] parameterAnnotationTable;

    /**
     * @param parameter_annotation_type the subclass type of the parameter annotation
     * @param name_index Index pointing to the name <em>Code</em>
     * @param length Content length in bytes
     * @param input Input stream
     * @param constant_pool Array of constants
     */
    ParameterAnnotations(final byte parameter_annotation_type, final int name_index, final int length,
            final DataInput input, final ConstantPool constant_pool) throws IOException {
        this(parameter_annotation_type, name_index, length, (ParameterAnnotationEntry[]) null,
                constant_pool);
        final int num_parameters = input.readUnsignedByte();
        parameterAnnotationTable = new ParameterAnnotationEntry[num_parameters];
        for (int i = 0; i < num_parameters; i++) {
            parameterAnnotationTable[i] = new ParameterAnnotationEntry(input, constant_pool);
        }
    }

    /**
     * @param parameterAnnotationType the subclass type of the parameter annotation
     * @param nameIndex Index pointing to the name <em>Code</em>
     * @param length Content length in bytes
     * @param parameterAnnotationTable the actual parameter annotations
     * @param constantPool Array of constants
     */
    public ParameterAnnotations(final byte parameterAnnotationType, final int nameIndex, final int length,
            final ParameterAnnotationEntry[] parameterAnnotationTable, final ConstantPool constantPool) {
        super(parameterAnnotationType, nameIndex, length, constantPool);
        this.parameterAnnotationTable = parameterAnnotationTable;
    }

    /**
     * Called by objects that are traversing the nodes of the tree implicitely
     * defined by the contents of a Java class. I.e., the hierarchy of methods,
     * fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    @Override
    public void accept( final Visitor v ) {
        v.visitParameterAnnotation(this);
    }

    /**
     * @param parameterAnnotationTable the entries to set in this parameter annotation
     */
    public final void setParameterAnnotationTable(final ParameterAnnotationEntry[] parameterAnnotationTable ) {
        this.parameterAnnotationTable = parameterAnnotationTable;
    }

    /**
     * @return the parameter annotation entry table
     */
    public final ParameterAnnotationEntry[] getParameterAnnotationTable() {
        return parameterAnnotationTable;
    }

    /**
     * returns the array of parameter annotation entries in this parameter annotation
     */
    public ParameterAnnotationEntry[] getParameterAnnotationEntries() {
        return parameterAnnotationTable;
    }

    @Override
    public void dump(final DataOutputStream dos) throws IOException
    {
        super.dump(dos);
        dos.writeByte(parameterAnnotationTable.length);

        for (final ParameterAnnotationEntry element : parameterAnnotationTable) {
            element.dump(dos);
        }

    }

    /**
     * @return deep copy of this attribute
     */
    @Override
    public Attribute copy( final ConstantPool constant_pool ) {
        return (Attribute) clone();
    }
}
