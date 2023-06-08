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
