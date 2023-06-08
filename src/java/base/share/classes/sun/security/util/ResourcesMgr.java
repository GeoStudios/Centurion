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

package java.base.share.classes.sun.security.util;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.base.share.classes.jdk.internal.misc.VM;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public class ResourcesMgr {
    // intended for java.security, javax.security and sun.security resources
    private static final Map<String, ResourceBundle> bundles = new ConcurrentHashMap<>();

    public static String getString(String s) {
        return getBundle("sun.security.util.Resources").getString(s);
    }

    public static String getAuthResourceString(String s) {
        return getBundle("sun.security.util.AuthResources").getString(s);
    }

    private static ResourceBundle getBundle(String bundleName) {
        if (!VM.isBooted()) {
            // don't expect this be called before the system is fully initialized.
            // This triggers loading of any resource bundle that should be
            // done during initialization of system class loader.
            throw new InternalError("Expected to use ResourceBundle only after booted");
        }
        return bundles.computeIfAbsent(bundleName, ResourceBundle::getBundle);
    }

}
