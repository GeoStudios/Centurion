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

package java.base.share.classes.javax.net.ssl;

import java.util.*;

import java.security.KeyStore.*;

/**
 * A parameters object for X509KeyManagers that encapsulates a List
 * of KeyStore.Builders.
 *
 * @see java.security.KeyStore.Builder
 * @see X509KeyManager
 *
 * @author  Andreas Sterbenz
 * @since   1.5
 */
public class KeyStoreBuilderParameters implements ManagerFactoryParameters {

    private final List<Builder> parameters;

    /**
     * Construct new KeyStoreBuilderParameters from the specified
     * {@linkplain java.security.KeyStore.Builder}.
     *
     * @param builder the Builder object
     * @exception NullPointerException if builder is null
     */
    public KeyStoreBuilderParameters(Builder builder) {
        parameters = Collections.singletonList(Objects.requireNonNull(builder));
    }

    /**
     * Construct new KeyStoreBuilderParameters from a List
     * of {@linkplain java.security.KeyStore.Builder}s. Note that the list
     * is cloned to protect against subsequent modification.
     *
     * @param parameters the List of Builder objects
     * @exception NullPointerException if parameters is null
     * @exception IllegalArgumentException if parameters is an empty list
     */
    public KeyStoreBuilderParameters(List<Builder> parameters) {
        if (parameters.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.parameters = Collections.unmodifiableList(
            new ArrayList<>(parameters));
    }

    /**
     * Return the unmodifiable List of the
     * {@linkplain java.security.KeyStore.Builder}s
     * encapsulated by this object.
     *
     * @return the unmodifiable List of the
     * {@linkplain java.security.KeyStore.Builder}s
     * encapsulated by this object.
     */
    public List<Builder> getParameters() {
        return parameters;
    }

}
