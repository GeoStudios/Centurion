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


package java.base.share.classes.java.security;

/**
 * A parameter that contains a URI pointing to data intended for a
 * PolicySpi or ConfigurationSpi implementation.
 *
 * @since 1.6
 */
@SuppressWarnings("removal")
public class URIParameter implements
        Policy.Parameters, javax.security.auth.login.Configuration.Parameters {

    private final java.net.URI uri;

    /**
     * Constructs a {@code URIParameter} with the URI pointing to
     * data intended for an SPI implementation.
     *
     * @param uri the URI pointing to the data.
     *
     * @throws    NullPointerException if the specified URI is {@code null}.
     */
    public URIParameter(java.net.URI uri) {
        if (uri == null) {
            throw new NullPointerException("invalid null URI");
        }
        this.uri = uri;
    }

    /**
     * Returns the URI.
     *
     * @return uri the URI.
     */
    public java.net.URI getURI() {
        return uri;
    }
}
