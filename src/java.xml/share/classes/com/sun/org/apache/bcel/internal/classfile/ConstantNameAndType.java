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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.Const;

/**
 * This class is derived from the abstract {@link Constant}
 * and represents a reference to the name and signature
 * of a field or method.
 *
 * @see     Constant
 */
public final class ConstantNameAndType extends Constant {

    private int nameIndex; // Name of field/method
    private int signatureIndex; // and its signature.


    /**
     * Initialize from another object.
     */
    public ConstantNameAndType(final ConstantNameAndType c) {
        this(c.getNameIndex(), c.getSignatureIndex());
    }


    /**
     * Initialize instance from file data.
     *
     * @param file Input stream
     * @throws IOException
     */
    ConstantNameAndType(final DataInput file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort());
    }


    /**
     * @param nameIndex Name of field/method
     * @param signatureIndex and its signature
     */
    public ConstantNameAndType(final int nameIndex, final int signatureIndex) {
        super(Const.CONSTANT_NameAndType);
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
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
        v.visitConstantNameAndType(this);
    }


    /**
     * Dump name and signature index to file stream in binary format.
     *
     * @param file Output file stream
     * @throws IOException
     */
    @Override
    public void dump( final DataOutputStream file ) throws IOException {
        file.writeByte(super.getTag());
        file.writeShort(nameIndex);
        file.writeShort(signatureIndex);
    }


    /**
     * @return Name index in constant pool of field/method name.
     */
    public int getNameIndex() {
        return nameIndex;
    }


    /** @return name
     */
    public String getName( final ConstantPool cp ) {
        return cp.constantToString(getNameIndex(), Const.CONSTANT_Utf8);
    }


    /**
     * @return Index in constant pool of field/method signature.
     */
    public int getSignatureIndex() {
        return signatureIndex;
    }


    /** @return signature
     */
    public String getSignature( final ConstantPool cp ) {
        return cp.constantToString(getSignatureIndex(), Const.CONSTANT_Utf8);
    }


    /**
     * @param nameIndex the name index of this constant
     */
    public void setNameIndex( final int nameIndex ) {
        this.nameIndex = nameIndex;
    }


    /**
     * @param signatureIndex the signature index in the constant pool of this type
     */
    public void setSignatureIndex( final int signatureIndex ) {
        this.signatureIndex = signatureIndex;
    }


    /**
     * @return String representation
     */
    @Override
    public String toString() {
        return super.toString() + "(nameIndex = " + nameIndex + ", signatureIndex = "
                + signatureIndex + ")";
    }
}
