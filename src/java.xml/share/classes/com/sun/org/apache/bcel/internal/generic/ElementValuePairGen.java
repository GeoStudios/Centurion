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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.ElementValuePair;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




/**
 */
public class ElementValuePairGen
{
    private final int nameIdx;

    private final ElementValueGen value;

    private final ConstantPoolGen constantPoolGen;

    public ElementValuePairGen(final ElementValuePair nvp, final ConstantPoolGen cpool,
            final boolean copyPoolEntries)
    {
        this.constantPoolGen = cpool;
        // J5ASSERT:
        // Could assert nvp.getNameString() points to the same thing as
        // constantPoolGen.getConstant(nvp.getNameIndex())
        // if
        // (!nvp.getNameString().equals(((ConstantUtf8)constantPoolGen.getConstant(nvp.getNameIndex())).getBytes()))
        // {
        // throw new IllegalArgumentException("envp buggered");
        // }
        if (copyPoolEntries)
        {
            nameIdx = cpool.addUtf8(nvp.getNameString());
        }
        else
        {
            nameIdx = nvp.getNameIndex();
        }
        value = ElementValueGen.copy(nvp.getValue(), cpool, copyPoolEntries);
    }

    /**
     * Retrieve an immutable version of this ElementNameValuePairGen
     */
    public ElementValuePair getElementNameValuePair()
    {
        final ElementValue immutableValue = value.getElementValue();
        return new ElementValuePair(nameIdx, immutableValue, constantPoolGen
                .getConstantPool());
    }

    protected ElementValuePairGen(final int idx, final ElementValueGen value,
            final ConstantPoolGen cpool)
    {
        this.nameIdx = idx;
        this.value = value;
        this.constantPoolGen = cpool;
    }

    public ElementValuePairGen(final String name, final ElementValueGen value,
            final ConstantPoolGen cpool)
    {
        this.nameIdx = cpool.addUtf8(name);
        this.value = value;
        this.constantPoolGen = cpool;
    }

    protected void dump(final DataOutputStream dos) throws IOException
    {
        dos.writeShort(nameIdx); // u2 name of the element
        value.dump(dos);
    }

    public int getNameIndex()
    {
        return nameIdx;
    }

    public final String getNameString()
    {
        // ConstantString cu8 = (ConstantString)constantPoolGen.getConstant(nameIdx);
        return ((ConstantUtf8) constantPoolGen.getConstant(nameIdx)).getBytes();
    }

    public final ElementValueGen getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return "ElementValuePair:[" + getNameString() + "="
                + value.stringifyValue() + "]";
    }
}
