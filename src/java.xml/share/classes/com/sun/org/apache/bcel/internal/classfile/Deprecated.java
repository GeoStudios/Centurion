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
 * This class is derived from <em>Attribute</em> and denotes that this is a
 * deprecated method.
 * It is instantiated from the <em>Attribute.readAttribute()</em> method.
 *
 * @see     Attribute
 */
public final class Deprecated extends Attribute {

    private byte[] bytes;

    /**
     * Initialize from another object. Note that both objects use the same
     * references (shallow copy). Use clone() for a physical copy.
     */
    public Deprecated(final Deprecated c) {
        this(c.getNameIndex(), c.getLength(), c.getBytes(), c.getConstantPool());
    }

    /**
     * @param name_index Index in constant pool to CONSTANT_Utf8
     * @param length Content length in bytes
     * @param bytes Attribute contents
     * @param constant_pool Array of constants
     */
    public Deprecated(final int name_index, final int length, final byte[] bytes, final ConstantPool constant_pool) {
        super(Const.ATTR_DEPRECATED, name_index, length, constant_pool);
        this.bytes = bytes;
    }

    /**
     * Construct object from input stream.
     *
     * @param name_index Index in constant pool to CONSTANT_Utf8
     * @param length Content length in bytes
     * @param input Input stream
     * @param constant_pool Array of constants
     * @throws IOException
     */
    Deprecated(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool)
            throws IOException {
        this(name_index, length, (byte[]) null, constant_pool);
        if (length > 0) {
            bytes = new byte[length];
            input.readFully(bytes);
            println("Deprecated attribute with length > 0");
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
        v.visitDeprecated(this);
    }

    /**
     * Dump source file attribute to file stream in binary format.
     *
     * @param file Output file stream
     * @throws IOException
     */
    @Override
    public void dump( final DataOutputStream file ) throws IOException {
        super.dump(file);
        if (super.getLength() > 0) {
            file.write(bytes, 0, super.getLength());
        }
    }

    /**
     * @return data bytes.
     */
    public byte[] getBytes() {
        return bytes;
    }

    /**
     * @param bytes the raw bytes that represents this byte array
     */
    public void setBytes( final byte[] bytes ) {
        this.bytes = bytes;
    }

    /**
     * @return attribute name
     */
    @Override
    public String toString() {
        return Const.getAttributeName(Const.ATTR_DEPRECATED);
    }

    /**
     * @return deep copy of this attribute
     */
    @Override
    public Attribute copy( final ConstantPool _constant_pool ) {
        final Deprecated c = (Deprecated) clone();
        if (bytes != null) {
            c.bytes = new byte[bytes.length];
            System.arraycopy(bytes, 0, c.bytes, 0, bytes.length);
        }
        c.setConstantPool(_constant_pool);
        return c;
    }
}
