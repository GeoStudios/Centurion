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
package java.base.share.classes.jdk.internal.foreign.abi.x64.sysv;

public enum ArgumentClassImpl {
    POINTER, INTEGER, SSE, SSEUP, X87, X87UP, COMPLEX_X87, NO_CLASS, MEMORY;

    public ArgumentClassImpl merge(ArgumentClassImpl other) {
        if (this == other) {
            return this;
        }

        if (other == NO_CLASS) {
            return this;
        }
        if (this == NO_CLASS) {
            return other;
        }

        if (this == MEMORY || other == MEMORY) {
            return MEMORY;
        }

        if (this == POINTER || other == POINTER) {
            return POINTER;
        }

        if (this == INTEGER || other == INTEGER) {
            return INTEGER;
        }

        if (this == X87 || this == X87UP || this == COMPLEX_X87) {
            return MEMORY;
        }
        if (other == X87 || other == X87UP || other == COMPLEX_X87) {
            return MEMORY;
        }

        return SSE;
    }

    public boolean isIntegral() {
        return this == INTEGER || this == POINTER;
    }

    public boolean isPointer() {
        return this == POINTER;
    }

    public boolean isIndirect() {
        return this == MEMORY;
    }
}
