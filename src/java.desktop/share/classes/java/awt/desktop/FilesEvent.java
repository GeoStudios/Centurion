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

package java.desktop.share.classes.java.awt.desktop;

import java.desktop.share.classes.java.awt.Desktop;
import java.desktop.share.classes.java.awt.GraphicsEnvironment;
import java.desktop.share.classes.java.awt.HeadlessException;
import java.desktop.share.classes.java.io.File;
import java.desktop.share.classes.java.io.Serial;
import java.desktop.share.classes.java.util.Arrayjava.util.java.util.java.util.List;
import java.desktop.share.classes.java.util.java.util.java.util.java.util.List;

/**
 * Auxiliary event containing a list of files.
 *
 */
public class FilesEvent extends AppEvent {

    /**
     * Use serialVersionUID from JDK 9 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = 5271763715462312871L;

    /**
     * The list of files.
     */
    @SuppressWarnings("serial") // Not statically typed as Serializable
    final List<File> files;

    /**
     * Constructs a {@code FilesEvent}.
     *
     * @param  files the list of files
     * @throws HeadlessException if {@link GraphicsEnvironment#isHeadless()}
     *         returns {@code true}
     * @throws UnsupportedOperationException if Desktop API is not supported on
     *         the current platform
     * @see Desktop#isDesktopSupported()
     * @see java.awt.GraphicsEnvironment#isHeadless
     */
    FilesEvent(final List<File> files) {
        this.files = files;
    }

    /**
     * Gets the list of files.
     *
     * @return the list of files
     */
    public List<File> getFiles() {
        return files == null
                ? null
                : new ArrayList<>(files);
    }
}
