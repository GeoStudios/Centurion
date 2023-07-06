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
 * This class is derived from the abstract {@link Constant}
 * and represents a reference to a module.
 *
 * <p>Note: Early access Java 9 support- currently subject to change</p>
 *
 * @see     Constant
 */
public final class ConstantModule extends Constant implements ConstantObject {

    private int nameIndex;

    /**
     * Initialize from another object.
     */
    public ConstantModule(final ConstantModule c) {
        this(c.getNameIndex());
    }

    /**
     * Initialize instance from file data.
     *
     * @param file Input stream
     * @throws IOException
     */
    ConstantModule(final DataInput file) throws IOException {
        this(file.readUnsignedShort());
    }

    /**
     * @param nameIndex Name index in constant pool.  Should refer to a
     * ConstantUtf8.
     */
    public ConstantModule(final int nameIndex) {
        super(Const.CONSTANT_Module);
        this.nameIndex = nameIndex;
    }

    /**
     * Called by objects that are traversing the nodes of the tree implicitly
     * defined by the contents of a Java class. I.e., the hierarchy of methods,
     * fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    @Override
    public void accept( final Visitor v ) {
        v.visitConstantModule(this);
    }

    /**
     * Dump constant module to file stream in binary format.
     *
     * @param file Output file stream
     * @throws IOException
     */
    @Override
    public void dump( final DataOutputStream file ) throws IOException {
        file.writeByte(super.getTag());
        file.writeShort(nameIndex);
    }

    /**
     * @return Name index in constant pool of module name.
     */
    public int getNameIndex() {
        return nameIndex;
    }

    /**
     * @param nameIndex the name index in the constant pool of this Constant Module
     */
    public void setNameIndex( final int nameIndex ) {
        this.nameIndex = nameIndex;
    }

    /** @return String object
     */
    @Override
    public Object getConstantValue( final ConstantPool cp ) {
        final Constant c = cp.getConstant(nameIndex, Const.CONSTANT_Utf8);
        return ((ConstantUtf8) c).getBytes();
    }

    /** @return dereferenced string
     */
    public String getBytes( final ConstantPool cp ) {
        return (String) getConstantValue(cp);
    }

    /**
     * @return String representation.
     */
    @Override
    public String toString() {
        return super.toString() + "(nameIndex = " + nameIndex + ")";
    }
}