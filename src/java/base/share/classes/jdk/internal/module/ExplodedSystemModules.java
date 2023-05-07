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
import java.util.Map;
import java.util.Set;

/**
 * A dummy SystemModules for use with exploded builds or testing.
 */

class ExplodedSystemModules implements SystemModules {
    @Override
    public boolean hasSplitPackages() {
        return true;  // not known
    }

    @Override
    public boolean hasIncubatorModules() {
        return true;  // not known
    }

    @Override
    public ModuleDescriptor[] moduleDescriptors() {
        throw new InternalError();
    }

    @Override
    public ModuleTarget[] moduleTargets() {
        throw new InternalError();
    }

    @Override
    public ModuleHashes[] moduleHashes() {
        throw new InternalError();
    }

    @Override
    public ModuleResolution[] moduleResolutions() {
        throw new InternalError();
    }

    @Override
    public Map<String, Set<String>> moduleReads() {
        throw new InternalError();
    }
}
