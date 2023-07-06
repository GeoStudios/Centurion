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

package knownpkg;
















public class MisleadingNonExistentPathError {

    void classNotFound() {
        // Not found, but in an existing package
        Class<?> c1 = knownpkg.NotFound.class;

        // Not found, but in a (system) package which exists and is in scope
        Class<?> c2 = java.lang.NotFound.class;

        // Not found, on a genuinely unknown package
        Class<?> c3 = unknownpkg.NotFound.class;

        // Not found, but in the 'java' package which is in scope as per JLS 6.3 and observable as per JLS 7.4.3
        Class<?> c4 = java.NotFound.class;

        // Not found, but in a (system) package which exists and is in scope
        Object c5 = new java.lang();
    }
}
