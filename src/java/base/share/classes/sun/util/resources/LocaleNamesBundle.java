/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.util.resources;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Implements ResourceBundle for LocaleNames bundles that don't provide
 * the complete set of locale names.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
public abstract class LocaleNamesBundle extends OpenListResourceBundle {

    protected LocaleNamesBundle() {
    }
}
