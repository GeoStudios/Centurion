/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.share.classes.jdk.internal.org.objectweb.asm.tree.analysis;

import java.base.share.classes.jdk.internal.org.objectweb.asm.Type;

/**
 * A {@link Value} that is represented with its type in a seven types type system. This type system
 * distinguishes the UNINITIALZED, INT, FLOAT, LONG, DOUBLE, REFERENCE and RETURNADDRESS types.
 *
 * @author Eric Bruneton
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

