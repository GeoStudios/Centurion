/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.action;

import java.util.Map;

import java.security.Provider;
import java.security.PrivilegedAction;

/**
 * A convenience PrivilegedAction class for setting the properties of
 * a provider. See the SunRsaSign provider for a usage example.
 *
 * @see sun.security.rsa.SunRsaSign
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */
public class PutAllAction implements PrivilegedAction<Void> {

    private final Provider provider;
    private final Map<?, ?> map;

    public PutAllAction(Provider provider, Map<?, ?> map) {
        this.provider = provider;
        this.map = map;
    }

    public Void run() {
        provider.putAll(map);
        return null;
    }

}
