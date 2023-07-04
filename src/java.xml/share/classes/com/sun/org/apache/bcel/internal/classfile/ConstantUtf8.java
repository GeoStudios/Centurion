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

package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.Const;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Extends the abstract {@link Constant} to represent a reference to a UTF-8 encoded string.
 *
 * @see Constant
 * @LastModified: Jan 2020
 */
public final class ConstantUtf8 extends Constant {

    private static class Cache {

        private static final boolean BCEL_STATISTICS = false;
        private static final int MAX_ENTRIES = 20000;
        private static final int INITIAL_CAPACITY = (int) (MAX_ENTRIES / 0.75);

        private static final HashMap<String, ConstantUtf8> CACHE = new LinkedHashMap<String, ConstantUtf8>(
            INITIAL_CAPACITY, 0.75f, true) {

            private static final long serialVersionUID = -8506975356158971766L;

            @Override
            protected boolean removeEldestEntry(final Map.Entry<String, ConstantUtf8> eldest) {
                return size() > MAX_ENTRIES;
            }
        };

        // Set the size to 0 or below to skip caching entirely
        private static final int MAX_ENTRY_SIZE = 200;

        static boolean isEnabled() {
            return Cache.MAX_ENTRIES > 0 && MAX_ENTRY_SIZE > 0;
        }

    }

    /**
     * Clears the cache.
     *
     */
    public static synchronized void clearCache() {
        Cache.CACHE.clear();
    }

    /**
     * Gets a new or cached instance of the given value.
     * <p>
     * See {@link ConstantUtf8} class Javadoc for details.
     * </p>
     *
     * @param value the value.
     * @return a new or cached instance of the given value.
     */
    public static ConstantUtf8 getCachedInstance(final String value) {
        if (value.length() > Cache.MAX_ENTRY_SIZE) {
            return new ConstantUtf8(value);
        }

        synchronized (ConstantUtf8.class) { // might be better with a specific lock object
            ConstantUtf8 result = Cache.CACHE.get(value);
            if (result != null) {
                return result;
            }
            result = new ConstantUtf8(value);
            Cache.CACHE.put(value, result);
            return result;
        }
    }

    /**
     * Gets a new or cached instance of the given value.
     * <p>
     * See {@link ConstantUtf8} class Javadoc for details.
     * </p>
     *
     * @param dataInput the value.
     * @return a new or cached instance of the given value.
     * @throws IOException if an I/O error occurs.
     */
    public static ConstantUtf8 getInstance(final DataInput dataInput) throws IOException {
        return getInstance(dataInput.readUTF());
    }

    /**
     * Gets a new or cached instance of the given value.
     * <p>
     * See {@link ConstantUtf8} class Javadoc for details.
     * </p>
     *
     * @param value the value.
     * @return a new or cached instance of the given value.
     */
    public static ConstantUtf8 getInstance(final String value) {
        return Cache.isEnabled() ? getCachedInstance(value) : new ConstantUtf8(value);
    }

    private final String value;

    /**
     * Initializes from another object.
     *
     * @param constantUtf8 the value.
     */
    public ConstantUtf8(final ConstantUtf8 constantUtf8) {
        this(constantUtf8.getBytes());
    }

    /**
     * Initializes instance from file data.
     *
     * @param dataInput Input stream
     * @throws IOException
     */
    ConstantUtf8(final DataInput dataInput) throws IOException {
        super(Const.CONSTANT_Utf8);
        value = dataInput.readUTF();
    }

    /**
     * @param value Data
     */
    public ConstantUtf8(final String value) {
        super(Const.CONSTANT_Utf8);
        if (value == null) {
            throw new IllegalArgumentException("Value must not be null.");
        }
        this.value = value;
    }

    /**
     * Called by objects that are traversing the nodes of the tree implicitely defined by the contents of a Java class.
     * I.e., the hierarchy of methods, fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    @Override
    public void accept(final Visitor v) {
        v.visitConstantUtf8(this);
    }

    /**
     * Dumps String in Utf8 format to file stream.
     *
     * @param file Output file stream
     * @throws IOException
     */
    @Override
    public void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.getTag());
        file.writeUTF(value);
    }

    /**
     * @return Data converted to string.
     */
    public String getBytes() {
        return value;
    }

    /**
     * @param bytes the raw bytes of this UTF-8
     * @deprecated (since 6.0)
     */
    @java.lang.Deprecated
    public void setBytes(final String bytes) {
        throw new UnsupportedOperationException();
    }

    /**
     * @return String representation
     */
    @Override
    public String toString() {
        return super.toString() + "(\"" + Utility.replace(value, "\n", "\\n") + "\")";
    }
}
