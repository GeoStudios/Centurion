/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package javax.security.auth.callback;

import java.util.Locale;

/**
 * <p> Underlying security services instantiate and pass a
 * {@code LanguageCallback} to the {@code handle}
 * method of a {@code CallbackHandler} to retrieve the {@code Locale}
 * used for localizing text.
 *
 * @see javax.security.auth.callback.CallbackHandler
 */
public class LanguageCallback implements Callback, java.io.Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 2019050433478903213L;

    /**
     * @serial
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
