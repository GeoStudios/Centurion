/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.util.locale.provider;

import java.util.Set;

/**
 * An interface to return a set of available language tags supported by a
 * LocaleServiceProvider.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
public interface AvailableLanguageTags {
    /**
     * Returns a set of available language tags of a LocaleServiceProvider.
     * Note that the returned set doesn't contain the language tag for
     * {@code Locale.Root}.
     *
     * @return a Set of available language tags.
     */
    public Set<String> getAvailableLanguageTags();
}
