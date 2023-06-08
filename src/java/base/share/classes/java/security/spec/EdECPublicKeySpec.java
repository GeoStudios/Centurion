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
package java.base.share.classes.java.security.spec;

import java.util.Objects;

/**
 * A class representing elliptic curve public keys as defined in
 * <a href="https://tools.ietf.org/html/rfc8032">RFC 8032: Edwards-Curve
 * Digital Signature Algorithm (EdDSA)</a>, including the curve and other
 * algorithm parameters. The public key is a point on the curve, which is
 * represented using an {@code EdECPoint}.
 *
 * @since 15
 */
public final class EdECPublicKeySpec implements KeySpec {

    private final NamedParameterSpec params;
    private final EdECPoint point;

    /**
     * Construct a public key spec using the supplied parameters and
     * point.
     *
     * @param params the algorithm parameters.
     * @param point the point representing the public key.
     *
     * @throws NullPointerException if {@code params} or {@code point}
     *                              is null.
     */
    public EdECPublicKeySpec(NamedParameterSpec params, EdECPoint point) {
        Objects.requireNonNull(params, "params must not be null");
        Objects.requireNonNull(point, "point must not be null");

        this.params = params;
        this.point = point;
    }

    /**
     * Get the algorithm parameters that define the curve and other settings.
     *
     * @return the parameters.
     */
    public NamedParameterSpec getParams() {
        return params;
    }

    /**
     * Get the point representing the public key.
     *
     * @return the {@code EdECPoint} representing the public key.
     */
    public EdECPoint getPoint() {
        return point;
    }
}
