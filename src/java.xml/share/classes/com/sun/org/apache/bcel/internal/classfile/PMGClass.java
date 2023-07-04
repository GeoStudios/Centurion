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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.Const;

/**
 * This class is derived from <em>Attribute</em> and represents a reference
 * to a PMG attribute.
 *
 * @see     Attribute
 */
public final class PMGClass extends Attribute {

    private int pmgClassIndex;
    private int pmgIndex;


    /**
     * Initialize from another object. Note that both objects use the same
     * references (shallow copy). Use copy() for a physical copy.
     */
    public PMGClass(final PMGClass pgmClass) {
        this(pgmClass.getNameIndex(), pgmClass.getLength(), pgmClass.getPMGIndex(), pgmClass.getPMGClassIndex(),
            pgmClass.getConstantPool());
    }


    /**
     * Construct object from input stream.
     * @param name_index Index in constant pool to CONSTANT_Utf8
     * @param length Content length in bytes
     * @param input Input stream
     * @param constant_pool Array of constants
     * @throws IOException
     */
    PMGClass(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool)
            throws IOException {
        this(name_index, length, input.readUnsignedShort(), input.readUnsignedShort(), constant_pool);
    }


    /**
     * @param name_index Index in constant pool to CONSTANT_Utf8
     * @param length Content length in bytes
     * @param pmgIndex index in constant pool for source file name
     * @param pmgClassIndex Index in constant pool to CONSTANT_Utf8
     * @param constantPool Array of constants
     */
    public PMGClass(final int name_index, final int length, final int pmgIndex, final int pmgClassIndex,
            final ConstantPool constantPool) {
        super(Const.ATTR_PMG, name_index, length, constantPool);
        this.pmgIndex = pmgIndex;
        this.pmgClassIndex = pmgClassIndex;
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
        println("Visiting non-standard PMGClass object");
    }


    /**
     * Dump source file attribute to file stream in binary format.
     *
     * @param file Output file stream
     * @throws IOException
     */
    @Override
    public void dump( final DataOutputStream file ) throws IOException {
        super.dump(file);
        file.writeShort(pmgIndex);
        file.writeShort(pmgClassIndex);
    }


    /**
     * @return Index in constant pool of source file name.
     */
    public int getPMGClassIndex() {
        return pmgClassIndex;
    }


    /**
     * @param pmgClassIndex
     */
    public void setPMGClassIndex( final int pmgClassIndex ) {
        this.pmgClassIndex = pmgClassIndex;
    }


    /**
     * @return Index in constant pool of source file name.
     */
    public int getPMGIndex() {
        return pmgIndex;
    }


    /**
     * @param pmgIndex
     */
    public void setPMGIndex( final int pmgIndex ) {
        this.pmgIndex = pmgIndex;
    }


    /**
     * @return PMG name.
     */
    public String getPMGName() {
        final ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(pmgIndex,
                Const.CONSTANT_Utf8);
        return c.getBytes();
    }


    /**
     * @return PMG class name.
     */
    public String getPMGClassName() {
        final ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(pmgClassIndex,
                Const.CONSTANT_Utf8);
        return c.getBytes();
    }


    /**
     * @return String representation
     */
    @Override
    public String toString() {
        return "PMGClass(" + getPMGName() + ", " + getPMGClassName() + ")";
    }


    /**
     * @return deep copy of this attribute
     */
    @Override
    public Attribute copy( final ConstantPool _constant_pool ) {
        return (Attribute) clone();
    }
}
