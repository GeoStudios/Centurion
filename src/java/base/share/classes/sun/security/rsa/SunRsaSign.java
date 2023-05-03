/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.rsa;

import java.util.*;

import java.security.*;

import static java.base.share.classes.sun.security.util.SecurityConstants.PROVIDER_VER;

/**
 * Provider class for the RSA signature provider. Supports RSA keyfactory,
 * keypair generation, and RSA signatures.
 *
 * @since Java 2
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public final class SunRsaSign extends Provider {

    @java.io.Serial
    private static final long serialVersionUID = 866040293550393045L;

    @SuppressWarnings("removal")
    public SunRsaSign() {
        super("SunRsaSign", PROVIDER_VER, "Sun RSA signature provider");

        Provider p = this;
        Iterator<Provider.Service> serviceIter = new SunRsaSignEntries(p).iterator();

        if (System.getSecurityManager() == null) {
            putEntries(serviceIter);
        } else {
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                @Override
                public Void run() {
                    putEntries(serviceIter);
                    return null;
                }
            });
        }
    }
    void putEntries(Iterator<Provider.Service> i) {
        while (i.hasNext()) {
            putService(i.next());
        }
    }
}
