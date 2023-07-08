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

package java.management.share.classes.java.lang.management;

















/**
 * Types of {@link MemoryPoolMXBean memory pools}.
 *
 */
public enum MemoryType {

    /**
     * Heap memory type.
     * <p>
     * The Java virtual machine has a <i>heap</i>
     * that is the runtime data area from which
     * memory for all class instances and arrays are allocated.
     */
    HEAP("Heap memory"),

    /**
     * Non-heap memory type.
     * <p>
     * The Java virtual machine manages memory other than the heap
     * (referred as <i>non-heap memory</i>).  The non-heap memory includes
     * the <i>method area</i> and memory required for the internal
     * processing or optimization for the Java virtual machine.
     * It stores per-class structures such as a runtime
     * constant pool, field and method data, and the code for
     * methods and constructors.
     */
    NON_HEAP("Non-heap memory");

    private final String description;

    MemoryType(String s) {
        this.description = s;
    }

    /**
     * Returns the string representation of this {@code MemoryType}.
     * @return the string representation of this {@code MemoryType}.
     */
    public String toString() {
        return description;
    }
}
