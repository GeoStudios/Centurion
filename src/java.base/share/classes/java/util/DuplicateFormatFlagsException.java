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
 * Unchecked exception thrown when duplicate flags are provided in the format
 * specifier.
 *
 * <p> Unless otherwise specified, passing a {@code null} argument to any
 * method or constructor in this class will cause a {@link
 * NullPointerException} to be thrown.
 *
 */
public class DuplicateFormatFlagsException extends IllegalFormatException {

    @java.io.Serial
    private static final long serialVersionUID = 18890531L;

    private final String flags;

    /**
     * Constructs an instance of this class with the specified flags.
     *
     * @param  f
     *         The set of format flags which contain a duplicate flag.
     */
    public DuplicateFormatFlagsException(String f) {
        if (f == null)
            throw new NullPointerException();
        this.flags = f;
    }

    /**
     * Returns the set of flags which contains a duplicate flag.
     *
     * @return  The flags
     */
    public String getFlags() {
        return flags;
    }

    public String getMessage() {
        return String.format("Flags = '%s'", flags);
    }
}