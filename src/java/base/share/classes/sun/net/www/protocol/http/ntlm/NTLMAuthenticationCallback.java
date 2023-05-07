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

package java.base.share.classes.sun.net.www.protocol.http.ntlm;

import java.net.URL;

/**
 * This class is used to call back to deployment to determine if a given
 * URL is trusted. Transparent authentication (try with logged in users
 * credentials without prompting) should only be tried with trusted sites.
 */
public abstract class NTLMAuthenticationCallback {
    private static volatile NTLMAuthenticationCallback callback;

    public static void setNTLMAuthenticationCallback(
            NTLMAuthenticationCallback callback) {
        NTLMAuthenticationCallback.callback = callback;
    }

    public static NTLMAuthenticationCallback getNTLMAuthenticationCallback() {
        return callback;
    }

    /**
     * Returns true if the given site is trusted, i.e. we can try
     * transparent Authentication.
     */
    public abstract boolean isTrustedSite(URL url);
}

