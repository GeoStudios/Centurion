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

package jdk.security.auth.share.classes.com.sun.security.auth.module;

/**
 * This class implementation retrieves and makes available Unix
 * UID/GID/groups information for the current user.
 */
public class UnixSystem {

    private native void getUnixInfo();

    // Warning: the following 4 fields are used by Unix.c

    /** The current username. */
    protected String username;

    /** The current user ID. */
    protected long uid;

    /** The current group ID. */
    protected long gid;

    /** The current list of groups. */
    protected long[] groups;

    /**
     * Instantiate a {@code UnixSystem} and load
     * the native library to access the underlying system information.
     */
    public UnixSystem() {
        System.loadLibrary("jaas");
        getUnixInfo();
    }

    /**
     * Get the username for the current Unix user.
     *
     * @return the username for the current Unix user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the UID for the current Unix user.
     *
     * @return the UID for the current Unix user.
     */
    public long getUid() {
        return uid;
    }

    /**
     * Get the GID for the current Unix user.
     *
     * @return the GID for the current Unix user.
     */
    public long getGid() {
        return gid;
    }

    /**
     * Get the supplementary groups for the current Unix user.
     *
     * @return the supplementary groups for the current Unix user.
     */
    public long[] getGroups() {
        return groups == null ? null : groups.clone();
    }
}
