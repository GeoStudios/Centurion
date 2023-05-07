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

package java.base.share.classes.sun.security.provider.certpath;

import java.util.Date;
import java.util.Set;

import java.security.cert.TrustAnchor;
import java.security.cert.X509CertSelector;
import java.security.cert.X509CRLSelector;

import java.base.share.classes.sun.security.x509.GeneralNameInterface;

/**
 * Helper class that allows access to JDK specific known-public methods in the
 * java.security.cert package. It relies on a subclass in the
 * java.security.cert packages that is initialized before any of these methods
 * are called (achieved via static initializers).
 *
 * The methods are made available in this fashion for performance reasons.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public abstract class CertPathHelper {

    /**
     * Object used to tunnel the calls. Initialized by CertPathHelperImpl.
     */
    protected static CertPathHelper instance;

    protected CertPathHelper() {
        // empty
    }

    protected abstract void implSetPathToNames(X509CertSelector sel,
            Set<GeneralNameInterface> names);

    protected abstract void implSetDateAndTime(X509CRLSelector sel, Date date, long skew);

    protected abstract boolean implIsJdkCA(TrustAnchor anchor);

    static void setPathToNames(X509CertSelector sel,
            Set<GeneralNameInterface> names) {
        instance.implSetPathToNames(sel, names);
    }

    public static void setDateAndTime(X509CRLSelector sel, Date date, long skew) {
        instance.implSetDateAndTime(sel, date, skew);
    }

    public static boolean isJdkCA(TrustAnchor anchor) {
        return anchor != null && instance.implIsJdkCA(anchor);
    }
}
