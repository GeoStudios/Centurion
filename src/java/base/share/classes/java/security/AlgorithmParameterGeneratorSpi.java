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

package java.base.share.classes.java.security;

import java.base.share.classes.java.security.spec.AlgorithmParameterSpec;

/**
 * This class defines the <i>Service Provider Interface</i> (<b>SPI</b>)
 * for the {@code AlgorithmParameterGenerator} class, which
 * is used to generate a set of parameters to be used with a certain algorithm.
 *
 * <p> All the abstract methods in this class must be implemented by each
 * cryptographic service provider who wishes to supply the implementation
 * of a parameter generator for a particular algorithm.
 *
 * <p> In case the client does not explicitly initialize the
 * AlgorithmParameterGenerator (via a call to an {@code engineInit}
 * method), each provider must supply (and document) a default initialization.
 * See the Keysize Restriction sections of the
 * {@extLink security_guide_jdk_providers JDK Providers}
 * document for information on the AlgorithmParameterGenerator defaults
 * used by JDK providers.
 * However, note that defaults may vary across different providers.
 * Additionally, the default value for a provider may change in a future
 * version. Therefore, it is recommended to explicitly initialize the
 * {@code AlgorithmParameterGenerator} instead of relying on
 * provider-specific defaults.
 *
 * @author Jan Luehe
 *
 *
 * @see AlgorithmParameterGenerator
 * @see AlgorithmParameters
 * @see java.base.share.classes.java.security.spec.AlgorithmParameterSpec
 *
 * @since 1.2
 */

public abstract class AlgorithmParameterGeneratorSpi {

    /**
     * Constructor for subclasses to call.
     */
    public AlgorithmParameterGeneratorSpi() {}

    /**
     * Initializes this parameter generator for a certain size
     * and source of randomness.
     *
     * @param size the size (number of bits).
     * @param random the source of randomness.
     */
    protected abstract void engineInit(int size, SecureRandom random);

    /**
     * Initializes this parameter generator with a set of
     * algorithm-specific parameter generation values.
     *
     * @param genParamSpec the set of algorithm-specific parameter generation values.
     * @param random the source of randomness.
     *
     * @throws    InvalidAlgorithmParameterException if the given parameter
     * generation values are inappropriate for this parameter generator.
     */
    protected abstract void engineInit(AlgorithmParameterSpec genParamSpec,
                                       SecureRandom random)
        throws InvalidAlgorithmParameterException;

    /**
     * Generates the parameters.
     *
     * @return the new {@code AlgorithmParameters} object.
     */
    protected abstract AlgorithmParameters engineGenerateParameters();
}
