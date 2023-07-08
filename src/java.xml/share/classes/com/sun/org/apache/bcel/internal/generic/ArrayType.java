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

package java.xml.share.classes.com.sun.org.apache.bcel.internal.generic;

import java.xml.share.classes.com.sun.org.apache.bcel.internal.Const;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Denotes array type, such as int[][]
 *
 */
public final class ArrayType extends ReferenceType {

    private final int dimensions;
    private final Type basicType;

    /**
     * Convenience constructor for array type, e.g. int[]
     *
     * @param type array type, e.g. T_INT
     */
    public ArrayType(final byte type, final int dimensions) {
        this(BasicType.getType(type), dimensions);
    }

    /**
     * Convenience constructor for reference array type, e.g. Object[]
     *
     * @param class_name complete name of class (java.lang.String, e.g.)
     */
    public ArrayType(final String class_name, final int dimensions) {
        this(ObjectType.getInstance(class_name), dimensions);
    }

    /**
     * Constructor for array of given type
     *
     * @param type type of array (may be an array itself)
     */
    public ArrayType(final Type type, final int dimensions) {
        super(Const.T_ARRAY, "<dummy>");
        if ((dimensions < 1) || (dimensions > Const.MAX_BYTE)) {
            throw new ClassGenException("Invalid number of dimensions: " + dimensions);
        }
        switch (type.getType()) {
            case Const.T_ARRAY:
                final ArrayType array = (ArrayType) type;
                this.dimensions = dimensions + array.dimensions;
                basicType = array.basicType;
                break;
            case Const.T_VOID:
                throw new ClassGenException("Invalid type: void[]");
            default: // Basic type or reference
                this.dimensions = dimensions;
                basicType = type;
                break;
        }
        final StringBuilder buf = new StringBuilder();
        for (int i = 0; i < this.dimensions; i++) {
            buf.append('[');
        }
        buf.append(basicType.getSignature());
        super.setSignature(buf.toString());
    }

    /**
     * @return basic type of array, i.e., for int[][][] the basic type is int
     */
    public Type getBasicType() {
        return basicType;
    }

    /**
     * @return element type of array, i.e., for int[][][] the element type is int[][]
     */
    public Type getElementType() {
        if (dimensions == 1) {
            return basicType;
        }
        return new ArrayType(basicType, dimensions - 1);
    }

    /** @return number of dimensions of array
     */
    public int getDimensions() {
        return dimensions;
    }

    /** @return a hash code value for the object.
     */
    @Override
    public int hashCode() {
        return basicType.hashCode() ^ dimensions;
    }

    /** @return true if both type objects refer to the same array type.
     */
    @Override
    public boolean equals( final Object _type ) {
        if (_type instanceof ArrayType array) {
            return (array.dimensions == dimensions) && array.basicType.equals(basicType);
        }
        return false;
    }
}
