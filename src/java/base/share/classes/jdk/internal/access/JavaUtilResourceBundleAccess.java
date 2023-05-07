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

package java.base.share.classes.jdk.internal.access;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Provides access to non-public methods in java.util.ResourceBundle.
 */
public interface JavaUtilResourceBundleAccess {
    /**
     * Sets the bundle's parent to the given parent.
     */
    void setParent(ResourceBundle bundle, ResourceBundle parent);

    /**
     * Returns the parent of the given bundle or null if the bundle has no parent.
     */
    ResourceBundle getParent(ResourceBundle bundle);

    /**
     * Sets the bundle's locale to the given locale.
     */
    void setLocale(ResourceBundle bundle, Locale locale);

    /**
     * Sets the bundle's base name to the given name.
     */
    void setName(ResourceBundle bundle, String name);

    /**
     * Returns a {@code ResourceBundle} of the given baseName and locale
     * loaded on behalf of the given module with no caller module
     * access check.
     */
    ResourceBundle getBundle(String baseName, Locale locale, Module module);

    /**
     * Instantiates a {@code ResourceBundle} of the given bundle class.
     */
    ResourceBundle newResourceBundle(Class<? extends ResourceBundle> bundleClass);
}
