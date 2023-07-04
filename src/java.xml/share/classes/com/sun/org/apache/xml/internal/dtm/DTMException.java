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

package com.sun.org.apache.xml.internal.dtm;

/**
 * This class specifies an exceptional condition that occurred
 * in the DTM module.
 */
public class DTMException extends RuntimeException {
    static final long serialVersionUID = -775576419181334734L;

    /**
     * Create a new DTMException.
     *
     * @param message The error or warning message.
     */
    public DTMException(String message) {
        super(message);
    }

    /**
     * Create a new DTMException wrapping an existing exception.
     *
     * @param e The exception to be wrapped.
     */
    public DTMException(Throwable e) {
        super(e);
    }

    /**
     * Wrap an existing exception in a DTMException.
     *
     * <p>This is used for throwing processor exceptions before
     * the processing has started.</p>
     *
     * @param message The error or warning message, or null to
     *                use the message from the embedded exception.
     * @param e Any exception
     */
    public DTMException(String message, Throwable e) {
        super(message, e);
    }
    }
