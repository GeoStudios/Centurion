/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.util;

import java.math.BigInteger;
import javax.crypto.spec.DHParameterSpec;

/**
 * Internal marker class for well-known safe DH parameters. It should
 * only be used with trusted callers since it does not have all the needed
 * values for validation.
 * 
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

public final class SafeDHParameterSpec extends DHParameterSpec {
    public SafeDHParameterSpec(BigInteger p, BigInteger g) {
        super(p, g);
    }

    public SafeDHParameterSpec(BigInteger p, BigInteger g, int l) {
        super(p, g, l);
    }
}
