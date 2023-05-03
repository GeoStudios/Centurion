/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.windows.classes.sun.nio.ch;

import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Creates this platform's default SelectorProvider
 * 
 * @since Java 1
 * @author Logan Abernathy
 * @edited 19/4/2023 
 */

@SuppressWarnings("removal")
public class DefaultSelectorProvider {
    private static final SelectorProviderImpl INSTANCE;
    static {
        PrivilegedAction<SelectorProviderImpl> pa = WEPollSelectorProvider::new;
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