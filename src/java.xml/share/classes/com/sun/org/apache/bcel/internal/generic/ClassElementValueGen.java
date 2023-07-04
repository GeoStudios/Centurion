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
package com.sun.org.apache.bcel.internal.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.classfile.ClassElementValue;
import com.sun.org.apache.bcel.internal.classfile.ConstantUtf8;
import com.sun.org.apache.bcel.internal.classfile.ElementValue;

/**
 */
public class ClassElementValueGen extends ElementValueGen
{
    // For primitive types and string type, this points to the value entry in
    // the cpool
    // For 'class' this points to the class entry in the cpool
    private final int idx;

    protected ClassElementValueGen(final int typeIdx, final ConstantPoolGen cpool)
    {
        super(ElementValueGen.CLASS, cpool);
        this.idx = typeIdx;
    }

    public ClassElementValueGen(final ObjectType t, final ConstantPoolGen cpool)
    {
        super(ElementValueGen.CLASS, cpool);
        // this.idx = cpool.addClass(t);
        idx = cpool.addUtf8(t.getSignature());
    }

    /**
     * Return immutable variant of this ClassElementValueGen
     */
    @Override
    public ElementValue getElementValue()
    {
        return new ClassElementValue(super.getElementValueType(),
                idx,
                getConstantPool().getConstantPool());
    }

    public ClassElementValueGen(final ClassElementValue value, final ConstantPoolGen cpool,
            final boolean copyPoolEntries)
    {
        super(CLASS, cpool);
        if (copyPoolEntries)
        {
            // idx = cpool.addClass(value.getClassString());
            idx = cpool.addUtf8(value.getClassString());
        }
        else
        {
            idx = value.getIndex();
        }
    }

    public int getIndex()
    {
        return idx;
    }

    public String getClassString()
    {
        final ConstantUtf8 cu8 = (ConstantUtf8) getConstantPool().getConstant(idx);
        return cu8.getBytes();
        // ConstantClass c = (ConstantClass)getConstantPool().getConstant(idx);
        // ConstantUtf8 utf8 =
        // (ConstantUtf8)getConstantPool().getConstant(c.getNameIndex());
        // return utf8.getBytes();
    }

    @Override
    public String stringifyValue()
    {
        return getClassString();
    }

    @Override
    public void dump(final DataOutputStream dos) throws IOException
    {
        dos.writeByte(super.getElementValueType()); // u1 kind of value
        dos.writeShort(idx);
    }
}
