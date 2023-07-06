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

package java.xml.share.classes.com.sun.org.apache.bcel.internal.generic;


import java.io.DataOutputStream;
import java.io.java.io.java.io.java.io.IOException;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.ConstantUtf8;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.ElementValue;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.EnumElementValue;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




/**
 */
public class EnumElementValueGen extends ElementValueGen
{
    // For enum types, these two indices point to the type and value
    private final int typeIdx;

    private final int valueIdx;

    /**
     * This ctor assumes the constant pool already contains the right type and
     * value - as indicated by typeIdx and valueIdx. This ctor is used for
     * deserialization
     */
    protected EnumElementValueGen(final int typeIdx, final int valueIdx,
            final ConstantPoolGen cpool)
    {
        super(ElementValueGen.ENUM_CONSTANT, cpool);
        if (super.getElementValueType() != ENUM_CONSTANT) {
            throw new IllegalArgumentException(
                    "Only element values of type enum can be built with this ctor - type specified: "
                            + super.getElementValueType());
        }
        this.typeIdx = typeIdx;
        this.valueIdx = valueIdx;
    }

    /**
     * Return immutable variant of this EnumElementValue
     */
    @Override
    public ElementValue getElementValue()
    {
        System.err.println("Duplicating value: " + getEnumTypeString() + ":"
                + getEnumValueString());
        return new EnumElementValue(super.getElementValueType(), typeIdx, valueIdx,
                getConstantPool().getConstantPool());
    }

    public EnumElementValueGen(final ObjectType t, final String value, final ConstantPoolGen cpool)
    {
        super(ElementValueGen.ENUM_CONSTANT, cpool);
        typeIdx = cpool.addUtf8(t.getSignature());// was addClass(t);
        valueIdx = cpool.addUtf8(value);// was addString(value);
    }

    public EnumElementValueGen(final EnumElementValue value, final ConstantPoolGen cpool,
            final boolean copyPoolEntries)
    {
        super(ENUM_CONSTANT, cpool);
        if (copyPoolEntries)
        {
            typeIdx = cpool.addUtf8(value.getEnumTypeString());// was
                                                                // addClass(value.getEnumTypeString());
            valueIdx = cpool.addUtf8(value.getEnumValueString()); // was
                                                                    // addString(value.getEnumValueString());
        }
        else
        {
            typeIdx = value.getTypeIndex();
            valueIdx = value.getValueIndex();
        }
    }

    @Override
    public void dump(final DataOutputStream dos) throws IOException
    {
        dos.writeByte(super.getElementValueType()); // u1 type of value (ENUM_CONSTANT == 'e')
        dos.writeShort(typeIdx); // u2
        dos.writeShort(valueIdx); // u2
    }

    @Override
    public String stringifyValue()
    {
        final ConstantUtf8 cu8 = (ConstantUtf8) getConstantPool().getConstant(valueIdx);
        return cu8.getBytes();
        // ConstantString cu8 =
        // (ConstantString)getConstantPool().getConstant(valueIdx);
        // return
        // ((ConstantUtf8)getConstantPool().getConstant(cu8.getStringIndex())).getBytes();
    }

    // BCELBUG: Should we need to call utility.signatureToString() on the output
    // here?
    public String getEnumTypeString()
    {
        // Constant cc = getConstantPool().getConstant(typeIdx);
        // ConstantClass cu8 =
        // (ConstantClass)getConstantPool().getConstant(typeIdx);
        // return
        // ((ConstantUtf8)getConstantPool().getConstant(cu8.getNameIndex())).getBytes();
        return ((ConstantUtf8) getConstantPool().getConstant(typeIdx))
                .getBytes();
        // return Utility.signatureToString(cu8.getBytes());
    }

    public String getEnumValueString()
    {
        return ((ConstantUtf8) getConstantPool().getConstant(valueIdx)).getBytes();
        // ConstantString cu8 =
        // (ConstantString)getConstantPool().getConstant(valueIdx);
        // return
        // ((ConstantUtf8)getConstantPool().getConstant(cu8.getStringIndex())).getBytes();
    }

    public int getValueIndex()
    {
        return valueIdx;
    }

    public int getTypeIndex()
    {
        return typeIdx;
    }
}
