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
 * This class represents a BootstrapMethods attribute.
 *
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.7.23">
 * The class File Format : The BootstrapMethods Attribute</a>
 */
public class BootstrapMethods extends Attribute {

    private BootstrapMethod[] bootstrapMethods;  // TODO this could be made final (setter is not used)

    /**
     * Initialize from another object. Note that both objects use the same
     * references (shallow copy). Use clone() for a physical copy.
     */
    public BootstrapMethods(final BootstrapMethods c) {
        this(c.getNameIndex(), c.getLength(), c.getBootstrapMethods(), c.getConstantPool());
    }

    /**
     * @param name_index Index in constant pool to CONSTANT_Utf8
     * @param length Content length in bytes
     * @param bootstrapMethods array of bootstrap methods
     * @param constant_pool Array of constants
     */
    public BootstrapMethods(final int name_index, final int length, final BootstrapMethod[] bootstrapMethods, final ConstantPool constant_pool) {
        super(Const.ATTR_BOOTSTRAP_METHODS, name_index, length, constant_pool);
        this.bootstrapMethods = bootstrapMethods;
    }

    /**
     * Construct object from Input stream.
     *
     * @param name_index Index in constant pool to CONSTANT_Utf8
     * @param length Content length in bytes
     * @param input Input stream
     * @param constant_pool Array of constants
     * @throws IOException
     */
    BootstrapMethods(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, (BootstrapMethod[]) null, constant_pool);

        final int num_bootstrap_methods = input.readUnsignedShort();
        bootstrapMethods = new BootstrapMethod[num_bootstrap_methods];
        for (int i = 0; i < num_bootstrap_methods; i++) {
            bootstrapMethods[i] = new BootstrapMethod(input);
        }
    }

    /**
     * @return array of bootstrap method "records"
     */
    public final BootstrapMethod[] getBootstrapMethods() {
        return bootstrapMethods;
    }

    /**
     * @param bootstrapMethods the array of bootstrap methods
     */
    public final void setBootstrapMethods(final BootstrapMethod[] bootstrapMethods) {
        this.bootstrapMethods = bootstrapMethods;
    }

    /**
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitBootstrapMethods(this);
    }

    /**
     * @return deep copy of this attribute
     */
    @Override
    public BootstrapMethods copy(final ConstantPool _constant_pool) {
        final BootstrapMethods c = (BootstrapMethods) clone();
        c.bootstrapMethods = new BootstrapMethod[bootstrapMethods.length];

        for (int i = 0; i < bootstrapMethods.length; i++) {
            c.bootstrapMethods[i] = bootstrapMethods[i].copy();
        }
        c.setConstantPool(_constant_pool);
        return c;
    }

    /**
     * Dump bootstrap methods attribute to file stream in binary format.
     *
     * @param file Output file stream
     * @throws IOException
     */
    @Override
    public final void dump(final DataOutputStream file) throws IOException {
        super.dump(file);

        file.writeShort(bootstrapMethods.length);
        for (final BootstrapMethod bootstrap_method : bootstrapMethods) {
            bootstrap_method.dump(file);
        }
    }

    /**
     * @return String representation.
     */
    @Override
    public final String toString() {
        final StringBuilder buf = new StringBuilder();
        buf.append("BootstrapMethods(");
        buf.append(bootstrapMethods.length);
        buf.append("):");
        for (int i = 0; i < bootstrapMethods.length; i++) {
            buf.append("\n");
            final int start = buf.length();
            buf.append("  ").append(i).append(": ");
            final int indent_count = buf.length() - start;
            final String[] lines = (bootstrapMethods[i].toString(super.getConstantPool())).split("\\r?\\n");
            buf.append(lines[0]);
            for (int j = 1; j < lines.length; j++) {
                buf.append("\n").append("          ", 0, indent_count).append(lines[j]);
            }
        }
        return buf.toString();
    }
}
