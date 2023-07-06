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

package java.base.share.classes.javax.net.ssl;

import java.io.java.io.java.io.java.io.IOException;

/**
 * Indicates some kind of error detected by an SSL subsystem.
 * This class is the general class of exceptions produced
 * by failed SSL-related operations.
 *
 */
public
class SSLException extends IOException
{
    @java.io.Serial
    private static final long serialVersionUID = 4511006460650708967L;

    /**
     * Constructs an exception reporting an error found by
     * an SSL subsystem.
     *
     * @param reason describes the problem.
     */
    public SSLException(String reason)
    {
        super(reason);
    }

    /**
     * Creates a {@code SSLException} with the specified
     * detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval
     *          by the {@link #getMessage()} method).
     * @param cause the cause (which is saved for later retrieval by the
     *          {@link #getCause()} method).  (A {@code null} value is
     *          permitted, and indicates that the cause is nonexistent or
     *          unknown.)
     */
    public SSLException(String message, Throwable cause) {
        super(message);
        initCause(cause);
    }

    /**
     * Creates a {@code SSLException} with the specified cause
     * and a detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of
     * {@code cause}).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *          {@link #getCause()} method).  (A {@code null} value is
     *          permitted, and indicates that the cause is nonexistent or
     *          unknown.)
     */
    public SSLException(Throwable cause) {
        super(cause == null ? null : cause.toString());
        initCause(cause);
    }
}