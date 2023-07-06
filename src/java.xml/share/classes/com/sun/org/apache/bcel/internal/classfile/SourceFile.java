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
 * This class is derived from <em>Attribute</em> and represents a reference
 * to the source file of this class.  At most one SourceFile attribute
 * should appear per classfile.  The intention of this class is that it is
 * instantiated from the <em>Attribute.readAttribute()</em> method.
 *
 * @see     Attribute
 */
public final class SourceFile extends Attribute {

    private int sourceFileIndex;


    /**
     * Initialize from another object. Note that both objects use the same
     * references (shallow copy). Use clone() for a physical copy.
     */
    public SourceFile(final SourceFile c) {
        this(c.getNameIndex(), c.getLength(), c.getSourceFileIndex(), c.getConstantPool());
    }


    /**
     * Construct object from input stream.
     * @param name_index Index in constant pool to CONSTANT_Utf8
     * @param length Content length in bytes
     * @param input Input stream
     * @param constant_pool Array of constants
     * @throws IOException
     */
    SourceFile(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool)
            throws IOException {
        this(name_index, length, input.readUnsignedShort(), constant_pool);
    }


    /**
     * @param name_index Index in constant pool to CONSTANT_Utf8, which
     * should represent the string "SourceFile".
     * @param length Content length in bytes, the value should be 2.
     * @param constantPool The constant pool that this attribute is
     * associated with.
     * @param sourceFileIndex Index in constant pool to CONSTANT_Utf8.  This
     * string will be interpreted as the name of the file from which this
     * class was compiled.  It will not be interpreted as indicating the name
     * of the directory contqining the file or an absolute path; this
     * information has to be supplied the consumer of this attribute - in
     * many cases, the JVM.
     */
    public SourceFile(final int name_index, final int length, final int sourceFileIndex, final ConstantPool constantPool) {
        super(Const.ATTR_SOURCE_FILE, name_index, length, constantPool);
        this.sourceFileIndex = sourceFileIndex;
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
        v.visitSourceFile(this);
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
        file.writeShort(sourceFileIndex);
    }


    /**
     * @return Index in constant pool of source file name.
     */
    public int getSourceFileIndex() {
        return sourceFileIndex;
    }


    /**
     * @param sourceFileIndex
     */
    public void setSourceFileIndex( final int sourceFileIndex ) {
        this.sourceFileIndex = sourceFileIndex;
    }


    /**
     * @return Source file name.
     */
    public String getSourceFileName() {
        final ConstantUtf8 c = (ConstantUtf8) super.getConstantPool().getConstant(sourceFileIndex,
                Const.CONSTANT_Utf8);
        return c.getBytes();
    }


    /**
     * @return String representation
     */
    @Override
    public String toString() {
        return "SourceFile: " + getSourceFileName();
    }


    /**
     * @return deep copy of this attribute
     */
    @Override
    public Attribute copy( final ConstantPool _constant_pool ) {
        return (Attribute) clone();
    }
}
