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
 * This file is available under and governed by the GNU General Public
 * License version 2 only, as published by the Free Software Foundation.
 * However, the following notice accompanied the original version of this
 * file, and Oracle licenses the original version of this file under the BSD
 * license:
 */
package jdk.dynalink.beans;

import java.lang.reflect.Modifier;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import jdk.dynalink.internal.AccessControlContextFactory;

/**
 * A utility class to check whether a given class is in a package with restricted access e.g. "sun.*" etc.
 */
class CheckRestrictedPackage {
    @SuppressWarnings("removal")
    private static final AccessControlContext NO_PERMISSIONS_CONTEXT =
            AccessControlContextFactory.createAccessControlContext();

    /**
     * Returns true if the class is either not public, or it resides in a package with restricted access.
     * @param clazz the class to test
     * @return true if the class is either not public, or it resides in a package with restricted access.
     */
    @SuppressWarnings("removal")
    static boolean isRestrictedClass(final Class<?> clazz) {
        if(!Modifier.isPublic(clazz.getModifiers())) {
            // Non-public classes are always restricted
            return true;
        }
        final String name = clazz.getName();
        final int i = name.lastIndexOf('.');
        if (i == -1) {
            // Classes in default package are never restricted
            return false;
        }
        final String pkgName = name.substring(0, i);
        final Module module = clazz.getModule();
        if (module != null && !module.isExported(pkgName)) {
            // Classes in unexported packages of modules are always restricted
            return true;
        }

        final SecurityManager sm = System.getSecurityManager();
        if(sm == null) {
            // No further restrictions if we don't have a security manager
            return false;
        }
        // Do a package access check from within an access control context with no permissions
        try {
            AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
                sm.checkPackageAccess(pkgName);
                return null;
            }, NO_PERMISSIONS_CONTEXT);
        } catch(final SecurityException e) {
            return true;
        }
        return false;
    }
}
