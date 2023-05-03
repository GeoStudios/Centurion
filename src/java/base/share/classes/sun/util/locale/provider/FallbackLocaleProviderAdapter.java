/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.util.locale.provider;

import java.util.Collections;
import java.util.Locale;
import java.util.Set;

/**
 * FallbackProviderAdapter implementation.
 *
 * @since Java 2
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
public class FallbackLocaleProviderAdapter extends JRELocaleProviderAdapter {

    /**
     * Supported language tag set.
     */
    private static final Set<String> rootTagSet =
        Collections.singleton(Locale.ROOT.toLanguageTag());

    /**
     * Fallback provider only provides the ROOT locale data.
     */
    private final LocaleResources rootLocaleResources =
        new LocaleResources(this, Locale.ROOT);

    /**
     * Returns the type of this LocaleProviderAdapter
     */
    @Override
    public LocaleProviderAdapter.Type getAdapterType() {
        return Type.FALLBACK;
    }

    @Override
    public LocaleResources getLocaleResources(Locale locale) {
        return rootLocaleResources;
    }

    @Override
    protected Set<String> createLanguageTagSet(String category) {
        return rootTagSet;
    }

    @Override
    public boolean isSupportedProviderLocale(Locale locale, Set<String>langtags) {
        return Locale.ROOT.equals(locale);
    }
}
