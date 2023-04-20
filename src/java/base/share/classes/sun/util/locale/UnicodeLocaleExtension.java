/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.util.locale;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.StringJoiner;

/**
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
public class UnicodeLocaleExtension extends Extension {
    public static final char SINGLETON = 'u';

    private final Set<String> attributes;
    private final Map<String, String> keywords;

    public static final UnicodeLocaleExtension CA_JAPANESE
        = new UnicodeLocaleExtension("ca", "japanese");
    public static final UnicodeLocaleExtension NU_THAI
        = new UnicodeLocaleExtension("nu", "thai");

    private UnicodeLocaleExtension(String key, String value) {
        super(SINGLETON, key + "-" + value);
        attributes = Collections.emptySet();
        keywords = Collections.singletonMap(key, value);
    }

    UnicodeLocaleExtension(SortedSet<String> attributes, SortedMap<String, String> keywords) {
        super(SINGLETON);
        if (attributes != null) {
            this.attributes = attributes;
        } else {
            this.attributes = Collections.emptySet();
        }
        if (keywords != null) {
            this.keywords = keywords;
        } else {
            this.keywords = Collections.emptyMap();
        }

        if (!this.attributes.isEmpty() || !this.keywords.isEmpty()) {
            StringJoiner sj = new StringJoiner(LanguageTag.SEP);
            for (String attribute : this.attributes) {
                sj.add(attribute);
            }
            for (Entry<String, String> keyword : this.keywords.entrySet()) {
                String key = keyword.getKey();
                String value = keyword.getValue();

                sj.add(key);
                if (!value.isEmpty()) {
                    sj.add(value);
                }
            }
            setValue(sj.toString());
        }
    }

    public Set<String> getUnicodeLocaleAttributes() {
        if (attributes == Collections.EMPTY_SET) {
            return attributes;
        }
        return Collections.unmodifiableSet(attributes);
    }

    public Set<String> getUnicodeLocaleKeys() {
        if (keywords == Collections.EMPTY_MAP) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(keywords.keySet());
    }

    public String getUnicodeLocaleType(String unicodeLocaleKey) {
        return keywords.get(unicodeLocaleKey);
    }

    public static boolean isSingletonChar(char c) {
        return (SINGLETON == LocaleUtils.toLower(c));
    }

    public static boolean isAttribute(String s) {
        // 3*8alphanum
        int len = s.length();
        return (len >= 3) && (len <= 8) && LocaleUtils.isAlphaNumericString(s);
    }

    public static boolean isKey(String s) {
        // 2alphanum
        return (s.length() == 2) && LocaleUtils.isAlphaNumericString(s);
    }

    public static boolean isTypeSubtag(String s) {
        // 3*8alphanum
        int len = s.length();
        return (len >= 3) && (len <= 8) && LocaleUtils.isAlphaNumericString(s);
    }
}
