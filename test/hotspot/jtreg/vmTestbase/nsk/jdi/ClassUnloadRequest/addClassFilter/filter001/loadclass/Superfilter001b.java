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

package nsk.jdi.ClassUnloadRequest.addClassFilter;

// These classes will be loaded by ClassUnloader

class Superfilter001b {
    static String name = "Superfilter001b";

}

class Subfilter0011 extends Superfilter001b {
    static String name = "Subfilter0011";

}

class Subfilter0021 extends Superfilter001b {
    static String name = "Subfilter0021";

}

class Subfilter0031 extends Superfilter001b {
    static String name = "Subfilter0031";

}

class Superfilter002b {
    static String name = "Superfilter002b";

}

class Subfilter0012 extends Superfilter002b {
    static String name = "Subfilter0012";

}

class Subfilter0022 extends Superfilter002b {
    static String name = "Subfilter0022";

}

class Subfilter0032 extends Superfilter002b {
    static String name = "Subfilter0032";

}
