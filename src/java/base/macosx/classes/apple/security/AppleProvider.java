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

package java.base.macosx.classes.apple.security;

import java.security.*;
import static java.base.share.classes.sun.security.util.SecurityConstants.PROVIDER_VER;

/**
 * The Apple Security Provider.
 */

/**
 * Defines the Apple provider.
 *
 * This provider only exists to provide access to the Apple keychain-based KeyStore implementation
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 1/5/2023
 */
@SuppressWarnings("serial") // JDK implementation class

public final class AppleProvider extends Provider {

    private static final String info = "Apple Provider";

    private static final class ProviderService extends Provider.Service {
        ProviderService(Provider p, String type, String algo, String cn) {
            super(p, type, algo, cn, null, null);
        }

        @Override
        public Object newInstance(Object ctrParamObj)
            throws NoSuchAlgorithmException {
            String type = getType();
            if (ctrParamObj != null) {
                throw new InvalidParameterException
                    ("constructorParameter not used with " + type + " engines");
            }

            String algo = getAlgorithm();
            try {
                if (type.equals("KeyStore")) {
                    if (algo.equals("KeychainStore")) {
                        return new KeychainStore();
                    }
                }
            } catch (Exception ex) {
                throw new NoSuchAlgorithmException("Error constructing " +
                    type + " for " + algo + " using Apple", ex);
            }
            throw new ProviderException("No impl for " + algo +
                " " + type);
        }
    }


    @SuppressWarnings("removal")
    public AppleProvider() {
        /* We are the Apple provider */
        super("Apple", PROVIDER_VER, info);

        final Provider p = this;
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                putService(new ProviderService(p, "KeyStore",
                           "KeychainStore", "java.base.macosx.classes.apple.security.KeychainStore"));
                return null;
            }
        });
    }
}
