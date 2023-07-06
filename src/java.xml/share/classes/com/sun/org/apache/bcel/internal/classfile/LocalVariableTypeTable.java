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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.Const;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




// The new table is used when generic types are about...

//LocalVariableTable_attribute {
//       u2 attribute_name_index;
//       u4 attribute_length;
//       u2 local_variable_table_length;
//       {  u2 start_pc;
//          u2 length;
//          u2 name_index;
//          u2 descriptor_index;
//          u2 index;
//       } local_variable_table[local_variable_table_length];
//     }

//LocalVariableTypeTable_attribute {
//    u2 attribute_name_index;
//    u4 attribute_length;
//    u2 local_variable_type_table_length;
//    {
//      u2 start_pc;
//      u2 length;
//      u2 name_index;
//      u2 signature_index;
//      u2 index;
//    } localVariableTypeTable[local_variable_type_table_length];
//  }
// J5TODO: Needs some testing !

/**
 */
public class LocalVariableTypeTable extends Attribute {

    private LocalVariable[] localVariableTypeTable;        // variables

    public LocalVariableTypeTable(final LocalVariableTypeTable c) {
        this(c.getNameIndex(), c.getLength(), c.getLocalVariableTypeTable(), c.getConstantPool());
    }

    public LocalVariableTypeTable(final int name_index, final int length, final LocalVariable[] local_variable_table, final ConstantPool constant_pool) {
        super(Const.ATTR_LOCAL_VARIABLE_TYPE_TABLE, name_index, length, constant_pool);
        this.localVariableTypeTable = local_variable_table;
    }

    LocalVariableTypeTable(final int nameIdx, final int len, final DataInput input, final ConstantPool cpool) throws IOException {
        this(nameIdx, len, (LocalVariable[]) null, cpool);

        final int local_variable_type_table_length = input.readUnsignedShort();
        localVariableTypeTable = new LocalVariable[local_variable_type_table_length];

        for (int i = 0; i < local_variable_type_table_length; i++) {
            localVariableTypeTable[i] = new LocalVariable(input, cpool);
        }
    }

    @Override
    public void accept(final Visitor v) {
        v.visitLocalVariableTypeTable(this);
    }

    @Override
    public final void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(localVariableTypeTable.length);
        for (final LocalVariable variable : localVariableTypeTable) {
            variable.dump(file);
        }
    }

    public final LocalVariable[] getLocalVariableTypeTable() {
        return localVariableTypeTable;
    }

    public final LocalVariable getLocalVariable(final int index) {
        for (final LocalVariable variable : localVariableTypeTable) {
            if (variable.getIndex() == index) {
                return variable;
            }
        }

        return null;
    }

    public final void setLocalVariableTable(final LocalVariable[] local_variable_table) {
        this.localVariableTypeTable = local_variable_table;
    }

    /**
     * @return String representation.
     */
    @Override
    public final String toString() {
        final StringBuilder buf = new StringBuilder();

        for (int i = 0; i < localVariableTypeTable.length; i++) {
            buf.append(localVariableTypeTable[i].toStringShared(true));

            if (i < localVariableTypeTable.length - 1) {
                buf.append('\n');
            }
        }

        return buf.toString();
    }

    /**
     * @return deep copy of this attribute
     */
    @Override
    public Attribute copy(final ConstantPool constant_pool) {
        final LocalVariableTypeTable c = (LocalVariableTypeTable) clone();

        c.localVariableTypeTable = new LocalVariable[localVariableTypeTable.length];
        for (int i = 0; i < localVariableTypeTable.length; i++) {
            c.localVariableTypeTable[i] = localVariableTypeTable[i].copy();
        }

        c.setConstantPool(constant_pool);
        return c;
    }

    public final int getTableLength() {
        return localVariableTypeTable == null ? 0 : localVariableTypeTable.length;
    }
}
