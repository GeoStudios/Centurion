/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.util.locale.provider;

import java.util.List;
import java.util.Locale;
import sun.util.resources.LocaleData;

/**
 * Accessor for LocaleData
 *
 * @since Java 2
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
public interface ResourceBundleBasedAdapter {
    public LocaleData getLocaleData();

    /**
     * candidate locales customization
     */
    public List<Locale> getCandidateLocales(String baseName, Locale locale);
}
