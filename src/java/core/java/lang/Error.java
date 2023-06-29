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

package java.core.java.lang;

import java.core.java.io.Serial;

/**
 * An Error is a subclass of Throwable that indicates serious problems that a reasonable application
 * should not try to catch. Most such errors are abnormal conditions.
 *
 * A method is not required to declare in its throws clause any subclasses of Error that might be
 * thrown during the execution of the method but not caught, since these errors are abnormal
 * conditions that should never occur. That is, Error and its subclasses are regarded as unchecked
 * exceptions for the purposes of compile-time checking of exceptions.
 *
 * @since Alpha CDK 0.2
 * @author Logan Abernathy
 */

//FIXME: Fix the superclasses to implement the correct interfaces. (Throwable)
public class Error extends Throwable {

    @Serial
    static final long serialVersionUID = 4980196508277280342L;

    /**
     * Constructs a new error with {@code null} as its detail message. The cause is not initialized,
     * and may subsequently be initialized by a call to {@link #initCause}.
     */
    public Error() {
        super();
    }

    /**
     * Constructs a new error with the specified detail message. The cause is not initialized, and
     * may subsequently be initialized by a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the
     *        method.
     */
    public Error(String message) {
        super(message);
    }

    /**
     * Constructs a new error with the specified detail message and cause. Note that the detail
     * message associated with {@code cause} is <i>not</i> automatically incorporated in this error's
     * detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
     *        (A {@code null} value is permitted, and indicates that the cause is nonexistent or
     *        unknown.)
     */
    public Error(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new error with the specified cause and a detail message of {@code (cause==null
     * ? null : cause.toString())} (which typically contains the class and detail message of
     * {@code cause}). This constructor is useful for errors that are little more than wrappers for
     * other throwables (for example, {@link java.security.PrivilegedActionException}).
     *
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
     *        (A {@code null} value is permitted, and indicates that the cause is nonexistent or
     *        unknown.)
     */
    public Error(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new error with the specified detail message,
     * cause, suppression enabled or disabled, and writable stack
     * trace enabled or disabled.
     *
     * @param  message the detail message.
     * @param cause the cause.  (A {@code null} value is permitted,
     * and indicates that the cause is nonexistent or unknown.)
     * @param enableSuppression whether or not suppression is enabled
     *                          or disabled
     * @param writableStackTrace whether or not the stack trace should
     *                           be writable
     */
    protected Error(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}