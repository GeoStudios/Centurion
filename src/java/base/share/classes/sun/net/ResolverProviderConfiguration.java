/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package sun.net;

import java.net.spi.InetAddressResolver;
import java.net.spi.InetAddressResolverProvider;
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
