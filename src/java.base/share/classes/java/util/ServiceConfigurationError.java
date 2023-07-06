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

package java.base.share.classes.java.util;

/**
 * Error thrown when something goes wrong while locating, loading, or
 * instantiating a service provider.
 *
 * @see ServiceLoader
 */

public class ServiceConfigurationError
    extends Error
{

    @java.io.Serial
    private static final long serialVersionUID = 74132770414881L;

    /**
     * Constructs a new instance with the specified message.
     *
     * @param  msg  The message, or {@code null} if there is no message
     *
     */
    public ServiceConfigurationError(String msg) {
        super(msg);
    }

    /**
     * Constructs a new instance with the specified message and cause.
     *
     * @param  msg  The message, or {@code null} if there is no message
     *
     * @param  cause  The cause, or {@code null} if the cause is nonexistent
     *                or unknown
     */
    public ServiceConfigurationError(String msg, Throwable cause) {
        super(msg, cause);
    }

}