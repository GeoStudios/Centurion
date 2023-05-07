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

package java.base.share.classes.sun.security.ssl;

import java.security.*;
import java.security.cert.*;
import java.util.*;
import javax.net.ssl.*;
import java.base.share.classes.sun.security.validator.TrustStoreUtil;
import java.base.share.classes.sun.security.validator.Validator;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

abstract class TrustManagerFactoryImpl extends TrustManagerFactorySpi {

    private X509TrustManager trustManager = null;
    private boolean isInitialized = false;

    TrustManagerFactoryImpl() {
        // empty
    }

    @Override
    protected void engineInit(KeyStore ks) throws KeyStoreException {
        if (ks == null) {
            try {
                trustManager = getInstance(TrustStoreManager.getTrustedCerts());
            } catch (SecurityException se) {
                // eat security exceptions but report other throwables
                if (SSLLogger.isOn && SSLLogger.isOn("trustmanager")) {
                    SSLLogger.fine(
                            "SunX509: skip default keystore", se);
                }
            } catch (Error err) {
                if (SSLLogger.isOn && SSLLogger.isOn("trustmanager")) {
                    SSLLogger.fine(
                        "SunX509: skip default keystore", err);
                }
                throw err;
            } catch (RuntimeException re) {
                if (SSLLogger.isOn && SSLLogger.isOn("trustmanager")) {
                    SSLLogger.fine(
                        "SunX509: skip default keystore", re);
                }
                throw re;
            } catch (Exception e) {
                if (SSLLogger.isOn && SSLLogger.isOn("trustmanager")) {
                    SSLLogger.fine(
                        "SunX509: skip default keystore", e);
                }
                throw new KeyStoreException(
                    "problem accessing trust store", e);
            }
        } else {
            trustManager = getInstance(TrustStoreUtil.getTrustedCerts(ks));
        }

        isInitialized = true;
    }

    abstract X509TrustManager getInstance(
            Collection<X509Certificate> trustedCerts);

    abstract X509TrustManager getInstance(ManagerFactoryParameters spec)
            throws InvalidAlgorithmParameterException;

    @Override
    protected void engineInit(ManagerFactoryParameters spec) throws
            InvalidAlgorithmParameterException {
        trustManager = getInstance(spec);
        isInitialized = true;
    }

    /**
     * Returns one trust manager for each type of trust material.
     */
    @Override
    protected TrustManager[] engineGetTrustManagers() {
        if (!isInitialized) {
            throw new IllegalStateException(
                        "TrustManagerFactoryImpl is not initialized");
        }
        return new TrustManager[] { trustManager };
    }

    public static final class SimpleFactory extends TrustManagerFactoryImpl {
        @Override
        X509TrustManager getInstance(
                Collection<X509Certificate> trustedCerts) {
            return new X509TrustManagerImpl(
                    Validator.TYPE_SIMPLE, trustedCerts);
        }

        @Override
        X509TrustManager getInstance(ManagerFactoryParameters spec)
                throws InvalidAlgorithmParameterException {
            throw new InvalidAlgorithmParameterException
                ("SunX509 TrustManagerFactory does not use "
                + "ManagerFactoryParameters");
        }
    }

    public static final class PKIXFactory extends TrustManagerFactoryImpl {
        @Override
        X509TrustManager getInstance(
                Collection<X509Certificate> trustedCerts) {
            return new X509TrustManagerImpl(Validator.TYPE_PKIX, trustedCerts);
        }

        @Override
        X509TrustManager getInstance(ManagerFactoryParameters spec)
                throws InvalidAlgorithmParameterException {
            if (!(spec instanceof CertPathTrustManagerParameters)) {
                throw new InvalidAlgorithmParameterException
                    ("Parameters must be CertPathTrustManagerParameters");
            }
            CertPathParameters params =
                ((CertPathTrustManagerParameters)spec).getParameters();
            if (!(params instanceof PKIXBuilderParameters pkixParams)) {
                throw new InvalidAlgorithmParameterException
                    ("Encapsulated parameters must be PKIXBuilderParameters");
            }
            return new X509TrustManagerImpl(Validator.TYPE_PKIX, pkixParams);
        }
    }
}
