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

package pkg;


import java.lang.invoke.MethodHandles;
import java.util.Set;














public class A {
    public static Object f_public_static;
    protected static Object f_protected_static;
    static /*package*/ Object f_package_static;
    private static Object f_private_static;

    public static final Object f_public_static_final = null;
    protected static final Object f_protected_static_final = null;
    static /*package*/ final Object f_package_static_final = null;
    private static final Object f_private_static_final = null;

    public Object f_public;
    protected Object f_protected;
    /*package*/ Object f_package;
    private Object f_private;

    public final Object f_public_final = null;
    protected final Object f_protected_final = null;
    /*package*/ final Object f_package_final = null;
    private final Object f_private_final = null;

    //

    public static MethodHandles.Lookup lookup() {
        return MethodHandles.lookup();
    }

    public static Set<String> inaccessibleFields() {
        // All fields of pkg.A are accessible to itself
        return Set.of();
    }
}
