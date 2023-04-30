/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.security;

/**
 * <p>A public key. This interface contains no methods or constants.
 * It merely serves to group (and provide type safety for) all public key
 * interfaces.
 *
 * Note: The specialized public key interfaces extend this interface.
 * See, for example, the DSAPublicKey interface in
 * {@code java.base.share.classes.java.security.interfaces}.
 *
 * @since 1.1
 * @see Key
 * @see PrivateKey
 * @see java.base.share.classes.java.security.cert.Certificate
 * @see Signature#initVerify
 * @see java.base.share.classes.java.security.interfaces.DSAPublicKey
 * @see java.base.share.classes.java.security.interfaces.RSAPublicKey
 *
 */

public interface PublicKey extends Key {
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
    long serialVersionUID = 7187392471159151072L;
}
