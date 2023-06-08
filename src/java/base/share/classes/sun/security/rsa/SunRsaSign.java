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

package java.base.share.classes.sun.security.rsa;

import java.util.*;

import java.security.*;

import static java.base.share.classes.sun.security.util.SecurityConstants.PROVIDER_VER;

/**
 * Provider class for the RSA signature provider. Supports RSA keyfactory,
 * keypair generation, and RSA signatures.
 *
 * @since Alpha cdk-1.1
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
