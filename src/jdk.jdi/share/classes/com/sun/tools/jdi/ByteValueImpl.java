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

package jdk.jdi.share.classes.com.sun.tools.jdi;

import jdk.jdi.share.classes.com.sun.jdi.ByteValue;
import jdk.jdi.share.classes.com.sun.jdi.InvalidTypeException;
import jdk.jdi.share.classes.com.sun.jdi.Type;
import jdk.jdi.share.classes.com.sun.jdi.VirtualMachine;

public class ByteValueImpl extends PrimitiveValueImpl
                           implements ByteValue
{
    private final byte value;

    ByteValueImpl(VirtualMachine aVm, byte aValue) {
        super(aVm);
        value = aValue;
    }

    public boolean equals(Object obj) {
        if ((obj != null) && (obj instanceof ByteValue)) {
            return (value == ((ByteValue)obj).value())
                   && super.equals(obj);
        } else {
            return false;
        }
    }

    public int hashCode() {
        /*
         * TO DO: Better hash code
         */
        return intValue();
    }

    public int compareTo(ByteValue obj) {
        byte other = obj.value();
        return value() - other;
    }

    public Type type() {
        return vm.theByteType();
    }

    public byte value() {
        return value;
    }

    public boolean booleanValue() {
        return (value != 0);
    }

    public byte byteValue() {
        return value;
    }

    public char charValue() {
        return (char)value;
    }

    public short shortValue() {
        return value;
    }

    public int intValue() {
        return value;
    }

    public long longValue() {
        return value;
    }

    public float floatValue() {
        return value;
    }

    public double doubleValue() {
        return value;
    }

    char checkedCharValue() throws InvalidTypeException {
        if ((value > Character.MAX_VALUE) || (value < Character.MIN_VALUE)) {
            throw new InvalidTypeException("Can't convert " + value + " to char");
        } else {
            return super.checkedCharValue();
        }
    }

    public String toString() {
        return String.valueOf(value);
    }

    byte typeValueKey() {
        return JDWP.Tag.BYTE;
    }
}
