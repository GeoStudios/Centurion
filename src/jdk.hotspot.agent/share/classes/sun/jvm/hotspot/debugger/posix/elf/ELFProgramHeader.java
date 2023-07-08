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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.posix.elf;

















/**
 * This is the interface definintion for a ProgramHeader in an ELF file.
 * Program headers contain system information necessary for preparing a program
 * for execution.
 */
public interface ELFProgramHeader {
    /** Type defining that the array element is unused.  Other member values
     * are undefined. */
    int TYPE_NULL = 0;
    /** Type defining that the array element specifies a loadable segment. */
    int TYPE_LOAD = 1;
    int TYPE_DYNAMIC = 2;
    int TYPE_INTERP = 3;
    int TYPE_NOTE = 4;
    int TYPE_SHLIB = 5;
    int TYPE_PHDR = 6;
    int TYPE_LOPROC = 0x70000000;
    int TYPE_HIPROC = 0x7fffffff;

    int getType();
}
