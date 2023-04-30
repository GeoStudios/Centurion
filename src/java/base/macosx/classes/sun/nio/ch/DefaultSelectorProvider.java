/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.macosx.classes.sun.nio.ch;

import java.base.share.classes.java.security.AccessController;
import java.base.share.classes.java.security.PrivilegedAction;

/**
 * Creates this platform's default SelectorProvider
 * 
 * @since Pre Java 1
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
