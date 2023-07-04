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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.TYPE_PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;

@Target({TYPE_USE, TYPE_PARAMETER, TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface A {}

@interface B { int value(); }

class T0x1E {
    void m0x1E() {
        Class<Object> c = @A Object.class;
    }

    Class<?> c = @A String.class;

    Class<? extends @A String> as = @A String.class;
}

class ClassLiterals {
    public static void main(String[] args) {
        if (String.class != @A String.class) throw new Error();
        if (@A int.class != int.class) throw new Error();
        if (@A int.class != Integer.TYPE) throw new Error();
        if (@A int @B(0) [].class != int[].class) throw new Error();

        if (String[].class != @A String[].class) throw new Error();
        if (String[].class != String @A [].class) throw new Error();
        if (@A int[].class != int[].class) throw new Error();
        if (@A int @B(0) [].class != int[].class) throw new Error();
    }

    Object classLit1 = @A String @C [] @B(0) [].class;
    Object classLit2 = @A String @C []       [].class;
    Object classLit3 = @A String    [] @B(0) [].class;
    Object classLit4 =    String    [] @B(0) [].class;
    Object classLit5 =    String @C []       [].class;
    Object classLit6 =    String    []       [].class;
}
