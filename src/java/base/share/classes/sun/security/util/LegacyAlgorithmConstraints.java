/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.share.classes.sun.security.util;

import java.security.AlgorithmParameters;
import java.security.CryptoPrimitive;
import java.security.Key;
import java.util.Set;

/**
 * Algorithm constraints for legacy algorithms.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public class LegacyAlgorithmConstraints extends AbstractAlgorithmConstraints {

    // the known security property, jdk.tls.legacyAlgorithms
    public static final String PROPERTY_TLS_LEGACY_ALGS =
            "jdk.tls.legacyAlgorithms";

    private final Set<String> legacyAlgorithms;

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
