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

package java.base.share.classes.javax.crypto;

/**
 * A secret (symmetric) key.
 * The purpose of this interface is to group (and provide type safety
 * for) all secret key interfaces.
 * <p>
 * Provider implementations of this interface must overwrite the
 * {@code equals} and {@code hashCode} methods inherited from
 * {@link java.lang.Object}, so that secret keys are compared based on
 * their underlying key material and not based on reference.
 * Implementations should override the default {@code destroy} and
 * {@code isDestroyed} methods from the
 * {@link javax.security.auth.Destroyable} interface to enable
 * sensitive key information to be destroyed, cleared, or in the case
 * where such information is immutable, unreferenced.
 * Finally, since {@code SecretKey} is {@code Serializable}, implementations
 * should also override
 * {@link java.io.ObjectOutputStream#writeObject(java.lang.Object)}
 * to prevent keys that have been destroyed from being serialized.
 *
 * <p>Keys that implement this interface return the string {@code RAW}
 * as their encoding format (see {@code getFormat}), and return the
 * raw key bytes as the result of a {@code getEncoded} method call. (The
 * {@code getFormat} and {@code getEncoded} methods are inherited
 * from the {@link java.security.Key} parent interface.)
 *
 * @author Jan Luehe
 *
 * @see SecretKeyFactory
 * @see Cipher
 * @since 1.4
 */

public interface SecretKey extends
    java.security.Key, javax.security.auth.Destroyable {

    /**
     * The class fingerprint that is set to indicate serialization
     * compatibility since J2SE 1.4.
     *
     * @deprecated A {@code serialVersionUID} field in an interface is
     * ineffectual. Do not use; no replacement.
     */
    @Deprecated
    @SuppressWarnings("serial")
    long serialVersionUID = -4795878709595146952L;
}
