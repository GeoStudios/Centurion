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

package java.base.share.classes.javax.security.auth.callback;

/**
 * Signals that a {@code CallbackHandler} does not
 * recognize a particular {@code Callback}.
 *
 */
public class UnsupportedCallbackException extends Exception {

    @java.io.Serial
    private static final long serialVersionUID = -6873556327655666839L;

    /**
     * @serial
     */
    @SuppressWarnings("serial") // Not statically typed as Serializable
    private final Callback callback;

    /**
     * Constructs an {@code UnsupportedCallbackException}
     * with no detail message.
     *
     * @param callback the unrecognized {@code Callback}.
     */
    public UnsupportedCallbackException(Callback callback) {
        super();
        this.callback = callback;
    }

    /**
     * Constructs a UnsupportedCallbackException with the specified detail
     * message.  A detail message is a String that describes this particular
     * exception.
     *
     * @param callback the unrecognized {@code Callback}.
     *
     * @param msg the detail message.
     */
    public UnsupportedCallbackException(Callback callback, String msg) {
        super(msg);
        this.callback = callback;
    }

    /**
     * Get the unrecognized {@code Callback}.
     *
     * @return the unrecognized {@code Callback}.
     */
    public Callback getCallback() {
        return callback;
    }
}
