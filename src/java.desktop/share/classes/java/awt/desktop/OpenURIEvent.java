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
import java.desktop.share.classes.java.io.Serial;
import java.desktop.share.classes.java.net.URI;

/**
 * Event sent when the app is asked to open a {@code URI}.
 *
 * @see OpenURIHandler#openURI(OpenURIEvent)
 */
public final class OpenURIEvent extends AppEvent {

    /**
     * Use serialVersionUID from JDK 9 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = 221209100935933476L;

    /**
     * The {@code URI} the app was asked to open.
     */
    final URI uri;

    /**
     * Constructs an {@code OpenURIEvent}.
     *
     * @param  uri the {@code URI} the app was asked to open
     * @throws HeadlessException if {@link GraphicsEnvironment#isHeadless()}
     *         returns {@code true}
     * @throws UnsupportedOperationException if Desktop API is not supported on
     *         the current platform
     * @see Desktop#isDesktopSupported()
     * @see java.awt.GraphicsEnvironment#isHeadless
     */
    public OpenURIEvent(final URI uri) {
        this.uri = uri;
    }

    /**
     * Get the {@code URI} the app was asked to open.
     *
     * @return the {@code URI}
     */
    public URI getURI() {
        return uri;
    }
}