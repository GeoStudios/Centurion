/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.util.spi;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.spi.LocaleServiceProvider;

/**
 * An abstract class for service providers that
 * provide instances of the
 * {@link java.util.Calendar Calendar} class.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
public abstract class CalendarProvider extends LocaleServiceProvider {

    /**
     * Sole constructor.  (For invocation by subclass constructors, typically
     * implicit.)
     */
    protected CalendarProvider() {
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
    public abstract Calendar getInstance(TimeZone zone, Locale locale);
}
