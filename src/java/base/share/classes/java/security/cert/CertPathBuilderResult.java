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

package java.base.share.classes.java.security.cert;

/**
 * A specification of the result of a certification path builder algorithm.
 * All results returned by the {@link CertPathBuilder#build
 * CertPathBuilder.build} method must implement this interface.
 * <p>
 * At a minimum, a {@code CertPathBuilderResult} contains the
 * {@code CertPath} built by the {@code CertPathBuilder} instance.
 * Implementations of this interface may add methods to return implementation
 * or algorithm specific information, such as debugging information or
 * certification path validation results.
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * Unless otherwise specified, the methods defined in this interface are not
 * thread-safe. Multiple threads that need to access a single
 * object concurrently should synchronize amongst themselves and
 * provide the necessary locking. Multiple threads each manipulating
 * separate objects need not synchronize.
 *
 * @see CertPathBuilder
 *
 * @since       1.4
 * @author      Sean Mullan
 */
public interface CertPathBuilderResult extends Cloneable {

    /**
     * Returns the built certification path.
     *
     * @return the certification path (never {@code null})
     */
    CertPath getCertPath();

    /**
     * Makes a copy of this {@code CertPathBuilderResult}. Changes to the
     * copy will not affect the original and vice versa.
     *
     * @return a copy of this {@code CertPathBuilderResult}
     */
    Object clone();
}
