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
package java.base.share.classes.java.security.interfaces;

import java.security.spec.AlgorithmParameterSpec;

/**
 * An interface for an elliptic curve public/private key as defined by
 * RFC 7748. These keys are distinct from the keys represented by
 * {@code ECKey}, and they are intended for use with algorithms based on RFC
 * 7748 such as the XDH {@code KeyAgreement} algorithm. This interface allows
 * access to the algorithm parameters associated with the key.
 *
 * @since 11
 */
public interface XECKey {
    /**
     * Returns the algorithm parameters associated
     * with the key.
     *
     * @return the associated algorithm parameters
     */
    AlgorithmParameterSpec getParams();
}

