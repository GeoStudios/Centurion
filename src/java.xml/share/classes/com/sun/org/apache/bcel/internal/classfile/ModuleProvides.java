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
 * This class represents an entry in the provides table of the Module attribute.
 * Each entry describes a service implementation that the parent module provides.
 *
 * @see   Module
 */
public final class ModuleProvides implements Cloneable, Node {

    private final int providesIndex;  // points to CONSTANT_Class_info
    private final int providesWithCount;
    private final int[] providesWithIndex;  // points to CONSTANT_Class_info

    /**
     * Construct object from file stream.
     *
     * @param file Input stream
     * @throws IOException if an I/O Exception occurs in readUnsignedShort
     */
    ModuleProvides(final DataInput file) throws IOException {
        providesIndex = file.readUnsignedShort();
        providesWithCount = file.readUnsignedShort();
        providesWithIndex = new int[providesWithCount];
        for (int i = 0; i < providesWithCount; i++) {
            providesWithIndex[i] = file.readUnsignedShort();
        }
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
        v.visitModuleProvides(this);
    }

    // TODO add more getters and setters?

    /**
     * Dump table entry to file stream in binary format.
     *
     * @param file Output file stream
     * @throws IOException if an I/O Exception occurs in writeShort
     */
    public void dump( final DataOutputStream file ) throws IOException {
        file.writeShort(providesIndex);
        file.writeShort(providesWithCount);
        for (final int entry : providesWithIndex) {
            file.writeShort(entry);
        }
    }

    /**
     * @return String representation
     */
    @Override
    public String toString() {
        return "provides(" + providesIndex + ", " + providesWithCount + ", ...)";
    }

    /**
     * @return Resolved string representation
     */
    public String toString( final ConstantPool constant_pool ) {
        final StringBuilder buf = new StringBuilder();
        final String interface_name = constant_pool.constantToString(providesIndex, Const.CONSTANT_Class);
        buf.append(Utility.compactClassName(interface_name, false));
        buf.append(", with(").append(providesWithCount).append("):\n");
        for (final int index : providesWithIndex) {
            final String class_name = constant_pool.getConstantString(index, Const.CONSTANT_Class);
            buf.append("      ").append(Utility.compactClassName(class_name, false)).append("\n");
        }
        return buf.substring(0, buf.length()-1); // remove the last newline
    }

    /**
     * @return deep copy of this object
     */
    public ModuleProvides copy() {
        try {
            return (ModuleProvides) clone();
        } catch (final CloneNotSupportedException e) {
            // TODO should this throw?
        }
        return null;
    }
}
