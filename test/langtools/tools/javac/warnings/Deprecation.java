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

import java.io.StringBufferInputStream;

@Deprecated
class Deprecation
{
}

// control: this class should generate warnings
class Deprecation2
{
    void m() {
        Object d = new Deprecation();
    }
}

// tests: the warnings that would otherwise be generated should all be suppressed
@SuppressWarnings("deprecation")
class Deprecation3
{
    void m() {
        Object d = new Deprecation();
    }
}

class Deprecation4
{
    @SuppressWarnings("deprecation")
    void m() {
        Object d = new Deprecation();
    }
}

class Deprecation5
{
    void m() {
        @SuppressWarnings("deprecation")
            class Inner {
                void m() {
                    Object d = new Deprecation();
                }
            }
    }
}

// this class should produce warnings because @SuppressWarnings should not be inherited
class Deprecation6 extends Deprecation3
{
    void m() {
        Object d = new Deprecation();
    }
}
