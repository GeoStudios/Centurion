/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.base.share.classes.jdk.internal.foreign.abi.aarch64;

import java.base.share.classes.java.lang.foreign.GroupLayout;
import java.base.share.classes.java.lang.foreign.MemoryLayout;
import java.base.share.classes.java.lang.foreign.MemorySegment;
import java.base.share.classes.java.lang.foreign.ValueLayout;

public enum TypeClass {
    STRUCT_REGISTER,
    STRUCT_REFERENCE,
    STRUCT_HFA,
    POINTER,
    INTEGER,
    FLOAT;

    private static final int MAX_AGGREGATE_REGS_SIZE = 2;

    private static TypeClass classifyValueType(ValueLayout type) {
        Class<?> carrier = type.carrier();
        if (carrier == boolean.class || carrier == byte.class || carrier == char.class ||
                carrier == short.class || carrier == int.class || carrier == long.class) {
            return INTEGER;
        } else if (carrier == float.class || carrier == double.class) {
            return FLOAT;
        } else if (carrier == MemorySegment.class) {
            return POINTER;
        } else {
            throw new IllegalStateException("Cannot get here: " + carrier.getName());
        }
    }

    static boolean isRegisterAggregate(MemoryLayout type) {
        return type.bitSize() <= MAX_AGGREGATE_REGS_SIZE * 64;
    }

    static boolean isHomogeneousFloatAggregate(MemoryLayout type) {
        if (!(type instanceof GroupLayout groupLayout))
            return false;

        final int numElements = groupLayout.memberLayouts().size();
        if (numElements > 4 || numElements == 0)
            return false;

        MemoryLayout baseType = groupLayout.memberLayouts().get(0);

        if (!(baseType instanceof ValueLayout))
            return false;

        TypeClass baseArgClass = classifyValueType((ValueLayout) baseType);
        if (baseArgClass != FLOAT)
           return false;

        for (MemoryLayout elem : groupLayout.memberLayouts()) {
            if (!(elem instanceof ValueLayout))
                return false;

            TypeClass argClass = classifyValueType((ValueLayout) elem);
            if (elem.bitSize() != baseType.bitSize() ||
                    elem.bitAlignment() != baseType.bitAlignment() ||
                    baseArgClass != argClass) {
                return false;
            }
        }

        return true;
    }

    private static TypeClass classifyStructType(MemoryLayout layout) {
        if (isHomogeneousFloatAggregate(layout)) {
            return TypeClass.STRUCT_HFA;
        } else if (isRegisterAggregate(layout)) {
            return TypeClass.STRUCT_REGISTER;
        }
        return TypeClass.STRUCT_REFERENCE;
    }

    public static TypeClass classifyLayout(MemoryLayout type) {
        if (type instanceof ValueLayout) {
            return classifyValueType((ValueLayout) type);
        } else if (type instanceof GroupLayout) {
            return classifyStructType(type);
        } else {
            throw new IllegalArgumentException("Unsupported layout: " + type);
        }
    }
}
