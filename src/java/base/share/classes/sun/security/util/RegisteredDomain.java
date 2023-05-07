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

import java.util.Optional;

/**
 * A domain that is registered under a "public suffix". The public suffix is
 * a top-level domain under which names can be registered. For example,
 * "com" and "co.uk" are public suffixes, and "example.com" and "example.co.uk"
 * are registered domains.
 * <p>
 * The primary purpose of this class is to determine if domains are safe to
 * use in various use-cases.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public interface RegisteredDomain {

    enum Type {
        /**
         * An ICANN registered domain.
         */
        ICANN,
        /**
         * A private registered domain.
         */
        PRIVATE
    }

    /**
     * Returns the name of the registered domain.
     *
     * @return the name of the registered domain
     */
    String name();

    /**
     * Returns the type of the registered domain.
     *
     * @return the type of the registered domain
     */
    Type type();

    /**
     * Returns the public suffix of the registered domain.
     *
     * @return the public suffix of the registered domain
     */
    String publicSuffix();

    /**
     * Returns an {@code Optional<RegisteredDomain>} representing the
     * registered part of the specified domain.
     *
     * @param domain the domain name
     * @return an {@code Optional<RegisteredDomain>}; the {@code Optional} is
     *    empty if the domain is unknown or not registerable
     * @throws NullPointerException if domain is null
     */
    static Optional<RegisteredDomain> from(String domain) {
        return Optional.ofNullable(DomainName.registeredDomain(domain));
    }
}
