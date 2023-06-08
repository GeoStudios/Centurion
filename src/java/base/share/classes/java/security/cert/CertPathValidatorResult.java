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
 * A specification of the result of a certification path validator algorithm.
 * <p>
 * The purpose of this interface is to group (and provide type safety
 * for) all certification path validator results. All results returned
 * by the {@link CertPathValidator#validate CertPathValidator.validate}
 * method must implement this interface.
 *
 * @see CertPathValidator
 *
 * @since       1.4
 * @author      Yassir Elley
 */
public interface CertPathValidatorResult extends Cloneable {

    /**
     * Makes a copy of this {@code CertPathValidatorResult}. Changes to the
     * copy will not affect the original and vice versa.
     *
     * @return a copy of this {@code CertPathValidatorResult}
     */
    Object clone();
}
