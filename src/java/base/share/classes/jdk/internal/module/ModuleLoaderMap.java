/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.jdk.internal.module;

import java.lang.module.Configuration;
import java.lang.module.ResolvedModule;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import jdk.internal.loader.ClassLoaders;

/**
 * Supports the mapping of modules to class loaders. The set of modules mapped
 * to the boot and platform class loaders is generated at build time from
 * this source file.
 */
public final class ModuleLoaderMap {

    /**
     * Maps the system modules to the built-in class loaders.
     */
    private static final class Mapper implements Function<String, ClassLoader> {

        private static final ClassLoader PLATFORM_CLASSLOADER =
                ClassLoaders.platformClassLoader();
        private static final ClassLoader APP_CLASSLOADER =
                ClassLoaders.appClassLoader();

        private static final Integer PLATFORM_LOADER_INDEX = 1;
        private static final Integer APP_LOADER_INDEX      = 2;

        /**
         * Map from module to a class loader index. The index is resolved to the
         * actual class loader in {@code apply}.
         */
        private final Map<String, Integer> map;

        /**
         * Creates a Mapper to map module names in the given Configuration to
         * built-in classloaders.
         *
         * As a proxy for the actual classloader, we store an easily archiveable
         * index value in the internal map. The index is stored as a boxed value
         * so that we can cheaply do identity comparisons during bootstrap.
         */
        Mapper(Configuration cf) {
            var map = new HashMap<String, Integer>();
            for (ResolvedModule resolvedModule : cf.modules()) {
                String mn = resolvedModule.name();
                if (!Modules.bootModules.contains(mn)) {
                    if (Modules.platformModules.contains(mn)) {
                        map.put(mn, PLATFORM_LOADER_INDEX);
                    } else {
                        map.put(mn, APP_LOADER_INDEX);
                    }
                }
            }
            this.map = map;
        }

        @Override
        public ClassLoader apply(String name) {
            Integer loader = map.get(name);
            if (loader == APP_LOADER_INDEX) {
                return APP_CLASSLOADER;
            } else if (loader == PLATFORM_LOADER_INDEX) {
                return PLATFORM_CLASSLOADER;
            } else { // BOOT_LOADER_INDEX
                return null;
            }
        }
    }

    /**
     * Returns the names of the modules defined to the boot loader.
     */
    public static Set<String> bootModules() {
        return Modules.bootModules;
    }

    /**
     * Returns the names of the modules defined to the platform loader.
     */
    public static Set<String> platformModules() {
        return Modules.platformModules;
    }

    private static class Modules {
        // list of boot modules is generated at build time.
        private static final Set<String> bootModules =
                Set.of(new String[] { "@@BOOT_MODULE_NAMES@@" });

        // list of platform modules is generated at build time.
        private static final Set<String> platformModules =
                Set.of(new String[] { "@@PLATFORM_MODULE_NAMES@@" });
    }

    /**
     * Returns a function to map modules in the given configuration to the
     * built-in class loaders.
     */
    static Function<String, ClassLoader> mappingFunction(Configuration cf) {
        return new Mapper(cf);
    }

    /**
     * When defining modules for a configuration, we only allow defining modules
     * to the boot or platform classloader if the ClassLoader mapping function
     * originate from here.
     */
    public static boolean isBuiltinMapper(Function<String, ClassLoader> clf) {
        return clf instanceof Mapper;
    }
}
