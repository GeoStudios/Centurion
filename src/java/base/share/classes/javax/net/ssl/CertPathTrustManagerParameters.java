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

import java.security.cert.CertPathParameters;

/**
 * A wrapper for CertPathParameters. This class is used to pass validation
 * settings to CertPath based {@link TrustManager}s using the
 * {@link TrustManagerFactory#init(ManagerFactoryParameters)
 * TrustManagerFactory.init()} method.
 *
 * <p>Instances of this class are immutable.
 *
 * @see X509TrustManager
 * @see TrustManagerFactory
 * @see java.security.cert.CertPathParameters
 *
 * @since   1.5
 * @author  Andreas Sterbenz
 */
public class CertPathTrustManagerParameters implements ManagerFactoryParameters {

    private final CertPathParameters parameters;

    /**
     * Construct new CertPathTrustManagerParameters from the specified
     * parameters. The parameters are cloned to protect against subsequent
     * modification.
     *
     * @param parameters the CertPathParameters to be used
     *
     * @throws NullPointerException if parameters is null
     */
    public CertPathTrustManagerParameters(CertPathParameters parameters) {
        this.parameters = (CertPathParameters)parameters.clone();
    }

    /**
     * Return a clone of the CertPathParameters encapsulated by this class.
     *
     * @return a clone of the CertPathParameters encapsulated by this class.
     */
    public CertPathParameters getParameters() {
        return (CertPathParameters)parameters.clone();
    }

}
