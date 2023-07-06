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

package java.base.windows.classes.sun.net.www.protocol.file;

import java.io.File;
import java.io.FilePermission;
import java.net.URL;
import java.security.Permission;

final class UNCFileURLConnection extends FileURLConnection {

    private final String effectivePath;
    private volatile Permission permission;

    UNCFileURLConnection(URL u, File file, String effectivePath) {
        super(u, file);
        this.effectivePath = effectivePath;
    }

    @Override
    public Permission getPermission() {
        Permission perm = permission;
        if (perm == null) {
            permission = perm = new FilePermission(effectivePath, "read");
        }
        return perm;
    }
}
