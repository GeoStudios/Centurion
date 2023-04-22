/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.provider;

import java.util.*;

import java.security.*;

import java.base.share.classes.sun.security.rsa.SunRsaSignEntries;
import static java.base.share.classes.sun.security.util.SecurityConstants.PROVIDER_VER;

/**
 * Provider used for verification of signed JAR files *if* the Sun and
 * SunRsaSign main classes have been removed. Otherwise, this provider is not
 * necessary and registers no algorithms. This functionality only exists to
 * support a use case required by a specific customer and is not generally
 * supported.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */
public final class VerificationProvider extends Provider {

    @java.io.Serial
    private static final long serialVersionUID = 7482667077568930381L;

    private static final boolean ACTIVE;

    static {
        boolean b;
        try {
            Class.forName("sun.security.provider.Sun");
            Class.forName("sun.security.rsa.SunRsaSign");
            b = false;
        } catch (ClassNotFoundException e) {
            b = true;
        }
        ACTIVE = b;
    }

    @SuppressWarnings("removal")
    public VerificationProvider() {
        super("SunJarVerification", PROVIDER_VER, "Jar Verification Provider");
        // register all algorithms normally registered by the Sun and SunRsaSign
        // providers, but only if they are missing
        if (ACTIVE == false) {
            return;
        }

        Provider p = this;
        Iterator<Provider.Service> sunIter = new SunEntries(p).iterator();
        Iterator<Provider.Service> rsaIter =
            new SunRsaSignEntries(p).iterator();

        // if there is no security manager installed, put directly into
        // the provider
        if (System.getSecurityManager() == null) {
            putEntries(sunIter);
            putEntries(rsaIter);
        } else {
            AccessController.doPrivileged(new PrivilegedAction<Object>() {
                public Void run() {
                    putEntries(sunIter);
                    putEntries(rsaIter);
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
