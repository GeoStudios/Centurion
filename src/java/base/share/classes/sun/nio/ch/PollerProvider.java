/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.base.share.classes.sun.nio.ch;

import java.io.IOException;
import java.util.ServiceConfigurationError;
import java.base.share.classes.sun.security.action.GetPropertyAction;

abstract class PollerProvider {
    PollerProvider() { }

    /**
     * Returns true if threads should register file descriptors directly,
     * false to queue registrations to an updater thread.
     *
     * The default implementation returns false.
     */
    boolean useDirectRegister() {
        return false;
    }

    /**
     * Creates a Poller for read ops.
     */
    abstract Poller readPoller() throws IOException;

    /**
     * Creates a Poller for write ops.
     */
    abstract Poller writePoller() throws IOException;

    /**
     * Creates the PollerProvider.
     */
    static PollerProvider provider() {
        String cn = GetPropertyAction.privilegedGetProperty("jdk.PollerProvider");
        if (cn != null) {
            try {
                Class<?> clazz = Class.forName(cn, true, ClassLoader.getSystemClassLoader());
                return (PollerProvider) clazz.getConstructor().newInstance();
            } catch (Exception e) {
                throw new ServiceConfigurationError(null, e);
            }
        } else {
            return new DefaultPollerProvider();
        }
    }
}
