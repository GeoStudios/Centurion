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
 * This class represents a MethodParameters attribute.
 *
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.7.24">
 * The class File Format : The MethodParameters Attribute</a>
 */
public class MethodParameters extends Attribute {

    private MethodParameter[] parameters = new MethodParameter[0];

    MethodParameters(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        super(Const.ATTR_METHOD_PARAMETERS, name_index, length, constant_pool);

        final int parameters_count = input.readUnsignedByte();
        parameters = new MethodParameter[parameters_count];
        for (int i = 0; i < parameters_count; i++) {
            parameters[i] = new MethodParameter(input);
        }
    }

    public MethodParameter[] getParameters() {
        return parameters;
    }

    public void setParameters(final MethodParameter[] parameters) {
        this.parameters = parameters;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitMethodParameters(this);
    }

    @Override
    public Attribute copy(final ConstantPool _constant_pool) {
        final MethodParameters c = (MethodParameters) clone();
        c.parameters = new MethodParameter[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            c.parameters[i] = parameters[i].copy();
        }
        c.setConstantPool(_constant_pool);
        return c;
    }

    /**
     * Dump method parameters attribute to file stream in binary format.
     *
     * @param file Output file stream
     * @throws IOException
     */
    @Override
       public void dump(final DataOutputStream file) throws IOException {
           super.dump(file);
           file.writeByte(parameters.length);
        for (final MethodParameter parameter : parameters) {
            parameter.dump(file);
        }
    }
}
