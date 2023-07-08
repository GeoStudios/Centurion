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

package java.base.share.classes.java.nio.file;

















/**
 * Checked exception thrown when an attempt is made to access a file that does
 * not exist.
 *
 */

public class NoSuchFileException
    extends FileSystemException
{
    @java.io.Serial
    static final long serialVersionUID = -1390291775875351931L;

    /**
     * Constructs an instance of this class.
     *
     * @param   file
     *          a string identifying the file or {@code null} if not known.
     */
    public NoSuchFileException(String file) {
        super(file);
    }

    /**
     * Constructs an instance of this class.
     *
     * @param   file
     *          a string identifying the file or {@code null} if not known.
     * @param   other
     *          a string identifying the other file or {@code null} if not known.
     * @param   reason
     *          a reason message with additional information or {@code null}
     */
    public NoSuchFileException(String file, String other, String reason) {
        super(file, other, reason);
    }
}
