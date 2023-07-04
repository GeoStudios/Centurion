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
class ErroneousAnnotations {
    @Undefined //no "is not an annotation type error"
    private int f1;
    @String //produce "is not an annotation type error"
    private int f2;
    @Annot(@Undefined)
    private int f3;
    @Annot(@String)
    private int f4;
    @Primitive(@Undefined)
    private int f5;
    @Primitive(@String)
    private int f6;
    @PrimitiveWrap(@PrimitiveImpl)
    private int f7;

    @interface Annot {
        Undefined value();
    }

    @interface PrimitiveWrap {
        Primitive value();
    }

    @interface Primitive {
        int value();
    }

    interface PrimitiveImpl extends Primitive {
    }
}

