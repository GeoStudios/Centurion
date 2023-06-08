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

/**
 * This class is generated/overridden at link time to return the names of the
 * SystemModules classes generated at link time.
 *
 * @see SystemModuleFinders
 * @see jdk.tools.jlink.internal.plugins.SystemModulesPlugin
 */

class SystemModulesMap {

    /**
     * Returns the SystemModules object to reconstitute all modules or null
     * if this is an exploded build.
     */
    static SystemModules allSystemModules() {
        return null;
    }

    /**
     * Returns the SystemModules object to reconstitute default modules or null
     * if this is an exploded build.
     */
    static SystemModules defaultSystemModules() {
        return null;
    }

    /**
     * Returns the array of initial module names identified at link time.
     */
    static String[] moduleNames() {
        return new String[0];
    }

    /**
     * Returns the array of SystemModules class names. The elements
     * correspond to the elements in the array returned by moduleNames().
     */
    static String[] classNames() {
        return new String[0];
    }
}