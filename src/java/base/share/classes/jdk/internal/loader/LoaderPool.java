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

package java.base.share.classes.jdk.internal.loader;

import java.lang.module.Configuration;
import java.lang.module.ResolvedModule;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * A pool of class loaders.
 *
 * @see ModuleLayer#defineModulesWithManyLoaders
 */

public final class LoaderPool {

    // maps module names to class loaders
    private final Map<String, Loader> loaders;


    /**
     * Creates a pool of class loaders. Each module in the given configuration
     * is mapped to its own class loader in the pool. The class loader is
     * created with the given parent class loader as its parent.
     */
    public LoaderPool(Configuration cf,
                      List<ModuleLayer> parentLayers,
                      ClassLoader parentLoader)
    {
        Map<String, Loader> loaders = new HashMap<>();
        for (ResolvedModule resolvedModule : cf.modules()) {
            Loader loader = new Loader(resolvedModule, this, parentLoader);
            String mn = resolvedModule.name();
            loaders.put(mn, loader);
        }
        this.loaders = loaders;

        // complete the initialization
        loaders.values().forEach(l -> l.initRemotePackageMap(cf, parentLayers));
    }


    /**
     * Returns the class loader for the named module
     */
    public Loader loaderFor(String name) {
        Loader loader = loaders.get(name);
        assert loader != null;
        return loader;
    }

    /**
     * Returns a stream of the loaders in this pool.
     */
    public Stream<Loader> loaders() {
        return loaders.values().stream();
    }

}
