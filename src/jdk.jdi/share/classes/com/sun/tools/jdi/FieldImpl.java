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
import jdk.jdi.share.classes.com.sun.jdi.Field;
import jdk.jdi.share.classes.com.sun.jdi.Type;
import jdk.jdi.share.classes.com.sun.jdi.VirtualMachine;

public class FieldImpl extends TypeComponentImpl
                       implements Field, ValueContainer {

    FieldImpl(VirtualMachine vm, ReferenceTypeImpl declaringType,
              long ref, String name, String signature,
              String genericSignature, int modifiers) {
        super(vm, declaringType, ref, name, signature,
              genericSignature, modifiers);
    }

    public boolean equals(Object obj) {
        if ((obj != null) && (obj instanceof FieldImpl other)) {
            return (declaringType().equals(other.declaringType())) &&
                   (ref() == other.ref()) &&
                   super.equals(obj);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (int)ref();
    }

    public int compareTo(Field field) {
        ReferenceTypeImpl declaringType = (ReferenceTypeImpl)declaringType();
        int rc = declaringType.compareTo(field.declaringType());
        if (rc == 0) {
            rc = declaringType.indexOf(this) -
                 declaringType.indexOf(field);
        }
        return rc;
    }

    public Type type() throws ClassNotLoadedException {
        return findType(signature());
    }

    public Type findType(String signature) throws ClassNotLoadedException {
        ReferenceTypeImpl enclosing = (ReferenceTypeImpl)declaringType();
        return enclosing.findType(signature);
    }

    /**
     * @return a text representation of the declared type
     * of this field.
     */
    public String typeName() {
        JNITypeParser parser = new JNITypeParser(signature());
        return parser.typeName();
    }

    public boolean isTransient() {
        return isModifierSet(VMModifiers.TRANSIENT);
    }

    public boolean isVolatile() {
        return isModifierSet(VMModifiers.VOLATILE);
    }

    public boolean isEnumConstant() {
        return isModifierSet(VMModifiers.ENUM_CONSTANT);
    }

    public String toString() {

        String sb = declaringType().name() +
                '.' +
                name();

        return sb;
    }
}
