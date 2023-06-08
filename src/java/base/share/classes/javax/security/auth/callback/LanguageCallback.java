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

package java.base.share.classes.javax.security.auth.callback;

import java.util.Locale;

/**
 * <p> Underlying security services instantiate and pass a
 * {@code LanguageCallback} to the {@code handle}
 * method of a {@code CallbackHandler} to retrieve the {@code Locale}
 * used for localizing text.
 *
 * @since 1.4
 * @see java.base.share.classes.javax.security.auth.callback.CallbackHandler
 */
public class LanguageCallback implements Callback, java.io.Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 2019050433478903213L;

    /**
     * @serial
     * @since 1.4
     */
    private Locale locale;

    /**
     * Construct a {@code LanguageCallback}.
     */
    public LanguageCallback() { }

    /**
     * Set the retrieved {@code Locale}.
     *
     * @param locale the retrieved {@code Locale}.
     *
     * @see #getLocale
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * Get the retrieved {@code Locale}.
     *
     * @return the retrieved {@code Locale}, or null
     *          if no {@code Locale} could be retrieved.
     *
     * @see #setLocale
     */
    public Locale getLocale() {
        return locale;
    }
}
