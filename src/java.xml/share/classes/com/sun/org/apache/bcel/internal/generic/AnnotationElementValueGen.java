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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.AnnotationElementValue;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.ElementValue;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




/**
 */
public class AnnotationElementValueGen extends ElementValueGen
{
    // For annotation element values, this is the annotation
    private final AnnotationEntryGen a;

    public AnnotationElementValueGen(final AnnotationEntryGen a, final ConstantPoolGen cpool)
    {
        super(ANNOTATION, cpool);
        this.a = a;
    }

    public AnnotationElementValueGen(final int type, final AnnotationEntryGen annotation,
            final ConstantPoolGen cpool)
    {
        super(type, cpool);
        if (type != ANNOTATION) {
            throw new IllegalArgumentException(
                    "Only element values of type annotation can be built with this ctor - type specified: " + type);
        }
        this.a = annotation;
    }

    public AnnotationElementValueGen(final AnnotationElementValue value,
            final ConstantPoolGen cpool, final boolean copyPoolEntries)
    {
        super(ANNOTATION, cpool);
        a = new AnnotationEntryGen(value.getAnnotationEntry(), cpool, copyPoolEntries);
    }

    @Override
    public void dump(final DataOutputStream dos) throws IOException
    {
        dos.writeByte(super.getElementValueType()); // u1 type of value (ANNOTATION == '@')
        a.dump(dos);
    }

    @Override
    public String stringifyValue()
    {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Return immutable variant of this AnnotationElementValueGen
     */
    @Override
    public ElementValue getElementValue()
    {
        return new AnnotationElementValue(super.getElementValueType(),
                a.getAnnotation(),
                getConstantPool().getConstantPool());
    }

    public AnnotationEntryGen getAnnotation()
    {
        return a;
    }
}
