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

package java.base.share.classes.java.text.spi;


import java.base.share.classes.java.text.DecimalFormatSymbols;
import java.base.share.classes.java.util.Locale;
import java.base.share.classes.java.util.spi.LocaleServiceProvider;















/**
 * An abstract class for service providers that
 * provide instances of the
 * {@link java.text.DecimalFormatSymbols DecimalFormatSymbols} class.
 *
 * <p>The requested {@code Locale} may contain an <a
 * href="../../util/Locale.html#def_locale_extension"> extension</a> for
 * specifying the desired numbering system. For example, {@code "ar-u-nu-arab"}
 * (in the BCP 47 language tag form) specifies Arabic with the Arabic-Indic
 * digits and symbols, while {@code "ar-u-nu-latn"} specifies Arabic with the
 * Latin digits and symbols. Refer to the <em>Unicode Locale Data Markup
 * Language (LDML)</em> specification for numbering systems.
 *
 * @see Locale#forLanguageTag(String)
 * @see Locale#getExtension(char)
 */
public abstract class DecimalFormatSymbolsProvider extends LocaleServiceProvider {

    /**
     * Sole constructor.  (For invocation by subclass constructors, typically
     * implicit.)
     */
    protected DecimalFormatSymbolsProvider() {
    }

    /**
     * Returns a new {@code DecimalFormatSymbols} instance for the
     * specified locale.
     *
     * @param locale the desired locale
     * @throws    NullPointerException if {@code locale} is null
     * @throws    IllegalArgumentException if {@code locale} isn't
     *     one of the locales returned from
     *     {@link java.util.spi.LocaleServiceProvider#getAvailableLocales()
     *     getAvailableLocales()}.
     * @return a {@code DecimalFormatSymbols} instance.
     * @see java.text.DecimalFormatSymbols#getInstance(java.util.Locale)
     */
    public abstract DecimalFormatSymbols getInstance(Locale locale);
}
