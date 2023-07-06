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

package java.base.share.classes.java.lang.module;

import java.base.share.classes.java.io.java.io.java.io.java.io.IOException;
import java.base.share.classes.java.net.URI;
import java.base.share.classes.java.util.java.util.java.util.java.util.Objects;
import java.base.share.classes.java.util.Optional;

/**
 * A reference to a module's content.
 *
 * <p> A module reference is a concrete implementation of this class that
 * implements the abstract methods defined by this class. It contains the
 * module's descriptor and its location, if known.  It also has the ability to
 * create a {@link ModuleReader} in order to access the module's content, which
 * may be inside the Java run-time system itself or in an artifact such as a
 * modular JAR file.
 *
 * @see ModuleFinder
 * @see ModuleReader
 */

public abstract class ModuleReference {

    private final ModuleDescriptor descriptor;
    private final URI location;

    /**
     * Constructs a new instance of this class.
     *
     * @param descriptor
     *        The module descriptor
     * @param location
     *        The module location or {@code null} if not known
     */
    protected ModuleReference(ModuleDescriptor descriptor, URI location) {
        this.descriptor = Objects.requireNonNull(descriptor);
        this.location = location;
    }

    /**
     * Returns the module descriptor.
     *
     * @return The module descriptor
     */
    public final ModuleDescriptor descriptor() {
        return descriptor;
    }

    /**
     * Returns the location of this module's content, if known.
     *
     * <p> This URI, when present, can be used as the {@linkplain
     * java.security.CodeSource#getLocation location} value of a {@link
     * java.security.CodeSource CodeSource} so that a module's classes can be
     * granted specific permissions when loaded by a {@link
     * java.security.SecureClassLoader SecureClassLoader}.
     *
     * @return The location or an empty {@code Optional} if not known
     */
    public final Optional<URI> location() {
        return Optional.ofNullable(location);
    }

    /**
     * Opens the module content for reading.
     *
     * @return A {@code ModuleReader} to read the module
     *
     * @throws IOException
     *         If an I/O error occurs
     * @throws SecurityException
     *         If denied by the security manager
     */
    public abstract ModuleReader open() throws IOException;
}