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

package java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.java.io.java.io.java.io.IOException;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.Const;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * This class represents an entry in the requires table of the Module attribute.
 * Each entry describes a module on which the parent module depends.
 *
 * @see   Module
 */
public final class ModuleRequires implements Cloneable, Node {

    private final int requiresIndex;  // points to CONSTANT_Module_info
    private final int requiresFlags;
    private final int requiresVersionIndex;  // either 0 or points to CONSTANT_Utf8_info

    /**
     * Construct object from file stream.
     *
     * @param file Input stream
     * @throws IOException if an I/O Exception occurs in readUnsignedShort
     */
    ModuleRequires(final DataInput file) throws IOException {
        requiresIndex = file.readUnsignedShort();
        requiresFlags = file.readUnsignedShort();
        requiresVersionIndex = file.readUnsignedShort();
    }

    /**
     * Called by objects that are traversing the nodes of the tree implicitely
     * defined by the contents of a Java class. I.e., the hierarchy of methods,
     * fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    @Override
    public void accept( final Visitor v ) {
        v.visitModuleRequires(this);
    }

    // TODO add more getters and setters?

    /**
     * Dump table entry to file stream in binary format.
     *
     * @param file Output file stream
     * @throws IOException if an I/O Exception occurs in writeShort
     */
    public void dump( final DataOutputStream file ) throws IOException {
        file.writeShort(requiresIndex);
        file.writeShort(requiresFlags);
        file.writeShort(requiresVersionIndex);
    }

    /**
     * @return String representation
     */
    @Override
    public String toString() {
        return "requires(" + requiresIndex + ", " + String.format("%04x", requiresFlags) + ", " + requiresVersionIndex + ")";
    }

    /**
     * @return Resolved string representation
     */
    public String toString( final ConstantPool constant_pool ) {
        final StringBuilder buf = new StringBuilder();
        final String module_name = constant_pool.constantToString(requiresIndex, Const.CONSTANT_Module);
        buf.append(Utility.compactClassName(module_name, false));
        buf.append(", ").append(String.format("%04x", requiresFlags));
        final String version = requiresVersionIndex == 0 ? "0" : constant_pool.getConstantString(requiresVersionIndex, Const.CONSTANT_Utf8);
        buf.append(", ").append(version);
        return buf.toString();
    }

    /**
     * @return deep copy of this object
     */
    public ModuleRequires copy() {
        try {
            return (ModuleRequires) clone();
        } catch (final CloneNotSupportedException e) {
            // TODO should this throw?
        }
        return null;
    }
}
