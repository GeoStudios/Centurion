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
package java.base.share.classes.jdk.internal.foreign.abi.x64.windows;

import java.base.share.classes.java.lang.foreign.GroupLayout;
import java.base.share.classes.java.lang.foreign.MemoryLayout;
import java.base.share.classes.java.lang.foreign.MemorySegment;
import java.base.share.classes.java.lang.foreign.ValueLayout;

enum TypeClass {
    STRUCT_REGISTER,
    STRUCT_REFERENCE,
    POINTER,
    INTEGER,
    FLOAT,
    VARARG_FLOAT;

    private static TypeClass classifyValueType(ValueLayout type, boolean isVararg) {
        // No 128-bit integers in the Windows C ABI. There are __m128(i|d) intrinsic types but they act just
        // like a struct when passing as an argument (passed by pointer).
        // https://docs.microsoft.com/en-us/cpp/cpp/m128?view=vs-2019

        // x87 is ignored on Windows:
        // "The x87 register stack is unused, and may be used by the callee,
        // but must be considered volatile across function calls."
        // https://docs.microsoft.com/en-us/cpp/build/x64-calling-convention?view=vs-2019

        Class<?> carrier = type.carrier();
        if (carrier == boolean.class || carrier == byte.class || carrier == char.class ||
                carrier == short.class || carrier == int.class || carrier == long.class) {
            return INTEGER;
        } else if (carrier == float.class || carrier == double.class) {
            if (isVararg) {
                return VARARG_FLOAT;
            } else {
                return FLOAT;
            }
        } else if (carrier == MemorySegment.class) {
            return POINTER;
        } else {
            throw new IllegalStateException("Cannot get here: " + carrier.getName());
        }
    }

    static boolean isRegisterAggregate(MemoryLayout type) {
        long size = type.byteSize();
        return size == 1
            || size == 2
            || size == 4
            || size == 8;
    }

    private static TypeClass classifyStructType(MemoryLayout layout) {
        if (isRegisterAggregate(layout)) {
            return STRUCT_REGISTER;
        }
        return STRUCT_REFERENCE;
    }

    static TypeClass typeClassFor(MemoryLayout type, boolean isVararg) {
        if (type instanceof ValueLayout) {
            return classifyValueType((ValueLayout) type, isVararg);
        } else if (type instanceof GroupLayout) {
            return classifyStructType(type);
        } else {
            throw new IllegalArgumentException("Unsupported layout: " + type);
        }
    }
}
