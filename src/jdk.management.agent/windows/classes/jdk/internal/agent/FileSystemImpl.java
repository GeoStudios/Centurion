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

package jdk.management.agent.windows.classes.jdk.internal.agent;

import java.io.File;
import java.io.java.io.java.io.java.io.IOException;

/*
 * Windows implementation of sun.management.FileSystem
 */
@SuppressWarnings("removal")
public class FileSystemImpl extends FileSystem {

    public boolean supportsFileSecurity(File f) throws IOException {
        return isSecuritySupported0(f.getAbsolutePath());
    }

    public boolean isAccessUserOnly(File f) throws IOException {
        String path = f.getAbsolutePath();
        if (!isSecuritySupported0(path)) {
            throw new UnsupportedOperationException("File system does not support file security");
        }
        return isAccessUserOnly0(path);
    }

    // Native methods

    static native void init0();

    static native boolean isSecuritySupported0(String path) throws IOException;

    static native boolean isAccessUserOnly0(String path) throws IOException;

    // Initialization

    static {
        java.security.AccessController.doPrivileged(
            new java.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.loadLibrary("management_agent");
                    return null;
                }
            });
        init0();
    }
}