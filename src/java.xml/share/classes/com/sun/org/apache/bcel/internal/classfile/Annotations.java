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
 * base class for annotations
 *
 */
public abstract class Annotations extends Attribute {

    private AnnotationEntry[] annotationTable;
    private final boolean isRuntimeVisible;

    /**
     * @param annotation_type the subclass type of the annotation
     * @param name_index Index pointing to the name <em>Code</em>
     * @param length Content length in bytes
     * @param input Input stream
     * @param constant_pool Array of constants
     */
    Annotations(final byte annotation_type, final int name_index, final int length, final DataInput input,
            final ConstantPool constant_pool, final boolean isRuntimeVisible) throws IOException {
        this(annotation_type, name_index, length, (AnnotationEntry[]) null, constant_pool, isRuntimeVisible);
        final int annotation_table_length = input.readUnsignedShort();
        annotationTable = new AnnotationEntry[annotation_table_length];
        for (int i = 0; i < annotation_table_length; i++) {
            annotationTable[i] = AnnotationEntry.read(input, constant_pool, isRuntimeVisible);
        }
    }

    /**
     * @param annotationType the subclass type of the annotation
     * @param nameIndex Index pointing to the name <em>Code</em>
     * @param length Content length in bytes
     * @param annotationTable the actual annotations
     * @param constantPool Array of constants
     */
    public Annotations(final byte annotationType, final int nameIndex, final int length, final AnnotationEntry[] annotationTable,
            final ConstantPool constantPool, final boolean isRuntimeVisible) {
        super(annotationType, nameIndex, length, constantPool);
        this.annotationTable = annotationTable;
        this.isRuntimeVisible = isRuntimeVisible;
    }

    /**
     * Called by objects that are traversing the nodes of the tree implicitely defined by the contents of a Java class.
     * I.e., the hierarchy of methods, fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitAnnotation(this);
    }

    /**
     * @param annotationTable the entries to set in this annotation
     */
    public final void setAnnotationTable(final AnnotationEntry[] annotationTable) {
        this.annotationTable = annotationTable;
    }

    /**
     * returns the array of annotation entries in this annotation
     */
    public AnnotationEntry[] getAnnotationEntries() {
        return annotationTable;
    }

    /**
     * @return the number of annotation entries in this annotation
     */
    public final int getNumAnnotations() {
        if (annotationTable == null) {
            return 0;
        }
        return annotationTable.length;
    }

    public boolean isRuntimeVisible() {
        return isRuntimeVisible;
    }

    protected void writeAnnotations(final DataOutputStream dos) throws IOException {
        if (annotationTable == null) {
            return;
        }
        dos.writeShort(annotationTable.length);
        for (final AnnotationEntry element : annotationTable) {
            element.dump(dos);
        }
    }
}
