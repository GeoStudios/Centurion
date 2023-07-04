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
 * Test that an auxiliary class referenced from its own source file,
 * does not trigger the warning. Such code does not prevent implicit
 * compilation. Also test that references to inner classes do not trigger the warning.
 */

/*
 * @test
 * @bug 7153951
 * @run compile -Werror -Xlint:auxiliaryclass SelfClassWithAux.java ClassWithAuxiliary.java
 * @run compile -Werror -Xlint:auxiliaryclass SelfClassWithAux.java
 */

class SelfClassWithAux {
    AuxClass aux;
    ClassWithAuxiliary.NotAnAuxiliaryClass alfa;
    ClassWithAuxiliary.NotAnAuxiliaryClassEither beta;
}

class AuxClass {
    AuxClass aux;
}
