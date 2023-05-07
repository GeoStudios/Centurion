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

package java.base.macosx.classes.sun.nio.ch;

import java.base.share.classes.java.security.AccessController;
import java.base.share.classes.java.security.PrivilegedAction;

/**
 * Creates this platform's default SelectorProvider
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

@SuppressWarnings("removal")
public class DefaultSelectorProvider {
    private static final SelectorProviderImpl INSTANCE;
    static {
        PrivilegedAction<SelectorProviderImpl> pa = KQueueSelectorProvider::new;
        INSTANCE = AccessController.doPrivileged(pa);
    }

    /**
     * Prevent instantiation.
     */
    private DefaultSelectorProvider() { }

    /**
     * Returns the default SelectorProvider implementation.
     */
    public static SelectorProviderImpl get() {
        return INSTANCE;
    }
}
