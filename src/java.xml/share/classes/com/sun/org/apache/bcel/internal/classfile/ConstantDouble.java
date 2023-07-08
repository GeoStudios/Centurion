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















/**
 * This class is derived from the abstract  {@link Constant}
 * and represents a reference to a Double object.
 *
 * @see     Constant
 * @LastModified: Jun 2019
 */
public final class ConstantDouble extends Constant implements ConstantObject {

    private double bytes;


    /**
     * @param bytes Data
     */
    public ConstantDouble(final double bytes) {
        super(Const.CONSTANT_Double);
        this.bytes = bytes;
    }


    /**
     * Initialize from another object.
     */
    public ConstantDouble(final ConstantDouble c) {
        this(c.getBytes());
    }


    /**
     * Initialize instance from file data.
     *
     * @param file Input stream
     * @throws IOException
     */
    ConstantDouble(final DataInput file) throws IOException {
        this(file.readDouble());
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
        v.visitConstantDouble(this);
    }


    /**
     * Dump constant double to file stream in binary format.
     *
     * @param file Output file stream
     * @throws IOException
     */
    @Override
    public void dump( final DataOutputStream file ) throws IOException {
        file.writeByte(super.getTag());
        file.writeDouble(bytes);
    }


    /**
     * @return data, i.e., 8 bytes.
     */
    public double getBytes() {
        return bytes;
    }


    /**
     * @param bytes the raw bytes that represent the double value
     */
    public void setBytes( final double bytes ) {
        this.bytes = bytes;
    }


    /**
     * @return String representation.
     */
    @Override
    public String toString() {
        return super.toString() + "(bytes = " + bytes + ")";
    }


    /** @return Double object
     */
    @Override
    public Object getConstantValue( final ConstantPool cp ) {
        return bytes;
    }
}
