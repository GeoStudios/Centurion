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

import java.security.*;

/**
 * An interface to an object capable of generating DSA key pairs.
 *
 * <p>The {@code initialize} methods may each be called any number
 * of times. If no {@code initialize} method is called on a
 * DSAKeyPairGenerator, each provider that implements this interface
 * should supply (and document) a default initialization. Note that
 * defaults may vary across different providers. Additionally, the default
 * value for a provider may change in a future version. Therefore, it is
 * recommended to explicitly initialize the DSAKeyPairGenerator instead
 * of relying on provider-specific defaults.
 *
 * <p>Users wishing to indicate DSA-specific parameters, and to generate a key
 * pair suitable for use with the DSA algorithm typically
 *
 * <ol>
 *
 * <li>Get a key pair generator for the DSA algorithm by calling the
 * KeyPairGenerator {@code getInstance} method with "DSA"
 * as its argument.
 *
 * <li>Check if the returned key pair generator is an instance of
 * DSAKeyPairGenerator before casting the result to a DSAKeyPairGenerator
 * and calling one of the {@code initialize} methods from this
 * DSAKeyPairGenerator interface.
 *
 * <li>Generate a key pair by calling the {@code generateKeyPair}
 * method of the KeyPairGenerator class.
 *
 * </ol>
 *
 * <p>Note: it is not always necessary to do algorithm-specific
 * initialization for a DSA key pair generator. That is, it is not always
 * necessary to call an {@code initialize} method in this interface.
 * Algorithm-independent initialization using the {@code initialize} method
 * in the KeyPairGenerator
 * interface is all that is needed when you accept defaults for algorithm-specific
 * parameters.
 *
 * <p>Note: Some earlier implementations of this interface may not support
 * larger values of DSA parameters such as 3072-bit.
 *
 * @since 1.1
 * @see java.security.KeyPairGenerator
 */
public interface DSAKeyPairGenerator {

    /**
     * Initializes the key pair generator using the DSA family parameters
     * (p,q and g) and an optional SecureRandom bit source. If a
     * SecureRandom bit source is needed but not supplied, i.e. null, a
     * default SecureRandom instance will be used.
     *
     * @param params the parameters to use to generate the keys.
     *
     * @param random the random bit source to use to generate key bits;
     * can be null.
     *
     * @throws    InvalidParameterException if the {@code params}
     * value is invalid, null, or unsupported.
     */
   void initialize(DSAParams params, SecureRandom random);

    /**
     * Initializes the key pair generator for a given modulus length
     * (instead of parameters), and an optional SecureRandom bit source.
     * If a SecureRandom bit source is needed but not supplied, i.e.
     * null, a default SecureRandom instance will be used.
     *
     * <p>If {@code genParams} is true, this method generates new
     * p, q and g parameters. If it is false, the method uses precomputed
     * parameters for the modulus length requested. If there are no
     * precomputed parameters for that modulus length, an exception will be
     * thrown.
     *
     * @param modlen the modulus length in bits. Valid values are any
     * multiple of 64 between 512 and 1024, inclusive, 2048, and 3072.
     *
     * @param random the random bit source to use to generate key bits;
     * can be null.
     *
     * @param genParams whether to generate new parameters for
     * the modulus length requested.
     *
     * @throws    InvalidParameterException if {@code modlen} is
     * invalid, or unsupported, or if {@code genParams} is false and there
     * are no precomputed parameters for the requested modulus length.
     */
    void initialize(int modlen, boolean genParams, SecureRandom random);
}
