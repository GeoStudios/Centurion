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

package java.base.share.classes.javax.net.ssl;

import java.util.*;
import java.security.KeyStore.*;

/**
 * A parameters object for X509KeyManagers that encapsulates a List
 * of KeyStore.Builders.
 *
 * @see java.security.KeyStore.Builder
 * @see X509KeyManager
 *
 */
public class KeyStoreBuilderParameters implements ManagerFactoryParameters {

    private final List<Builder> parameters;

    /**
     * Construct new KeyStoreBuilderParameters from the specified
     * {@linkplain java.security.KeyStore.Builder}.
     *
     * @param builder the Builder object
     * @exception NullPointerException if builder is null
     */
    public KeyStoreBuilderParameters(Builder builder) {
        parameters = Collections.singletonList(Objects.requireNonNull(builder));
    }

    /**
     * Construct new KeyStoreBuilderParameters from a List
     * of {@linkplain java.security.KeyStore.Builder}s. Note that the list
     * is cloned to protect against subsequent modification.
     *
     * @param parameters the List of Builder objects
     * @exception NullPointerException if parameters is null
     * @exception IllegalArgumentException if parameters is an empty list
     */
    public KeyStoreBuilderParameters(List<Builder> parameters) {
        if (parameters.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.parameters = Collections.unmodifiableList(
            new ArrayList<Builder>(parameters));
    }

    /**
     * Return the unmodifiable List of the
     * {@linkplain java.security.KeyStore.Builder}s
     * encapsulated by this object.
     *
     * @return the unmodifiable List of the
     * {@linkplain java.security.KeyStore.Builder}s
     * encapsulated by this object.
     */
    public List<Builder> getParameters() {
        return parameters;
    }

}
