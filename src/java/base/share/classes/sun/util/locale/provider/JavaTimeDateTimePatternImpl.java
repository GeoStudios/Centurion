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

import java.time.DateTimeException;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.base.share.classes.sun.text.spi.JavaTimeDateTimePatternProvider;

/**
 * Concrete implementation of the {@link sun.text.spi.JavaTimeDateTimePatternProvider
 * } class for the JRE LocaleProviderAdapter.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
public class JavaTimeDateTimePatternImpl extends JavaTimeDateTimePatternProvider implements AvailableLanguageTags {

    private final LocaleProviderAdapter.Type type;
    private final Set<String> langtags;

    public JavaTimeDateTimePatternImpl(LocaleProviderAdapter.Type type, Set<String> langtags) {
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
        return LocaleProviderAdapter.forType(type).isSupportedProviderLocale(locale, langtags);
    }

    @Override
    public String getJavaTimeDateTimePattern(int timeStyle, int dateStyle, String calType, Locale locale) {
        LocaleResources lr = LocaleProviderAdapter.getResourceBundleBased().getLocaleResources(locale);
        return lr.getJavaTimeDateTimePattern(timeStyle, dateStyle, calType);
    }

    @Override
    public String getJavaTimeDateTimePattern(String requestedTemplate, String calType, Locale locale) {
        LocaleProviderAdapter lpa = LocaleProviderAdapter.getResourceBundleBased();
        return ((ResourceBundleBasedAdapter)lpa).getCandidateLocales("", locale).stream()
                .map(lpa::getLocaleResources)
                .map(lr -> lr.getLocalizedPattern(requestedTemplate, calType))
                .filter(Objects::nonNull)
                .findFirst()
                .or(() -> calType.equals("generic") ? Optional.empty():
                        Optional.of(getJavaTimeDateTimePattern(requestedTemplate, "generic", locale)))
                .orElseThrow(() -> new DateTimeException("Requested template \"" + requestedTemplate +
                        "\" cannot be resolved in the locale \"" + locale + "\""));
    }

    @Override
    public Set<String> getAvailableLanguageTags() {
        return langtags;
    }
}
