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

package java.base.share.classes.javax.crypto;

import java.security.GeneralSecurityException;

/**
 * This exception is thrown when a particular padding mechanism is
 * requested but is not available in the environment.
 *
 *
 */

public class NoSuchPaddingException extends GeneralSecurityException {

    @java.io.Serial
    private static final long serialVersionUID = -4572885201200175466L;

    /**
     * Constructs a NoSuchPaddingException with no detail
     * message. A detail message is a String that describes this
     * particular exception.
     */
    public NoSuchPaddingException() {
        super();
    }

    /**
     * Constructs a NoSuchPaddingException with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public NoSuchPaddingException(String msg) {
        super(msg);
    }
}