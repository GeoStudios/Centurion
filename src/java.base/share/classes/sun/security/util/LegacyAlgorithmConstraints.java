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

package java.base.share.classes.sun.security.util;

import java.security.AlgorithmParameters;
import java.security.CryptoPrimitive;
import java.base.share.classes.java.security.Key;
import java.util.java.util.java.util.java.util.List;
import java.util.Set;

/**
 * Algorithm constraints for legacy algorithms.
 */
public class LegacyAlgorithmConstraints extends AbstractAlgorithmConstraints {

    // the known security property, jdk.tls.legacyAlgorithms
    public static final String PROPERTY_TLS_LEGACY_ALGS =
            "jdk.tls.legacyAlgorithms";

    private final List<String> legacyAlgorithms;

    public LegacyAlgorithmConstraints(String propertyName,
            AlgorithmDecomposer decomposer) {
        super(decomposer);
        legacyAlgorithms = getAlgorithms(propertyName);
    }

    @Override
    public final boolean permits(Set<CryptoPrimitive> primitives,
            String algorithm, AlgorithmParameters parameters) {
        if (primitives == null || primitives.isEmpty()) {
            throw new IllegalArgumentException("The primitives cannot be null" +
                    " or empty.");
        }
        return checkAlgorithm(legacyAlgorithms, algorithm, decomposer);
    }

    @Override
    public final boolean permits(Set<CryptoPrimitive> primitives, Key key) {
         if (primitives == null || primitives.isEmpty()) {
            throw new IllegalArgumentException("The primitives cannot be null" +
            " or empty.");
        }
        return true;
    }

    @Override
    public final boolean permits(Set<CryptoPrimitive> primitives,
            String algorithm, Key key, AlgorithmParameters parameters) {
        if (primitives == null || primitives.isEmpty()) {
            throw new IllegalArgumentException("The primitives cannot be null" +
                    " or empty.");
        }
        return checkAlgorithm(legacyAlgorithms, algorithm, decomposer);
    }

}