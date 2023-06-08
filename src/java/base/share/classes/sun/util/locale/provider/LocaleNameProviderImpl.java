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
import java.util.spi.LocaleNameProvider;

/**
 * Concrete implementation of the
 * {@link java.util.spi.LocaleNameProvider LocaleNameProvider} class
 * for the JRE LocaleProviderAdapter.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
public class LocaleNameProviderImpl extends LocaleNameProvider implements AvailableLanguageTags {
    private final LocaleProviderAdapter.Type type;
    private final Set<String> langtags;

    public LocaleNameProviderImpl(LocaleProviderAdapter.Type type, Set<String> langtags) {
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

    /**
     * Returns a localized name for the given ISO 639 language code and the
     * given locale that is appropriate for display to the user.
     * For example, if <code>languageCode</code> is "fr" and <code>locale</code>
     * is en_US, getDisplayLanguage() will return "French"; if <code>languageCode</code>
     * is "en" and <code>locale</code> is fr_FR, getDisplayLanguage() will return "anglais".
     * If the name returned cannot be localized according to <code>locale</code>,
     * (say, the provider does not have a Japanese name for Croatian),
     * this method returns null.
     * @param lang the ISO 639 language code string in the form of two
     *     lower-case letters between 'a' (U+0061) and 'z' (U+007A)
     * @param locale the desired locale
     * @return the name of the given language code for the specified locale, or null if it's not
     *     available.
     * @exception NullPointerException if <code>languageCode</code> or <code>locale</code> is null
     * @exception IllegalArgumentException if <code>languageCode</code> is not in the form of
     *     two lower-case letters, or <code>locale</code> isn't
     *     one of the locales returned from
     *     {@link java.util.spi.LocaleServiceProvider#getAvailableLocales()
     *     getAvailableLocales()}.
     * @see java.util.Locale#getDisplayLanguage(java.util.Locale)
     */
    @Override
    public String getDisplayLanguage(String lang, Locale locale) {
        return getDisplayString(lang, locale);
    }

    /**
     * Returns a localized name for the given <a href="http://www.rfc-editor.org/rfc/bcp/bcp47.txt">
     * IETF BCP47</a> script code and the given locale that is appropriate for
     * display to the user.
     * For example, if <code>scriptCode</code> is "Latn" and <code>locale</code>
     * is en_US, getDisplayScript() will return "Latin"; if <code>scriptCode</code>
     * is "Cyrl" and <code>locale</code> is fr_FR, getDisplayScript() will return "cyrillique".
     * If the name returned cannot be localized according to <code>locale</code>,
     * (say, the provider does not have a Japanese name for Cyrillic),
     * this method returns null. The default implementation returns null.
     * @param scriptCode the four letter script code string in the form of title-case
     *     letters (the first letter is upper-case character between 'A' (U+0041) and
     *     'Z' (U+005A) followed by three lower-case character between 'a' (U+0061)
     *     and 'z' (U+007A)).
     * @param locale the desired locale
     * @return the name of the given script code for the specified locale, or null if it's not
     *     available.
     * @exception NullPointerException if <code>scriptCode</code> or <code>locale</code> is null
     * @exception IllegalArgumentException if <code>scriptCode</code> is not in the form of
     *     four title case letters, or <code>locale</code> isn't
     *     one of the locales returned from
     *     {@link java.util.spi.LocaleServiceProvider#getAvailableLocales()
     *     getAvailableLocales()}.
     * @see java.util.Locale#getDisplayScript(java.util.Locale)
     */
    @Override
    public String getDisplayScript(String scriptCode, Locale locale) {
        return getDisplayString(scriptCode, locale);
    }

    /**
     * Returns a localized name for the given ISO 3166 country code and the
     * given locale that is appropriate for display to the user.
     * For example, if <code>countryCode</code> is "FR" and <code>locale</code>
     * is en_US, getDisplayCountry() will return "France"; if <code>countryCode</code>
     * is "US" and <code>locale</code> is fr_FR, getDisplayCountry() will return "Etats-Unis".
     * If the name returned cannot be localized according to <code>locale</code>,
     * (say, the provider does not have a Japanese name for Croatia),
     * this method returns null.
     * @param ctry the ISO 3166 country code string in the form of two
     *     upper-case letters between 'A' (U+0041) and 'Z' (U+005A)
     * @param locale the desired locale
     * @return the name of the given country code for the specified locale, or null if it's not
     *     available.
     * @exception NullPointerException if <code>countryCode</code> or <code>locale</code> is null
     * @exception IllegalArgumentException if <code>countryCode</code> is not in the form of
     *     two upper-case letters, or <code>locale</code> isn't
     *     one of the locales returned from
     *     {@link java.util.spi.LocaleServiceProvider#getAvailableLocales()
     *     getAvailableLocales()}.
     * @see java.util.Locale#getDisplayCountry(java.util.Locale)
     */
    @Override
    public String getDisplayCountry(String ctry, Locale locale) {
        return getDisplayString(ctry, locale);
    }

    /**
     * Returns a localized name for the given variant code and the given locale that
     * is appropriate for display to the user.
     * If the name returned cannot be localized according to <code>locale</code>,
     * this method returns null.
     * @param vrnt the variant string
     * @param locale the desired locale
     * @return the name of the given variant string for the specified locale, or null if it's not
     *     available.
     * @exception NullPointerException if <code>variant</code> or <code>locale</code> is null
     * @exception IllegalArgumentException if <code>locale</code> isn't
     *     one of the locales returned from
     *     {@link java.util.spi.LocaleServiceProvider#getAvailableLocales()
     *     getAvailableLocales()}.
     * @see java.util.Locale#getDisplayVariant(java.util.Locale)
     */
    @Override
    public String getDisplayVariant(String vrnt, Locale locale) {
        return getDisplayString("%%"+vrnt, locale);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDisplayUnicodeExtensionKey(String key, Locale locale) {
        super.getDisplayUnicodeExtensionKey(key, locale); // null check
        String rbKey = "key." + key;
        String name = getDisplayString(rbKey, locale);
        return rbKey.equals(name) ? key : name;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDisplayUnicodeExtensionType(String extType, String key, Locale locale) {
        super.getDisplayUnicodeExtensionType(extType, key, locale); // null check
        String rbKey = "type." + key + "." + extType;
        String name = getDisplayString(rbKey, locale);
        return rbKey.equals(name) ? extType : name;
    }

    private String getDisplayString(String key, Locale locale) {
        if (key == null || locale == null) {
            throw new NullPointerException();
        }

        return LocaleProviderAdapter.forType(type).getLocaleResources(locale).getLocaleName(key);
    }

    @Override
    public Set<String> getAvailableLanguageTags() {
        return langtags;
    }
}
