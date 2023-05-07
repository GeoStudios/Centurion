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

/**
 * Singletons for the well-known JRE-specific Locales. (th_TH isn't JRE specific,
 * but it's treated as a special Locale because of the Thai Buddhist calendar
 * support.)
 *
 * @since Alpha cdk-1.1
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
