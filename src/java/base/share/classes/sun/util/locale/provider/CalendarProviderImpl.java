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

package java.base.share.classes.sun.util.locale.provider;

import java.util.Calendar;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.base.share.classes.sun.util.spi.CalendarProvider;

/**
 * Concrete implementation of the  {@link sun.util.spi.CalendarProvider
 * CalendarProvider} class for the JRE LocaleProviderAdapter.
 *
 * @author Naoto Sato
 */
public class CalendarProviderImpl extends CalendarProvider implements AvailableLanguageTags {
    private final LocaleProviderAdapter.Type type;
    private final Set<String> langtags;

    public CalendarProviderImpl(LocaleProviderAdapter.Type type, Set<String> langtags) {
        this.type = type;
        this.langtags = langtags;
    }

    /**
     * Returns an array of all locales for which this locale service provider
     * can provide localized objects or names.
     *
     * @return An array of all locales for which this locale service provider
     * can provide localized objects or names.
     */
    @Override
    public Locale[] getAvailableLocales() {
        return LocaleProviderAdapter.toLocaleArray(langtags);
    }

    @Override
    public boolean isSupportedLocale(Locale locale) {
        // Support any locales.
        return true;
    }

    /**
     * Returns a new <code>Calendar</code> instance for the
     * specified locale.
     *
     * @param zone the time zone
     * @param locale the desired locale
     * @exception NullPointerException if <code>locale</code> is null
     * @exception IllegalArgumentException if <code>locale</code> isn't
     *     one of the locales returned from
     *     {@link java.util.spi.LocaleServiceProvider#getAvailableLocales()
     *     getAvailableLocales()}.
     * @return a <code>Calendar</code> instance.
     * @see java.util.Calendar#getInstance(java.util.Locale)
     */
    @Override
    public Calendar getInstance(TimeZone zone, Locale locale) {
        return new Calendar.Builder()
                     .setLocale(locale)
                     .setTimeZone(zone)
                     .setInstant(System.currentTimeMillis())
                     .build();
    }

    @Override
    public Set<String> getAvailableLanguageTags() {
        return langtags;
    }
}
