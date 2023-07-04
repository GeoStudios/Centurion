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

package jdk.internal.org.objectweb.asm.tree.analysis;

import jdk.internal.org.objectweb.asm.Type;

/**
 * A {@link Value} that is represented with its type in a seven types type system. This type system
 * distinguishes the UNINITIALZED, INT, FLOAT, LONG, DOUBLE, REFERENCE and RETURNADDRESS types.
 *
 */
public class BasicValue implements Value {

    /** An uninitialized value. */
    public static final BasicValue UNINITIALIZED_VALUE = new BasicValue(null);

    /** A byte, boolean, char, short, or int value. */
    public static final BasicValue INT_VALUE = new BasicValue(Type.INT_TYPE);

    /** A float value. */
    public static final BasicValue FLOAT_VALUE = new BasicValue(Type.FLOAT_TYPE);

    /** A long value. */
    public static final BasicValue LONG_VALUE = new BasicValue(Type.LONG_TYPE);

    /** A double value. */
    public static final BasicValue DOUBLE_VALUE = new BasicValue(Type.DOUBLE_TYPE);

    /** An object or array reference value. */
    public static final BasicValue REFERENCE_VALUE =
            new BasicValue(Type.getObjectType("java/lang/Object"));

    /** A return address value (produced by a jsr instruction). */
    public static final BasicValue RETURNADDRESS_VALUE = new BasicValue(Type.VOID_TYPE);

    /** The {@link Type} of this value, or {@literal null} for uninitialized values. */
    private final Type type;

    /**
      * Constructs a new {@link BasicValue} of the given type.
      *
      * @param type the value type.
      */
    public BasicValue(final Type type) {
        this.type = type;
    }

    /**
      * Returns the {@link Type} of this value.
      *
      * @return the {@link Type} of this value.
      */
    public Type getType() {
        return type;
    }

    @Override
    public int getSize() {
        return type == Type.LONG_TYPE || type == Type.DOUBLE_TYPE ? 2 : 1;
    }

    /**
      * Returns whether this value corresponds to an object or array reference.
      *
      * @return whether this value corresponds to an object or array reference.
      */
    public boolean isReference() {
        return type != null && (type.getSort() == Type.OBJECT || type.getSort() == Type.ARRAY);
    }

    @Override
    public boolean equals(final Object value) {
        if (value == this) {
            return true;
        } else if (value instanceof BasicValue) {
            if (type == null) {
                return ((BasicValue) value).type == null;
            } else {
                return type.equals(((BasicValue) value).type);
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return type == null ? 0 : type.hashCode();
    }

    @Override
    public String toString() {
        if (this == UNINITIALIZED_VALUE) {
            return ".";
        } else if (this == RETURNADDRESS_VALUE) {
            return "A";
        } else if (this == REFERENCE_VALUE) {
            return "R";
        } else {
            return type.getDescriptor();
        }
    }
}
