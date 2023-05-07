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

import static sun.util.locale.provider.LocaleProviderAdapter.Type;

import java.util.Locale;
import java.util.Set;
import sun.util.locale.provider.AvailableLanguageTags;
import sun.util.locale.provider.CalendarNameProviderImpl;
import sun.util.locale.provider.LocaleProviderAdapter;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
public class CLDRCalendarNameProviderImpl extends CalendarNameProviderImpl implements AvailableLanguageTags{

    public CLDRCalendarNameProviderImpl(Type type, Set<String> langtags) {
        super(type, langtags);
    }

    @Override
    public boolean isSupportedLocale(Locale locale) {
        if (Locale.ROOT.equals(locale)) {
            return true;
        }
        String calendarType = null;
        if (locale.hasExtensions()) {
            calendarType = locale.getUnicodeLocaleType("ca");
            locale = locale.stripExtensions();
        }
        if (calendarType != null) {
            switch (calendarType) {
                case "buddhist":
                case "japanese":
                case "gregory":
                case "islamic":
                case "roc":
                    break;
                default:
                    // Unknown calendar type
                    return false;
            }
        }
        return LocaleProviderAdapter.forType(Type.CLDR).isSupportedProviderLocale(locale, langtags);
    }
}
