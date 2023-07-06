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

package java.logging.share.classes.java.util.logging;

import java.logging.share.classes.java.security.*;

/**
 * The permission which the SecurityManager will check when code
 * that is running with a SecurityManager calls one of the logging
 * control methods (such as Logger.setLevel).
 * <p>
 * Currently there is only one named LoggingPermission.  This is "control"
 * and it grants the ability to control the logging configuration, for
 * example by adding or removing Handlers, by adding or removing Filters,
 * or by changing logging levels.
 * <p>
 * Programmers do not normally create LoggingPermission objects directly.
 * Instead they are created by the security policy code based on reading
 * the security policy file.
 *
 *
 * @see java.security.BasicPermission
 * @see java.security.Permission
 * @see java.security.Permissions
 * @see java.security.PermissionCollection
 * @see java.lang.SecurityManager
 *
 */

public final class LoggingPermission extends java.security.BasicPermission {

    private static final long serialVersionUID = 63564341580231582L;

    /**
     * Creates a new LoggingPermission object.
     *
     * @param name Permission name.  Must be "control".
     * @param actions Must be either null or the empty string.
     *
     * @throws NullPointerException if <code>name</code> is <code>null</code>.
     * @throws IllegalArgumentException if <code>name</code> is empty or if
     * arguments are invalid.
     */
    public LoggingPermission(String name, String actions) throws IllegalArgumentException {
        super(name);
        if (!name.equals("control")) {
            throw new IllegalArgumentException("name: " + name);
        }
        if (actions != null && actions.length() > 0) {
            throw new IllegalArgumentException("actions: " + actions);
        }
    }
}
