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

public class AssignmentDifferentTypes {

    public static void main(String[] args) {
        Ref<Der> derexact = null;
        Ref<Base> baseexact = null;
        Ref<? extends Der> derext = null;
        Ref<? extends Base> baseext = null;
        Ref<? super Der> dersuper = null;
        Ref<? super Base> basesuper = null;

        baseext = derext;       // <<pass>> <? extends Base> = <? extends Der>
        baseext = derexact;     // <<pass>> <? extends Base> = <Der>
        dersuper = basesuper;   // <<pass>> <? super Der> = <? super Base>
        dersuper = baseexact;   // <<pass>> <? super Der> = <Base>

        derexact = baseexact;   // <<fail>> <Der> = <Base>
        baseexact = derexact;   // <<fail>> <Base> = <Der>
        derext = baseext;       // <<fail>> <? extends Der> = <? extends Base>
        derext = baseexact;     // <<fail>> <? extends Der> = <Base>
        derext = basesuper;     // <<fail>> <? extends Der> = <? super Base>
        baseext = dersuper;     // <<fail>> <? extends Base> = <? super Der>
        basesuper = dersuper;   // <<fail>> <? super Base> = <? super Der>
        basesuper = derexact;   // <<fail>> <? super Base> = <Der>
    }
}

class Ref<T> {}
class Base {}
class Der extends Base {}
