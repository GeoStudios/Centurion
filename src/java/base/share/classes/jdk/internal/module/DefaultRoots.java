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

package java.base.share.classes.jdk.internal.module;

import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Defines methods to compute the default set of root modules for the unnamed
 * module.
 */

public final class DefaultRoots {
    private DefaultRoots() { }

    /**
     * Returns the default set of root modules for the unnamed module from the
     * modules observable with the intersection of two module finders.
     *
     * The first module finder should be the module finder that finds modules on
     * the upgrade module path or among the system modules. The second module
     * finder should be the module finder that finds all modules on the module
     * path, or a subset of when using --limit-modules.
     */
    static Set<String> compute(ModuleFinder finder1, ModuleFinder finder2) {
        return finder1.findAll().stream()
                .filter(mref -> !ModuleResolution.doNotResolveByDefault(mref))
                .map(ModuleReference::descriptor)
                .filter(descriptor -> finder2.find(descriptor.name()).isPresent()
                                      && exportsAPI(descriptor))
                .map(ModuleDescriptor::name)
                .collect(Collectors.toSet());
    }

    /**
     * Returns the default set of root modules for the unnamed module from the
     * modules observable with the given module finder.
     *
     * This method is used by the jlink system modules plugin.
     */
    public static Set<String> compute(ModuleFinder finder) {
        return compute(finder, finder);
    }

    /**
     * Returns true if the given module exports a package to all modules
     */
    private static boolean exportsAPI(ModuleDescriptor descriptor) {
        return descriptor.exports()
                .stream()
                .filter(e -> !e.isQualified())
                .findAny()
                .isPresent();
    }
}
