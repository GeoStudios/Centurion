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

package com.sun.tools.jdeps;

/**
 * Signals that an exception of some sort has occurred while processing
 * a multi-release jar file.
 *
 */
class MultiReleaseException extends RuntimeException {
    private static final long serialVersionUID = 4474870142461654108L;
    private final String key;
    private final Object[] params;

    /**
     * Constructs an {@code MultiReleaseException} with the specified detail
     * error message array.
     *
     * @param key
     *        The key that identifies the message in the jdeps.properties file
     * @param params
     *        The detail message array
     */
    public MultiReleaseException(String key, Object... params) {
        super();
        this.key = key;
        this.params = params;
    }

    /**
     * Returns the resource message key
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns the detailed error message array.
     *
     * @return the detailed error message array
     */
    public Object[] getParams() {
        return params;
    }
}
