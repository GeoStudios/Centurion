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

package java.base.share.classes.sun.net;

import java.base.share.classes.java.net.spi.InetAddressResolver;
import java.base.share.classes.java.net.spi.InetAddressResolverProvider;
import java.util.function.Supplier;

public final class ResolverProviderConfiguration implements
        InetAddressResolverProvider.Configuration {

    private final InetAddressResolver builtinResolver;
    private final Supplier<String> localHostNameSupplier;

    public ResolverProviderConfiguration(InetAddressResolver builtinResolver,
                                         Supplier<String> localHostNameSupplier) {
        this.builtinResolver = builtinResolver;
        this.localHostNameSupplier = localHostNameSupplier;
    }

    @Override
    public InetAddressResolver builtinResolver() {
        return builtinResolver;
    }

    @Override
    public String lookupLocalHostName() {
        return localHostNameSupplier.get();
    }
}
