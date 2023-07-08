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




/**
 * represents an annotation that is represented in the class file but is not
 * provided to the JVM.
 *
 */
public class RuntimeInvisibleAnnotations extends Annotations
{
    /**
     * @param name_index
     *            Index pointing to the name <em>Code</em>
     * @param length
     *            Content length in bytes
     * @param input
     *            Input stream
     * @param constant_pool
     *            Array of constants
     */
    public RuntimeInvisibleAnnotations(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool)
            throws IOException
    {
        super(Const.ATTR_RUNTIME_INVISIBLE_ANNOTATIONS, name_index, length, input, constant_pool, false);
    }

    /**
     * @return deep copy of this attribute
     */
    @Override
    public Attribute copy(final ConstantPool constant_pool)
    {
        return (Attribute) clone();
    }

    @Override
    public final void dump(final DataOutputStream dos) throws IOException
    {
        super.dump(dos);
        writeAnnotations(dos);
    }
}
