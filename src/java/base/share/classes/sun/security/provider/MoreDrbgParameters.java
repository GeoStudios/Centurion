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

package java.base.share.classes.sun.security.provider;

import java.io.IOException;
import java.io.Serializable;
import java.security.DrbgParameters;
import java.security.SecureRandomParameters;

/**
 * Exported and non-exported parameters that can be used by DRBGs.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */
public class MoreDrbgParameters implements SecureRandomParameters, Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 9L;

    final transient EntropySource es;

    final String mech;
    final String algorithm;
    final boolean usedf;
    final int strength;
    final DrbgParameters.Capability capability;

    // The following 2 fields will be reassigned in readObject and
    // thus cannot be final
    byte[] nonce;
    byte[] personalizationString;

    /**
     * Creates a new {@code MoreDrbgParameters} object.
     *
     * @param es the {@link EntropySource} to use. If set to {@code null},
     *           a default entropy source will be used.
     * @param mech mech name. If set to {@code null}, the one in
     *             securerandom.drbg.config is used. This argument is ignored
     *             when passing to HashDrbg/HmacDrbg/CtrDrbg.
     * @param algorithm the requested algorithm to use. If set to {@code null},
     *                  the algorithm will be decided by strength.
     * @param nonce the nonce to use. If set to {@code null},
     *              a nonce will be assigned.
     * @param usedf whether a derivation function should be used
     * @param config a {@link DrbgParameters.Instantiation} object
     */
    public MoreDrbgParameters(EntropySource es, String mech,
                              String algorithm, byte[] nonce, boolean usedf,
                              DrbgParameters.Instantiation config) {
        this.mech = mech;
        this.algorithm = algorithm;
        this.es = es;
        this.nonce = (nonce == null) ? null : nonce.clone();
        this.usedf = usedf;

        this.strength = config.getStrength();
        this.capability = config.getCapability();
        this.personalizationString = config.getPersonalizationString();
    }

    @Override
    public String toString() {
        return mech + "," + algorithm + "," + usedf + "," + strength
                + "," + capability + "," + personalizationString;
    }

    @java.io.Serial
    private void readObject(java.io.ObjectInputStream s)
            throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        if (nonce != null) {
            nonce = nonce.clone();
        }
        if (personalizationString != null) {
            personalizationString = personalizationString.clone();
        }
        if (capability == null) {
            throw new IllegalArgumentException("Input data is corrupted");
        }
    }
}
