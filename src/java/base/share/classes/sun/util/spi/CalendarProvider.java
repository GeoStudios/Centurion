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
 * @since Alpha cdk-1.1
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
