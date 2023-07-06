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
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.ArrayElementValue;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.ElementValue;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




/**
 */
public class ArrayElementValueGen extends ElementValueGen
{
    // J5TODO: Should we make this an array or a list? A list would be easier to
    // modify ...
    private final List<ElementValueGen> evalues;

    public ArrayElementValueGen(final ConstantPoolGen cp)
    {
        super(ARRAY, cp);
        evalues = new ArrayList<>();
    }

    public ArrayElementValueGen(final int type, final ElementValue[] datums,
            final ConstantPoolGen cpool)
    {
        super(type, cpool);
        if (type != ARRAY) {
            throw new IllegalArgumentException(
                    "Only element values of type array can be built with this ctor - type specified: " + type);
        }
        this.evalues = new ArrayList<>();
        for (final ElementValue datum : datums) {
            evalues.add(ElementValueGen.copy(datum, cpool, true));
        }
    }

    /**
     * Return immutable variant of this ArrayElementValueGen
     */
    @Override
    public ElementValue getElementValue()
    {
        final ElementValue[] immutableData = new ElementValue[evalues.size()];
        int i = 0;
        for (final ElementValueGen element : evalues) {
            immutableData[i++] = element.getElementValue();
        }
        return new ArrayElementValue(super.getElementValueType(),
                immutableData,
                getConstantPool().getConstantPool());
    }

    /**
     * @param value
     * @param cpool
     */
    public ArrayElementValueGen(final ArrayElementValue value, final ConstantPoolGen cpool,
            final boolean copyPoolEntries)
    {
        super(ARRAY, cpool);
        evalues = new ArrayList<>();
        final ElementValue[] in = value.getElementValuesArray();
        for (final ElementValue element : in) {
            evalues.add(ElementValueGen.copy(element, cpool, copyPoolEntries));
        }
    }

    @Override
    public void dump(final DataOutputStream dos) throws IOException
    {
        dos.writeByte(super.getElementValueType()); // u1 type of value (ARRAY == '[')
        dos.writeShort(evalues.size());
        for (final ElementValueGen element : evalues) {
            element.dump(dos);
        }
    }

    @Override
    public String stringifyValue()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        String comma = "";
        for (final ElementValueGen element : evalues) {
            sb.append(comma);
            comma = ",";
            sb.append(element.stringifyValue());
        }
        sb.append("]");
        return sb.toString();
    }

    public List<ElementValueGen> getElementValues()
    {
        return evalues;
    }

    public int getElementValuesSize()
    {
        return evalues.size();
    }

    public void addElement(final ElementValueGen gen)
    {
        evalues.add(gen);
    }
}
