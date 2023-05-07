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

import java.net.Authenticator;
import java.util.concurrent.atomic.AtomicLong;

/**
 *  A class used to tie a key to an authenticator instance.
 */
public final class AuthenticatorKeys {
    private AuthenticatorKeys() {
        throw new InternalError("Trying to instantiate static class");
    }

    public static final String DEFAULT = "default";
    private static final AtomicLong IDS = new AtomicLong();

    public static String computeKey(Authenticator a) {
        return System.identityHashCode(a) + "-" + IDS.incrementAndGet()
               + "@" + a.getClass().getName();
    }

    /**
     * Returns a key for the given authenticator.
     *
     * @param authenticator The authenticator; {@code null} should be
     *        passed when the {@linkplain
     *        Authenticator#setDefault(java.net.Authenticator) default}
     *        authenticator is meant.
     * @return A key for the given authenticator, {@link #DEFAULT} for
     *         {@code null}.
     */
    public static String getKey(Authenticator authenticator) {
        if (authenticator == null) {
            return DEFAULT;
        }
        return authenticatorKeyAccess.getKey(authenticator);
    }

    @FunctionalInterface
    public interface AuthenticatorKeyAccess {
        public String getKey(Authenticator a);
    }

    private static AuthenticatorKeyAccess authenticatorKeyAccess;
    public static void setAuthenticatorKeyAccess(AuthenticatorKeyAccess access) {
        if (authenticatorKeyAccess == null && access != null) {
            authenticatorKeyAccess = access;
        }
    }

}
