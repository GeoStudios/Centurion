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

package java.base.share.classes.java.util.spi;

import java.util.ResourceBundle;

/**
 * An interface for service providers that provide implementations of {@link
 * java.util.ResourceBundle.Control}. The <a
 * href="../ResourceBundle.html#default_behavior">default resource bundle loading
 * behavior</a> of the {@code ResourceBundle.getBundle} factory methods that take
 * no {@link java.util.ResourceBundle.Control} instance can be modified with {@code
 * ResourceBundleControlProvider} implementations.
 *
 * <p>Provider implementations are loaded from the application's class path
 * using {@link java.util.ServiceLoader} at the first invocation of the
 * {@code ResourceBundle.getBundle} factory method that takes no
 * {@link java.util.ResourceBundle.Control} instance.
 *
 * <p>All {@code ResourceBundleControlProvider}s are ignored in named modules.
 *
 * @author Masayoshi Okutsu
 * @since 1.8
 * @revised 9
 * @see ResourceBundle#getBundle(String, java.util.Locale, ClassLoader, ResourceBundle.Control)
 *      ResourceBundle.getBundle
 * @see java.util.ServiceLoader#load(Class)
 */
public interface ResourceBundleControlProvider {
    /**
     * Returns a {@code ResourceBundle.Control} instance that is used
     * to handle resource bundle loading for the given {@code
     * baseName}. This method must return {@code null} if the given
     * {@code baseName} isn't handled by this provider.
     *
     * @param baseName the base name of the resource bundle
     * @return a {@code ResourceBundle.Control} instance,
     *         or {@code null} if the given {@code baseName} is not
     *         applicable to this provider.
     * @throws NullPointerException if {@code baseName} is {@code null}
     */
    public ResourceBundle.Control getControl(String baseName);
}
