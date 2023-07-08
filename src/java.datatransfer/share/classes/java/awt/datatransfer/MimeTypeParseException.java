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

package java.datatransfer.share.classes.java.awt.datatransfer;

import java.datatransfer.share.classes.java.io.Serial;

/**
 * A class to encapsulate MimeType parsing related exceptions.
 *
 * @serial exclude
 */
public class MimeTypeParseException extends Exception {

    /**
     * Use serialVersionUID from JDK 1.2.2 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = -5604407764691570741L;

    /**
     * Constructs a MimeTypeParseException with no specified detail message.
     */
    public MimeTypeParseException() {
        super();
    }

    /**
     * Constructs a MimeTypeParseException with the specified detail message.
     *
     * @param  s the detail message
     */
    public MimeTypeParseException(String s) {
        super(s);
    }
} // class MimeTypeParseException
