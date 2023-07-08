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

package jdk.compiler.share.classes.com.sun.tools.sjavac.pubapi;


import java.io.Serializable;
import javax.lang.model.type.TypeKind;
import jdk.compiler.share.classes.com.sun.tools.javac.util.StringUtils;















public class PrimitiveTypeDesc extends TypeDesc implements Serializable {

    private static final long serialVersionUID = 6051065543149129106L;

    public PrimitiveTypeDesc(TypeKind typeKind) {
        super(typeKind);
        if (!typeKind.isPrimitive() && typeKind != TypeKind.VOID)
            throw new IllegalArgumentException("Only primitives or void accepted");
    }

    // This class has no fields, so the inherited hashCode and equals should do fine.

    @Override
    public String toString() {
        return StringUtils.toLowerCase(typeKind.toString());
    }
}
