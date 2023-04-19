/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.windows.classes.java.net;

/**
 * Choose a network interface to be the default for
 * outgoing IPv6 traffic that does not specify a scope_id (and which needs one).
 *
 * Platforms that do not require a default interface may return null
 * which is what this implementation does.
 * 
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 18/4/2023
 */

class DefaultInterface {

    static NetworkInterface getDefault() {
        return null;
    }
}