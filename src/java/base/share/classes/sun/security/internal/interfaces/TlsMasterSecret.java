/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.internal.interfaces;

import javax.crypto.SecretKey;

/**
 * An SSL/TLS master secret key. It is a <code>SecretKey</code> that optionally
 * contains protocol version information that is used to detect version
 * rollback attacks during the SSL/TLS handshake.
 *
 * <p>Implementation of this interface are returned by the
 * <code>generateKey()</code> method of KeyGenerators of the type
 * "TlsMasterSecret".
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 * @deprecated Sun JDK internal use only --- WILL BE REMOVED in a future
 * release.
 */
@Deprecated
public interface TlsMasterSecret extends SecretKey {

    /***
     * @deprecated A {@code serialVersionUID} field in an interface is
     * ineffectual. Do not use; no replacement.
     */
    @Deprecated
    @SuppressWarnings("serial")
    @java.io.Serial
    long serialVersionUID = -461748105810469773L;

    /**
     * Returns the major version number encapsulated in the premaster secret
     * this master secret was derived from, or -1 if it is not available.
     *
     * <p>This information will only usually only be available when RSA
     * was used as the key exchange algorithm.
     *
     * @return the major version number, or -1 if it is not available
     */
    int getMajorVersion();

    /**
     * Returns the minor version number encapsulated in the premaster secret
     * this master secret was derived from, or -1 if it is not available.
     *
     * <p>This information will only usually only be available when RSA
     * was used as the key exchange algorithm.
     *
     * @return the major version number, or -1 if it is not available
     */
    int getMinorVersion();

}
