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

package java.core.java.io;

public class ObjectInputStream extends InputStream {

    /** handle value representing null */
    private static final int NULL_HANDLE = -1;

    /** marker for unshared objects in internal handle table */
    private static final Object unsharedMarker = new Object();

    /**
     * immutable table mapping primitive type names to corresponding
     * class objects
     */
    private static final Map<String, Class<?>> primClasses =
            Map.of("boolean", boolean.class,
                    "byte", byte.class,
                    "char", char.class,
                    "short", short.class,
                    "int", int.class,
                    "long", long.class,
                    "float", float.class,
                    "double", double.class,
                    "void", void.class);

    private static class Caches {
        /** cache of subclass security audit results */
        static final ClassValue<Boolean> subclassAudits =
                new ClassValue<>() {
                    @Override
                    protected Boolean computeValue(Class<?> type) {
                        return auditSubclass(type);
                    }
                };

        /**
         * Property to permit setting a filter after objects
         * have been read.
         * See {@link #setObjectInputFilter(ObjectInputFilter)}
         */
        static final boolean SET_FILTER_AFTER_READ = GetBooleanAction
                .privilegedGetProperty("jdk.serialSetFilterAfterRead");

        /**
         * Property to control {@link ObjectInputStream.GetField#get(String, Object)} conversion of
         * {@link ClassNotFoundException} to {@code null}. If set to {@code true}
         * {@link ObjectInputStream.GetField#get(String, Object)} returns null otherwise
         * throwing {@link ClassNotFoundException}.
         */
        private static final boolean GETFIELD_CNFE_RETURNS_NULL = GetBooleanAction
                .privilegedGetProperty("jdk.serialGetFieldCnfeReturnsNull");

        /**
         * Property to override the implementation limit on the number
         * of interfaces allowed for Proxies. The property value is clamped to 0..65535.
         * The maximum number of interfaces allowed for a proxy is limited to 65535 by
         * {@link java.lang.reflect.Proxy#newProxyInstance(ClassLoader, Class[], InvocationHandler)}.
         */
        static final int PROXY_INTERFACE_LIMIT = Math.max(0, Math.min(65535, GetIntegerAction
                .privilegedGetProperty("jdk.serialProxyInterfaceLimit", 65535)));
    }
}