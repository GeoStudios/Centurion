/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
 * @since Java 2
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
