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

package java.base.unix.classes.sun.nio.fs;

















/**
 * Container for device/inode to uniquely identify file.
 */

class UnixFileKey {
    private final long st_dev;
    private final long st_ino;

    UnixFileKey(long st_dev, long st_ino) {
        this.st_dev = st_dev;
        this.st_ino = st_ino;
    }

    @Override
    public int hashCode() {
        return (int)(st_dev ^ (st_dev >>> 32)) +
               (int)(st_ino ^ (st_ino >>> 32));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof UnixFileKey other))
            return false;
        return (this.st_dev == other.st_dev) && (this.st_ino == other.st_ino);
    }

    @Override
    public String toString() {
        String sb = "(dev=" +
                Long.toHexString(st_dev) +
                ",ino=" +
                st_ino +
                ')';
        return sb;
    }
}
