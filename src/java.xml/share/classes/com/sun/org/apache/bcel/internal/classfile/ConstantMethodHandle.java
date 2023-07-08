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
 * and represents a reference to a method handle.
 *
 * @see     Constant
 */
public final class ConstantMethodHandle extends Constant {

    private int referenceKind;
    private int referenceIndex;


    /**
     * Initialize from another object.
     */
    public ConstantMethodHandle(final ConstantMethodHandle c) {
        this(c.getReferenceKind(), c.getReferenceIndex());
    }


    /**
     * Initialize instance from file data.
     *
     * @param file Input stream
     * @throws IOException
     */
    ConstantMethodHandle(final DataInput file) throws IOException {
        this(file.readUnsignedByte(), file.readUnsignedShort());
    }


    public ConstantMethodHandle(final int reference_kind, final int reference_index) {
        super(Const.CONSTANT_MethodHandle);
        this.referenceKind = reference_kind;
        this.referenceIndex = reference_index;
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
        v.visitConstantMethodHandle(this);
    }


    /**
     * Dump method kind and index to file stream in binary format.
     *
     * @param file Output file stream
     * @throws IOException
     */
    @Override
    public void dump( final DataOutputStream file ) throws IOException {
        file.writeByte(super.getTag());
        file.writeByte(referenceKind);
        file.writeShort(referenceIndex);
    }


    public int getReferenceKind() {
        return referenceKind;
    }


    public void setReferenceKind(final int reference_kind) {
        this.referenceKind = reference_kind;
    }


    public int getReferenceIndex() {
        return referenceIndex;
    }


    public void setReferenceIndex(final int reference_index) {
        this.referenceIndex = reference_index;
    }


    /**
     * @return String representation
     */
    @Override
    public String toString() {
        return super.toString() + "(referenceKind = " + referenceKind +
                ", referenceIndex = " + referenceIndex + ")";
    }
}
