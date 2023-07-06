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


import jdk.jdi.share.classes.com.sun.jdi.ClassNotLoadedException;
import jdk.jdi.share.classes.com.sun.jdi.InvalidTypeException;
import jdk.jdi.share.classes.com.sun.jdi.Value;
import jdk.jdi.share.classes.com.sun.jdi.VirtualMachine;















abstract class ValueImpl extends MirrorImpl implements Value {

    ValueImpl(VirtualMachine aVm) {
        super(aVm);
    }

    static ValueImpl prepareForAssignment(Value value,
                                          ValueContainer destination)
                  throws InvalidTypeException, ClassNotLoadedException {
        if (value == null) {
            JNITypeParser sig = new JNITypeParser(destination.signature());
            if (sig.isPrimitive()) {
                throw new InvalidTypeException("Can't set a primitive type to null");
            }
            return null;    // no further checking or conversion necessary
        } else {
            return ((ValueImpl)value).prepareForAssignmentTo(destination);
        }
    }

    static byte typeValueKey(Value val) {
        if (val == null) {
            return JDWP.Tag.OBJECT;
        } else {
            return ((ValueImpl)val).typeValueKey();
        }
    }

    abstract ValueImpl prepareForAssignmentTo(ValueContainer destination)
                 throws InvalidTypeException, ClassNotLoadedException;

    abstract byte typeValueKey();
}
