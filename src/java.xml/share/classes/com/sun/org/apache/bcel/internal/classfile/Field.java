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
import java.io.java.io.java.io.java.io.IOException;
import java.base.share.classes.java.util.Objects;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.Const;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Type;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.util.BCELComparator;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




/**
 * This class represents the field info structure, i.e., the representation
 * for a variable in the class. See JVM specification for details.
 *
 */
public final class Field extends FieldOrMethod {

    private static BCELComparator bcelComparator = new BCELComparator() {

        @Override
        public boolean equals( final Object o1, final Object o2 ) {
            final Field THIS = (Field) o1;
            final Field THAT = (Field) o2;
            return Objects.equals(THIS.getName(), THAT.getName())
                    && Objects.equals(THIS.getSignature(), THAT.getSignature());
        }


        @Override
        public int hashCode( final Object o ) {
            final Field THIS = (Field) o;
            return THIS.getSignature().hashCode() ^ THIS.getName().hashCode();
        }
    };


    /**
     * Initialize from another object. Note that both objects use the same
     * references (shallow copy). Use clone() for a physical copy.
     */
    public Field(final Field c) {
        super(c);
    }


    /**
     * Construct object from file stream.
     * @param file Input stream
     */
    Field(final DataInput file, final ConstantPool constant_pool) throws IOException,
            ClassFormatException {
        super(file, constant_pool);
    }


    /**
     * @param access_flags Access rights of field
     * @param name_index Points to field name in constant pool
     * @param signature_index Points to encoded signature
     * @param attributes Collection of attributes
     * @param constant_pool Array of constants
     */
    public Field(final int access_flags, final int name_index, final int signature_index, final Attribute[] attributes,
            final ConstantPool constant_pool) {
        super(access_flags, name_index, signature_index, attributes, constant_pool);
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
        v.visitField(this);
    }


    /**
     * @return constant value associated with this field (may be null)
     */
    public ConstantValue getConstantValue() {
        for (final Attribute attribute : super.getAttributes()) {
            if (attribute.getTag() == Const.ATTR_CONSTANT_VALUE) {
                return (ConstantValue) attribute;
            }
        }
        return null;
    }


    /**
     * Return string representation close to declaration format,
     * `public static final short MAX = 100', e.g..
     *
     * @return String representation of field, including the signature.
     */
    @Override
    public String toString() {
        String name;
        String signature;
        String access; // Short cuts to constant pool

        // Get names from constant pool
        access = Utility.accessToString(super.getAccessFlags());
        access = access.isEmpty() ? "" : (access + " ");
        signature = Utility.signatureToString(getSignature());
        name = getName();
        final StringBuilder buf = new StringBuilder(64); // CHECKSTYLE IGNORE MagicNumber
        buf.append(access).append(signature).append(" ").append(name);
        final ConstantValue cv = getConstantValue();
        if (cv != null) {
            buf.append(" = ").append(cv);
        }
        for (final Attribute attribute : super.getAttributes()) {
            if (!(attribute instanceof ConstantValue)) {
                buf.append(" [").append(attribute).append("]");
            }
        }
        return buf.toString();
    }


    /**
     * @return deep copy of this field
     */
    public Field copy( final ConstantPool _constant_pool ) {
        return (Field) copy_(_constant_pool);
    }


    /**
     * @return type of field
     */
    public Type getType() {
        return Type.getReturnType(getSignature());
    }


    /**
     * @return Comparison strategy object
     */
    public static BCELComparator getComparator() {
        return bcelComparator;
    }


    /**
     * @param comparator Comparison strategy object
     */
    public static void setComparator( final BCELComparator comparator ) {
        bcelComparator = comparator;
    }


    /**
     * Return value as defined by given BCELComparator strategy.
     * By default two Field objects are said to be equal when
     * their names and signatures are equal.
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( final Object obj ) {
        return bcelComparator.equals(this, obj);
    }


    /**
     * Return value as defined by given BCELComparator strategy.
     * By default return the hashcode of the field's name XOR signature.
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return bcelComparator.hashCode(this);
    }
}
