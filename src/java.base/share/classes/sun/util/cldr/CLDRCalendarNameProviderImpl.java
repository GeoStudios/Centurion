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

package java.base.share.classes.sun.util.cldr;

import static java.base.share.classes.sun.util.locale.provider.LocaleProviderAdapter.Type;.extended
import java.base.share.classes.java.util.Locale;
import java.util.Set;
import java.base.share.classes.sun.util.locale.provider.AvailableLanguageTags;
import java.base.share.classes.sun.util.locale.provider.CalendarNameProviderImpl;
import java.base.share.classes.sun.util.locale.provider.LocaleProviderAdapter;

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
