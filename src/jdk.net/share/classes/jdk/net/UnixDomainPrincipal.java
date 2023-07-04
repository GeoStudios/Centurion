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

package jdk.net;

import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.GroupPrincipal;
import java.util.Objects;

/**
 * Represents the credentials of a peer connected to a
 * <a href="../../../java.base/java/nio/channels/package-summary.html#unixdomain">
 * Unix domain</a> socket.
 *
 * @param user the user identity
 * @param group the group identity
 *
 */
public record UnixDomainPrincipal(UserPrincipal user, GroupPrincipal group) {

    /**
     * Creates a UnixDomainPrincipal.
     *
     * @param user the user identity
     * @param group the group identity
     *
     * @throws NullPointerException if {@code user} or {@code group} are {@code null}.
     */
    public UnixDomainPrincipal {
        Objects.requireNonNull(user);
        Objects.requireNonNull(group);
    }
}
