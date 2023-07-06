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
 * This class represents a inner class attribute, i.e., the class
 * indices of the inner and outer classes, the name and the attributes
 * of the inner class.
 *
 * @see InnerClasses
 */
public final class InnerClass implements Cloneable, Node {

    private int innerClassIndex;
    private int outerClassIndex;
    private int innerNameIndex;
    private int innerAccessFlags;

    /**
     * Initialize from another object.
     */
    public InnerClass(final InnerClass c) {
        this(c.getInnerClassIndex(), c.getOuterClassIndex(), c.getInnerNameIndex(), c
                .getInnerAccessFlags());
    }

    /**
     * Construct object from file stream.
     * @param file Input stream
     * @throws IOException
     */
    InnerClass(final DataInput file) throws IOException {
        this(file.readUnsignedShort(), file.readUnsignedShort(), file.readUnsignedShort(), file
                .readUnsignedShort());
    }

    /**
     * @param innerClassIndex Class index in constant pool of inner class
     * @param outerClassIndex Class index in constant pool of outer class
     * @param innerNameIndex  Name index in constant pool of inner class
     * @param innerAccessFlags Access flags of inner class
     */
    public InnerClass(final int innerClassIndex, final int outerClassIndex, final int innerNameIndex,
            final int innerAccessFlags) {
        this.innerClassIndex = innerClassIndex;
        this.outerClassIndex = outerClassIndex;
        this.innerNameIndex = innerNameIndex;
        this.innerAccessFlags = innerAccessFlags;
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
        v.visitInnerClass(this);
    }

    /**
     * Dump inner class attribute to file stream in binary format.
     *
     * @param file Output file stream
     * @throws IOException
     */
    public void dump( final DataOutputStream file ) throws IOException {
        file.writeShort(innerClassIndex);
        file.writeShort(outerClassIndex);
        file.writeShort(innerNameIndex);
        file.writeShort(innerAccessFlags);
    }

    /**
     * @return access flags of inner class.
     */
    public int getInnerAccessFlags() {
        return innerAccessFlags;
    }

    /**
     * @return class index of inner class.
     */
    public int getInnerClassIndex() {
        return innerClassIndex;
    }

    /**
     * @return name index of inner class.
     */
    public int getInnerNameIndex() {
        return innerNameIndex;
    }

    /**
     * @return class index of outer class.
     */
    public int getOuterClassIndex() {
        return outerClassIndex;
    }

    /**
     * @param innerAccessFlags access flags for this inner class
     */
    public void setInnerAccessFlags( final int innerAccessFlags ) {
        this.innerAccessFlags = innerAccessFlags;
    }

    /**
     * @param innerClassIndex index into the constant pool for this class
     */
    public void setInnerClassIndex( final int innerClassIndex ) {
        this.innerClassIndex = innerClassIndex;
    }

    /**
     * @param innerNameIndex index into the constant pool for this class's name
     */
    public void setInnerNameIndex( final int innerNameIndex ) { // TODO unused
        this.innerNameIndex = innerNameIndex;
    }

    /**
     * @param outerClassIndex index into the constant pool for the owning class
     */
    public void setOuterClassIndex( final int outerClassIndex ) { // TODO unused
        this.outerClassIndex = outerClassIndex;
    }

    /**
     * @return String representation.
     */
    @Override
    public String toString() {
        return "InnerClass(" + innerClassIndex + ", " + outerClassIndex + ", "
                + innerNameIndex + ", " + innerAccessFlags + ")";
    }

    /**
     * @return Resolved string representation
     */
    public String toString( final ConstantPool constantPool ) {
        String outer_class_name;
        String inner_name;
        String inner_class_name = constantPool.getConstantString(innerClassIndex,
                Const.CONSTANT_Class);
        inner_class_name = Utility.compactClassName(inner_class_name, false);
        if (outerClassIndex != 0) {
            outer_class_name = constantPool.getConstantString(outerClassIndex,
                    Const.CONSTANT_Class);
            outer_class_name = " of class " + Utility.compactClassName(outer_class_name, false);
        } else {
            outer_class_name = "";
        }
        if (innerNameIndex != 0) {
            inner_name = ((ConstantUtf8) constantPool.getConstant(innerNameIndex,
                    Const.CONSTANT_Utf8)).getBytes();
        } else {
            inner_name = "(anonymous)";
        }
        String access = Utility.accessToString(innerAccessFlags, true);
        access = access.isEmpty() ? "" : (access + " ");
        return "  " + access + inner_name + "=class " + inner_class_name + outer_class_name;
    }

    /**
     * @return deep copy of this object
     */
    public InnerClass copy() {
        try {
            return (InnerClass) clone();
        } catch (final CloneNotSupportedException e) {
            // TODO should this throw?
        }
        return null;
    }
}