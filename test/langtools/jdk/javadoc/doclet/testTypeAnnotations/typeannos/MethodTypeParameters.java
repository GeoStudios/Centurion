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

package typeannos;


import java.lang.annotation.*;














/*
 * This class is replicated from test/tools/javac/annotations/typeAnnotations/newlocations.
 */
class UnscopedUnmodified {
    <K extends @MTyParamA String> void methodExtends() {}
    <K extends @MTyParamA MtdTyParameterized<@MTyParamB String>> void nestedExtends() {}
    <K extends @MTyParamA String, V extends @MTyParamA MtdTyParameterized<@MTyParamB String>> void dual() {}
    <K extends String, V extends MtdTyParameterized<@MTyParamB String>> void dualOneAnno() {}
}

class PublicModifiedMethods {
    public final <K extends @MTyParamA String> void methodExtends() {}
    public final <K extends @MTyParamA MtdTyParameterized<@MTyParamB String>> void nestedExtends() {}
    public final <K extends @MTyParamA String, V extends @MTyParamA MtdTyParameterized<@MTyParamB String>> void dual() {}
    public final <K extends String, V extends MtdTyParameterized<@MTyParamB String>> void dualOneAnno() {}
}

class MtdTyParameterized<K> { }

@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@Documented
@interface MTyParamA { }
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@Documented
@interface MTyParamB { }
