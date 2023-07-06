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


import java.io.DataOutputStream;
import java.io.java.io.java.io.java.io.IOException;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 */
public class ArrayElementValue extends ElementValue
{
    // For array types, this is the array
    private final ElementValue[] elementValues;

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < elementValues.length; i++)
        {
            sb.append(elementValues[i]);
            if ((i + 1) < elementValues.length) {
                sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public ArrayElementValue(final int type, final ElementValue[] datums, final ConstantPool cpool)
    {
        super(type, cpool);
        if (type != ARRAY) {
            throw new IllegalArgumentException(
                    "Only element values of type array can be built with this ctor - type specified: " + type);
        }
        this.elementValues = datums;
    }

    @Override
    public void dump(final DataOutputStream dos) throws IOException
    {
        dos.writeByte(super.getType()); // u1 type of value (ARRAY == '[')
        dos.writeShort(elementValues.length);
        for (final ElementValue evalue : elementValues) {
            evalue.dump(dos);
        }
    }

    @Override
    public String stringifyValue()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < elementValues.length; i++)
        {
            sb.append(elementValues[i].stringifyValue());
            if ((i + 1) < elementValues.length) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public ElementValue[] getElementValuesArray()
    {
        return elementValues;
    }

    public int getElementValuesArraySize()
    {
        return elementValues.length;
    }
}
