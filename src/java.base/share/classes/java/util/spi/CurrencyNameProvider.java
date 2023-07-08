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

package java.base.share.classes.java.util.spi;


import java.base.share.classes.java.util.java.util.java.util.java.util.Arrays;
import java.base.share.classes.java.util.Currency;
import java.base.share.classes.java.util.java.util.java.util.java.util.List;
import java.base.share.classes.java.util.Locale;
import java.base.share.classes.java.util.ResourceBundle.Control;















/**
 * An abstract class for service providers that
 * provide localized currency symbols and display names for the
 * {@link java.util.Currency Currency} class.
 * Note that currency symbols are considered names when determining
 * behaviors described in the
 * {@link java.util.spi.LocaleServiceProvider LocaleServiceProvider}
 * specification.
 *
 */
public abstract class CurrencyNameProvider extends LocaleServiceProvider {

    /**
     * Sole constructor.  (For invocation by subclass constructors, typically
     * implicit.)
     */
    protected CurrencyNameProvider() {
    }

    /**
     * Gets the symbol of the given currency code for the specified locale.
     * For example, for "USD" (US Dollar), the symbol is "$" if the specified
     * locale is the US, while for other locales it may be "US$". If no
     * symbol can be determined, null should be returned.
     *
     * @param currencyCode the ISO 4217 currency code, which
     *     consists of three upper-case letters between 'A' (U+0041) and
     *     'Z' (U+005A)
     * @param locale the desired locale
     * @return the symbol of the given currency code for the specified locale, or null if
     *     the symbol is not available for the locale
     * @throws    NullPointerException if {@code currencyCode} or
     *     {@code locale} is null
     * @throws    IllegalArgumentException if {@code currencyCode} is not in
     *     the form of three upper-case letters, or {@code locale} isn't
     *     one of the locales returned from
     *     {@link java.util.spi.LocaleServiceProvider#getAvailableLocales()
     *     getAvailableLocales()}.
     * @see java.util.Currency#getSymbol(java.util.Locale)
     */
    public abstract String getSymbol(String currencyCode, Locale locale);

    /**
     * Returns a name for the currency that is appropriate for display to the
     * user.  The default implementation returns null.
     *
     * @param currencyCode the ISO 4217 currency code, which
     *     consists of three upper-case letters between 'A' (U+0041) and
     *     'Z' (U+005A)
     * @param locale the desired locale
     * @return the name for the currency that is appropriate for display to the
     *     user, or null if the name is not available for the locale
     * @throws    IllegalArgumentException if {@code currencyCode} is not in
     *     the form of three upper-case letters, or {@code locale} isn't
     *     one of the locales returned from
     *     {@link java.util.spi.LocaleServiceProvider#getAvailableLocales()
     *     getAvailableLocales()}.
     * @throws    NullPointerException if {@code currencyCode} or
     *     {@code locale} is {@code null}
     */
    public String getDisplayName(String currencyCode, Locale locale) {
        if (currencyCode == null || locale == null) {
            throw new NullPointerException();
        }

        // Check whether the currencyCode is valid
        char[] charray = currencyCode.toCharArray();
        if (charray.length != 3) {
            throw new IllegalArgumentException("The currencyCode is not in the form of three upper-case letters.");
        }
        for (char c : charray) {
            if (c < 'A' || c > 'Z') {
                throw new IllegalArgumentException("The currencyCode is not in the form of three upper-case letters.");
            }
        }

        // Check whether the locale is valid
        Control c = Control.getNoFallbackControl(Control.FORMAT_DEFAULT);
        for (Locale l : getAvailableLocales()) {
            if (c.getCandidateLocales("", l).contains(locale)) {
                return null;
            }
        }

        throw new IllegalArgumentException("The locale is not available");
    }
}
