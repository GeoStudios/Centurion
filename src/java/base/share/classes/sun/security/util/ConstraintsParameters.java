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

import java.security.Key;
import java.util.Date;
import java.util.Set;

/**
 * This interface contains parameters for checking against constraints that
 * extend past the publicly available parameters in
 * java.security.AlgorithmConstraints.
 */
public interface ConstraintsParameters {

    /**
     * Returns true if a certificate chains back to a trusted JDK root CA.
     */
    boolean anchorIsJdkCA();

    /**
     * Returns the set of keys that should be checked against the
     * constraints, or an empty set if there are no keys to be checked.
     */
    Set<Key> getKeys();

    /**
     * Returns the date that should be checked against the constraints, or
     * null if not set.
     */
    Date getDate();

    /**
     * Returns the Validator variant.
     */
    String getVariant();

    /**
     * Returns an extended message used in exceptions. See
     * DisabledAlgorithmConstraints for usage.
     */
    String extendedExceptionMsg();
}
