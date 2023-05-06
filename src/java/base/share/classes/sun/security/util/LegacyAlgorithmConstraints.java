/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
