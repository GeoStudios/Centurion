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
import p1.*;

class T6313164 {
    {
        B b = new B();
        b.foo1(new B(), new B()); //error - A not accessible
        /*   7  : ok - A not accessible, but foo2(Object...) applicable
         *   8+ : error - A not accessible
         */
        b.foo2(new B(), new B());
        b.foo3(null, null); //error - A (inferred) not accessible
        b.foo4(null, null); //error - A not accesible
        /*   7  : ok - A not accessible, but foo4(Object...) applicable
         *   8+ : error - A not accessible
         */
        b.foo4(new B(), new C());
    }
}
