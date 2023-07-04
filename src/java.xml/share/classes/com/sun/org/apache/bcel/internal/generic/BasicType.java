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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;

/**
 * Denotes basic type such as int.
 *
 */
public final class BasicType extends Type {

    /**
     * Constructor for basic types such as int, long, `void'
     *
     * @param type one of T_INT, T_BOOLEAN, ..., T_VOID
     * @see Const
     */
    BasicType(final byte type) {
        super(type, Const.getShortTypeName(type));
        if ((type < Const.T_BOOLEAN) || (type > Const.T_VOID)) {
            throw new ClassGenException("Invalid type: " + type);
        }
    }


    public static BasicType getType( final byte type ) {
        switch (type) {
            case Const.T_VOID:
                return VOID;
            case Const.T_BOOLEAN:
                return BOOLEAN;
            case Const.T_BYTE:
                return BYTE;
            case Const.T_SHORT:
                return SHORT;
            case Const.T_CHAR:
                return CHAR;
            case Const.T_INT:
                return INT;
            case Const.T_LONG:
                return LONG;
            case Const.T_DOUBLE:
                return DOUBLE;
            case Const.T_FLOAT:
                return FLOAT;
            default:
                throw new ClassGenException("Invalid type: " + type);
        }
    }


    /** @return a hash code value for the object.
     */
    @Override
    public int hashCode() {
        return super.getType();
    }


    /** @return true if both type objects refer to the same type
     */
    @Override
    public boolean equals( final Object _type ) {
        return _type instanceof BasicType && ((BasicType) _type).getType() == this.getType();
    }
}
