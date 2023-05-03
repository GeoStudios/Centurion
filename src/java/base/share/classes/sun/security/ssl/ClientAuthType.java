/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.ssl;

/**
 * Client authentication type.
 * 
 * @since Java 2
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

enum ClientAuthType {
    CLIENT_AUTH_NONE,           // turn off client authentication
    CLIENT_AUTH_REQUESTED,      // need to request client authentication
    CLIENT_AUTH_REQUIRED        // require client authentication
}

