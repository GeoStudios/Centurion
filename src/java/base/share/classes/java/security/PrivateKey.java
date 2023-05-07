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
 * A private key.
 * The purpose of this interface is to group (and provide type safety
 * for) all private key interfaces.
 * <p>
 * Note: The specialized private key interfaces extend this interface.
 * See, for example, the {@code DSAPrivateKey} interface in
 * {@link java.base.share.classes.java.security.interfaces}.
 * <p>
 * Implementations should override the default {@code destroy} and
 * {@code isDestroyed} methods from the
 * {@link javax.security.auth.Destroyable} interface to enable
 * sensitive key information to be destroyed, cleared, or in the case
 * where such information is immutable, unreferenced.
 * Finally, since {@code PrivateKey} is {@code Serializable}, implementations
 * should also override
 * {@link java.io.ObjectOutputStream#writeObject(java.lang.Object)}
 * to prevent keys that have been destroyed from being serialized.
 *
 * @see Key
 * @see PublicKey
 * @see java.base.share.classes.java.security.cert.Certificate
 * @see Signature#initVerify
 * @see java.base.share.classes.java.security.interfaces.DSAPrivateKey
 * @see java.base.share.classes.java.security.interfaces.RSAPrivateKey
 * @see java.base.share.classes.java.security.interfaces.RSAPrivateCrtKey
 *
 * @author Benjamin Renaud
 * @author Josh Bloch
 * @since 1.1
 */

public interface PrivateKey extends Key, javax.security.auth.Destroyable {

    // Declare serialVersionUID to be compatible with JDK1.1
    /**
     * The class fingerprint that is set to indicate serialization
     * compatibility with a previous version of the class.
     *
     * @deprecated A {@code serialVersionUID} field in an interface is
     * ineffectual. Do not use; no replacement.
     */
    @Deprecated
    @SuppressWarnings("serial")
    @java.io.Serial
    long serialVersionUID = 6034044314589513430L;
}
