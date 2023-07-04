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

package sun.jvm.hotspot.debugger.posix.elf;

public interface ELFFile {
    /** ELF magic number. */
    byte[] ELF_MAGIC_NUMBER = { 0x7f, 'E', 'L', 'F' };

    byte CLASS_INVALID = 0;
    /** 32-bit objects. */
    byte CLASS_32 = 1;
    /** 64-bit objects. */
    byte CLASS_64 = 2;

    /** No data encoding. */
    byte DATA_INVALID = 0;
    /** LSB data encoding. */
    byte DATA_LSB = 1;
    /** MSB data encoding. */
    byte DATA_MSB = 2;

    /** No ELF header version. */
    byte VERSION_INVALID = 0;
    /** Current ELF header version. */
    byte VERSION_CURRENT = 1;

    byte NDX_MAGIC_0 = 0;
    byte NDX_MAGIC_1 = 1;
    byte NDX_MAGIC_2 = 2;
    byte NDX_MAGIC_3 = 3;
    byte NDX_OBJECT_SIZE = 4;
    byte NDX_ENCODING = 5;
    byte NDX_VERSION = 6;

    ELFHeader getHeader();
    void close();

    /** Returns the 4 byte magic number for this file.  This value should
     * match the values in ELF_MAGIC_NUMBER. */
    byte[] getMagicNumber();
    /** Returns a byte identifying the size of objects used for this ELF
     * file.  The byte will be either CLASS_INVALID, CLASS_32 or CLASS_64. */
    byte getObjectSize();
    /** Returns a byte identifying the data encoding of the processor specific
     * data.  This byte will be either DATA_INVALID, DATA_LSB or DATA_MSB. */
    byte getEncoding();
    /** Returns one of the version constants. This should be VERSION_CURRENT. */
    byte getVersion();
}
