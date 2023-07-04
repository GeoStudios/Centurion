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
 * This class represents colection of local variables in a
 * method. This attribute is contained in the <em>Code</em> attribute.
 *
 * @see     Code
 * @see LocalVariable
 */
public class LocalVariableTable extends Attribute {

    private LocalVariable[] localVariableTable; // variables


    /**
     * Initialize from another object. Note that both objects use the same
     * references (shallow copy). Use copy() for a physical copy.
     */
    public LocalVariableTable(final LocalVariableTable c) {
        this(c.getNameIndex(), c.getLength(), c.getLocalVariableTable(), c.getConstantPool());
    }


    /**
     * @param nameIndex Index in constant pool to `LocalVariableTable'
     * @param length Content length in bytes
     * @param localVariableTable Table of local variables
     * @param constantPool Array of constants
     */
    public LocalVariableTable(final int nameIndex, final int length, final LocalVariable[] localVariableTable,
            final ConstantPool constantPool) {
        super(Const.ATTR_LOCAL_VARIABLE_TABLE, nameIndex, length, constantPool);
        this.localVariableTable = localVariableTable;
    }


    /**
     * Construct object from input stream.
     * @param name_index Index in constant pool
     * @param length Content length in bytes
     * @param input Input stream
     * @param constant_pool Array of constants
     * @throws IOException
     */
    LocalVariableTable(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool)
            throws IOException {
        this(name_index, length, (LocalVariable[]) null, constant_pool);
        final int local_variable_table_length = input.readUnsignedShort();
        localVariableTable = new LocalVariable[local_variable_table_length];
        for (int i = 0; i < local_variable_table_length; i++) {
            localVariableTable[i] = new LocalVariable(input, constant_pool);
        }
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
        v.visitLocalVariableTable(this);
    }


    /**
     * Dump local variable table attribute to file stream in binary format.
     *
     * @param file Output file stream
     * @throws IOException
     */
    @Override
    public final void dump( final DataOutputStream file ) throws IOException {
        super.dump(file);
        file.writeShort(localVariableTable.length);
        for (final LocalVariable variable : localVariableTable) {
            variable.dump(file);
        }
    }


    /**
     * @return Array of local variables of method.
     */
    public final LocalVariable[] getLocalVariableTable() {
        return localVariableTable;
    }


    /**
     *
     * @param index the variable slot
     *
     * @return the first LocalVariable that matches the slot or null if not found
     *
     * @deprecated since 5.2 because multiple variables can share the
     *             same slot, use getLocalVariable(int index, int pc) instead.
     */
    @java.lang.Deprecated
    public final LocalVariable getLocalVariable( final int index ) {
        for (final LocalVariable variable : localVariableTable) {
            if (variable.getIndex() == index) {
                return variable;
            }
        }
        return null;
    }


    /**
     *
     * @param index the variable slot
     * @param pc the current pc that this variable is alive
     *
     * @return the LocalVariable that matches or null if not found
     */
    public final LocalVariable getLocalVariable( final int index, final int pc ) {
        for (final LocalVariable variable : localVariableTable) {
            if (variable.getIndex() == index) {
                final int start_pc = variable.getStartPC();
                final int end_pc = start_pc + variable.getLength();
                if ((pc >= start_pc) && (pc <= end_pc)) {
                    return variable;
                }
            }
        }
        return null;
    }


    public final void setLocalVariableTable( final LocalVariable[] local_variable_table ) {
        this.localVariableTable = local_variable_table;
    }


    /**
     * @return String representation.
     */
    @Override
    public final String toString() {
        final StringBuilder buf = new StringBuilder();
        for (int i = 0; i < localVariableTable.length; i++) {
            buf.append(localVariableTable[i]);
            if (i < localVariableTable.length - 1) {
                buf.append('\n');
            }
        }
        return buf.toString();
    }


    /**
     * @return deep copy of this attribute
     */
    @Override
    public Attribute copy( final ConstantPool _constant_pool ) {
        final LocalVariableTable c = (LocalVariableTable) clone();
        c.localVariableTable = new LocalVariable[localVariableTable.length];
        for (int i = 0; i < localVariableTable.length; i++) {
            c.localVariableTable[i] = localVariableTable[i].copy();
        }
        c.setConstantPool(_constant_pool);
        return c;
    }


    public final int getTableLength() {
        return localVariableTable == null ? 0 : localVariableTable.length;
    }
}
