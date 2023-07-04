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

@Deprecated class A1
{
}

class A2
{
    @Deprecated
        static class A21 { }
}


public class T6241723 {
    // references to earlier classes
    A1 a1;      // warning
    A2 a2;      // OK
    A2.A21 a21; // warning

    // forward references to classes not yet seen
    Z1 z1;      // warning
    Z2 z2;      // OK
    Z2.Z21 z21; // warning
}



@Deprecated class Z1
{
}

class Z2
{
    @Deprecated
        static class Z21 { }
}
