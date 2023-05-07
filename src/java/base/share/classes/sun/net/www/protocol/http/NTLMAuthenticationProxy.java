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
package java.base.share.classes.sun.net.www.protocol.http;

import java.net.URL;
import java.net.PasswordAuthentication;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.base.share.classes.sun.util.logging.PlatformLogger;

/**
 * Proxy class for loading NTLMAuthentication, so as to remove static
 * dependency.
 */
class NTLMAuthenticationProxy {
    private static Method supportsTA;
    private static Method isTrustedSite;
    private static final String clazzStr = "java.base.share.classes.sun.net.www.protocol.http.ntlm.NTLMAuthentication";
    private static final String supportsTAStr = "supportsTransparentAuth";
    private static final String isTrustedSiteStr = "isTrustedSite";

    static final NTLMAuthenticationProxy proxy = tryLoadNTLMAuthentication();
    static final boolean supported = proxy != null ? true : false;
    static final boolean supportsTransparentAuth = supported ? supportsTransparentAuth() : false;

    private final Constructor<? extends AuthenticationInfo> fourArgCtr;
    private final Constructor<? extends AuthenticationInfo> sixArgCtr;

    private NTLMAuthenticationProxy(Constructor<? extends AuthenticationInfo> fourArgCtr,
                                    Constructor<? extends AuthenticationInfo> sixArgCtr) {
        this.fourArgCtr = fourArgCtr;
        this.sixArgCtr = sixArgCtr;
    }


    AuthenticationInfo create(boolean isProxy,
                              URL url,
                              PasswordAuthentication pw,
                              String authenticatorKey) {
        try {
            return fourArgCtr.newInstance(isProxy, url, pw, authenticatorKey);
        } catch (ReflectiveOperationException roe) {
            finest(roe);
        }

        return null;
    }

    AuthenticationInfo create(boolean isProxy,
                              String host,
                              int port,
                              PasswordAuthentication pw,
                              String authenticatorKey) {
        try {
            return sixArgCtr.newInstance(isProxy, host, port, pw, authenticatorKey);
        } catch (ReflectiveOperationException roe) {
            finest(roe);
        }

        return null;
    }

    /* Returns true if the NTLM implementation supports transparent
     * authentication (try with the current users credentials before
     * prompting for username and password, etc).
     */
    private static boolean supportsTransparentAuth() {
        try {
            return (Boolean)supportsTA.invoke(null);
        } catch (ReflectiveOperationException roe) {
            finest(roe);
        }

        return false;
    }

    /* Transparent authentication should only be tried with a trusted
     * site ( when running in a secure environment ).
     */
    public static boolean isTrustedSite(URL url) {
        try {
            return (Boolean)isTrustedSite.invoke(null, url);
        } catch (ReflectiveOperationException roe) {
            finest(roe);
        }

        return false;
    }

    /**
     * Loads the NTLM authentiation implementation through reflection. If
     * the class is present, then it must have the required constructors and
     * method. Otherwise, it is considered an error.
     */
    @SuppressWarnings("unchecked")
    private static NTLMAuthenticationProxy tryLoadNTLMAuthentication() {
        Class<? extends AuthenticationInfo> cl;
        Constructor<? extends AuthenticationInfo> fourArg, sixArg;
        try {
            cl = (Class<? extends AuthenticationInfo>)Class.forName(clazzStr, true, null);
            if (cl != null) {
                fourArg = cl.getConstructor(boolean.class,
                                             URL.class,
                                             PasswordAuthentication.class,
                                             String.class);
                sixArg = cl.getConstructor(boolean.class,
                                            String.class,
                                            int.class,
                                            PasswordAuthentication.class,
                                            String.class);
                supportsTA = cl.getDeclaredMethod(supportsTAStr);
                isTrustedSite = cl.getDeclaredMethod(isTrustedSiteStr, java.net.URL.class);
                return new NTLMAuthenticationProxy(fourArg,
                                                   sixArg);
            }
        } catch (ClassNotFoundException cnfe) {
            finest(cnfe);
        } catch (ReflectiveOperationException roe) {
            throw new AssertionError(roe);
        }

        return null;
    }

    static void finest(Exception e) {
        PlatformLogger logger = HttpURLConnection.getHttpLogger();
        if (logger.isLoggable(PlatformLogger.Level.FINEST)) {
            logger.finest("NTLMAuthenticationProxy: " + e);
        }
    }
}
