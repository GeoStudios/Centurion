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

public interface ELFSymbol {
    /** Binding specifying that local symbols are not visible outside the
     * object file that contains its definition. */
    int BINDING_LOCAL = 0;
    /** Binding specifying that global symbols are visible to all object files
     * being combined. */
    int BINDING_GLOBAL = 1;
    /** Binding secifying that the symbol resembles a global symbol, but has
     * a lower precedence. */
    int BINDING_WEAK = 2;
    /** Lower bound binding values reserverd for processor specific
     * semantics. */
    int BINDING_LOPROC = 13;
    /** Upper bound binding values reserverd for processor specific
     * semantics. */
    int BINDING_HIPROC = 15;

    /** Type specifying that the symbol is unspecified. */
    byte TYPE_NOOBJECT = 0;
    /** Type specifying that the symbol is associated with an object. */
    byte TYPE_OBJECT = 1;
    /** Type specifying that the symbol is associated with a function. */
    byte TYPE_FUNCTION = 2;
    /** Type specifying that the symbol is associated with a section.  Symbol
     * table entries of this type exist for relocation and normally have the
     * binding BINDING_LOCAL. */
    byte TYPE_SECTION = 3;
    /** Type defining that the symbol is associated with a file. */
    byte TYPE_FILE = 4;
    /** Lower bound type reserved for processor specific semantics. */
    byte TYPE_LOPROC = 13;
    /** Upper bound type reserved for processor specific semantics. */
    byte TYPE_HIPROC = 15;

    /** Returns the location from the beginning of the file to the symbol. */
    long getOffset();
    /** Returns the name of the symbol or null if the symbol has no name. */
    String getName();
    /** Returns the binding for this symbol. */
    int getBinding();
    /** Returns the symbol type. */
    int getType();

    /** Value of the associated symbol.  This may be a relativa address for .so
     * or absolute address for other ELFs. */
    long getValue();

    /** Size of the symbol.  0 if the symbol has no size or the size
     * is unknown. */
    long getSize();
}
