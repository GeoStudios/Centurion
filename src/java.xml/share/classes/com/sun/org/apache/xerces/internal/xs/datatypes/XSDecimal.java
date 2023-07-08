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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.datatypes;

import java.math.BigDecimal;
import java.math.BigInteger;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * <p>Interface to expose the value of 'decimal' and related datatypes.</p>
 *
 *
 */
public interface XSDecimal {

    /**
     * @return the <code>BigDecimal</code> representation of this object
     */
    BigDecimal getBigDecimal();

    /**
     * @return the <code>BigInteger</code> representation of this object
     * @exception NumberFormatException if the value cannot be represented as a <code>BigInteger</code>
     */
    BigInteger getBigInteger() throws NumberFormatException;

    /**
     * @return the long value representation of this object
     * @exception NumberFormatException if the value cannot be represented as a <code>long</code>
     */
    long getLong() throws NumberFormatException;

    /**
     * @return the int value representation of this object
     * @exception NumberFormatException if the value cannot be represented as a <code>int</code>
     */
    int getInt() throws NumberFormatException;

    /**
     * @return the short value representation of this object
     * @exception NumberFormatException if the value cannot be represented as a <code>short</code>
     */
    short getShort() throws NumberFormatException;

    /**
     * @return the byte value representation of this object
     * @exception NumberFormatException if the value cannot be represented as a <code>byte</code>
     */
    byte getByte() throws NumberFormatException;
}
