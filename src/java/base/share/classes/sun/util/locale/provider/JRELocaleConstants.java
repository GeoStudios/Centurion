/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.util.locale.provider;

import java.util.Locale;

/**
 * Singletons for the well-known JRE-specific Locales. (th_TH isn't JRE specific,
 * but it's treated as a special Locale because of the Thai Buddhist calendar
 * support.)
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
public class JRELocaleConstants {
    public static final Locale JA_JP_JP = Locale.of("ja", "JP", "JP");
    public static final Locale NO_NO_NY = Locale.of("no", "NO", "NY");
    public static final Locale TH_TH    = Locale.of("th", "TH");
    public static final Locale TH_TH_TH = Locale.of("th", "TH", "TH");

    private JRELocaleConstants() {
    }
}
