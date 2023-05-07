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

import java.util.Locale;
import java.util.Set;
import java.util.spi.CalendarDataProvider;

/**
 * Concrete implementation of the  {@link java.util.spi.CalendarDataProvider
 * CalendarDataProvider} class for the JRE LocaleProviderAdapter.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
public class CalendarDataProviderImpl extends CalendarDataProvider implements AvailableLanguageTags {
    private final LocaleProviderAdapter.Type type;
    private final Set<String> langtags;

    public CalendarDataProviderImpl(LocaleProviderAdapter.Type type, Set<String> langtags) {
        this.type = type;
        this.langtags = langtags;
    }

    @Override
    public int getFirstDayOfWeek(Locale locale) {
        String fw = LocaleProviderAdapter.forType(type).getLocaleResources(locale)
                   .getCalendarData(CalendarDataUtility.FIRST_DAY_OF_WEEK);
        return convertToCalendarData(fw);
    }

    @Override
    public int getMinimalDaysInFirstWeek(Locale locale) {
        String md = LocaleProviderAdapter.forType(type).getLocaleResources(locale)
                   .getCalendarData(CalendarDataUtility.MINIMAL_DAYS_IN_FIRST_WEEK);
        return convertToCalendarData(md);
    }

    @Override
    public Locale[] getAvailableLocales() {
        return LocaleProviderAdapter.toLocaleArray(langtags);
    }

    @Override
    public Set<String> getAvailableLanguageTags() {
        return langtags;
    }

    private int convertToCalendarData(String src) {
        int val = Integer.parseInt(src);
        return (src.isEmpty() || val <= 0 || val > 7) ? 0 : val;
    }
}
