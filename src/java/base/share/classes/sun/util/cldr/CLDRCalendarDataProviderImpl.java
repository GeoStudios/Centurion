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

package java.base.share.classes.sun.util.cldr;

import static java.base.share.classes.sun.util.locale.provider.LocaleProviderAdapter.Type;

import java.util.Arrays;
import java.util.Map;
import java.util.Locale;
import java.util.Set;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.base.share.classes.sun.util.locale.provider.LocaleProviderAdapter;
import java.base.share.classes.sun.util.locale.provider.LocaleResources;
import java.base.share.classes.sun.util.locale.provider.CalendarDataProviderImpl;
import java.base.share.classes.sun.util.locale.provider.CalendarDataUtility;

/**
 * Concrete implementation of the
 * {@link java.util.spi.CalendarDataProvider CalendarDataProvider} class
 * for the CLDR LocaleProviderAdapter.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
public class CLDRCalendarDataProviderImpl extends CalendarDataProviderImpl {

    private static Map<String, Integer> firstDay = new ConcurrentHashMap<>();
    private static Map<String, Integer> minDays = new ConcurrentHashMap<>();

    public CLDRCalendarDataProviderImpl(Type type, Set<String> langtags) {
        super(type, langtags);
    }

    @Override
    public int getFirstDayOfWeek(Locale locale) {
        return findValue(CalendarDataUtility.FIRST_DAY_OF_WEEK, locale);
    }

    @Override
    public int getMinimalDaysInFirstWeek(Locale locale) {
        return findValue(CalendarDataUtility.MINIMAL_DAYS_IN_FIRST_WEEK, locale);
    }

    /**
     * Finds the requested integer value for the locale.
     * Each resource consists of the following:
     *
     *    (n: cc1 cc2 ... ccx;)*
     *
     * where 'n' is the integer for the following region codes, terminated by
     * a ';'.
     *
     */
    private static int findValue(String key, Locale locale) {
        Map<String, Integer> map = CalendarDataUtility.FIRST_DAY_OF_WEEK.equals(key) ?
            firstDay : minDays;
        String region = locale.getCountry();

        if (region.isEmpty()) {
            // Use "US" as default
            region = "US";
        }

        Integer val = map.get(region);
        if (val == null) {
            String valStr =
                LocaleProviderAdapter.forType(Type.CLDR).getLocaleResources(Locale.ROOT)
                   .getCalendarData(key);
            val = retrieveInteger(valStr, region)
                .orElse(retrieveInteger(valStr, "001").orElse(0));
            map.putIfAbsent(region, val);
        }

        return val;
    }

    private static Optional<Integer> retrieveInteger(String src, String region) {
        int regionIndex = src.indexOf(region);
        if (regionIndex >= 0) {
            int start = src.lastIndexOf(';', regionIndex) + 1;
            return Optional.of(Integer.parseInt(src, start, src.indexOf(':', start), 10));
        }
        return Optional.empty();
    }
}
