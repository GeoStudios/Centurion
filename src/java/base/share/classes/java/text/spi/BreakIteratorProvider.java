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

package java.base.share.classes.java.text.spi;

import java.text.BreakIterator;
import java.util.Locale;
import java.util.spi.LocaleServiceProvider;

/**
 * An abstract class for service providers that
 * provide concrete implementations of the
 * {@link java.text.BreakIterator BreakIterator} class.
 *
 * @since        1.6
 */
public abstract class BreakIteratorProvider extends LocaleServiceProvider {

    /**
     * Sole constructor.  (For invocation by subclass constructors, typically
     * implicit.)
     */
    protected BreakIteratorProvider() {
    }

    /**
     * Returns a new {@code BreakIterator} instance
     * for <a href="../BreakIterator.html#word">word breaks</a>
     * for the given locale.
     * @param locale the desired locale
     * @return A break iterator for word breaks
     * @throws    NullPointerException if {@code locale} is null
     * @throws    IllegalArgumentException if {@code locale} isn't
     *     one of the locales returned from
     *     {@link java.util.spi.LocaleServiceProvider#getAvailableLocales()
     *     getAvailableLocales()}.
     * @see java.text.BreakIterator#getWordInstance(java.util.Locale)
     */
    public abstract BreakIterator getWordInstance(Locale locale);

    /**
     * Returns a new {@code BreakIterator} instance
     * for <a href="../BreakIterator.html#line">line breaks</a>
     * for the given locale.
     * @param locale the desired locale
     * @return A break iterator for line breaks
     * @throws    NullPointerException if {@code locale} is null
     * @throws    IllegalArgumentException if {@code locale} isn't
     *     one of the locales returned from
     *     {@link java.util.spi.LocaleServiceProvider#getAvailableLocales()
     *     getAvailableLocales()}.
     * @see java.text.BreakIterator#getLineInstance(java.util.Locale)
     */
    public abstract BreakIterator getLineInstance(Locale locale);

    /**
     * Returns a new {@code BreakIterator} instance
     * for <a href="../BreakIterator.html#character">character breaks</a>
     * for the given locale.
     * @param locale the desired locale
     * @return A break iterator for character breaks
     * @throws    NullPointerException if {@code locale} is null
     * @throws    IllegalArgumentException if {@code locale} isn't
     *     one of the locales returned from
     *     {@link java.util.spi.LocaleServiceProvider#getAvailableLocales()
     *     getAvailableLocales()}.
     * @see java.text.BreakIterator#getCharacterInstance(java.util.Locale)
     */
    public abstract BreakIterator getCharacterInstance(Locale locale);

    /**
     * Returns a new {@code BreakIterator} instance
     * for <a href="../BreakIterator.html#sentence">sentence breaks</a>
     * for the given locale.
     * @param locale the desired locale
     * @return A break iterator for sentence breaks
     * @throws    NullPointerException if {@code locale} is null
     * @throws    IllegalArgumentException if {@code locale} isn't
     *     one of the locales returned from
     *     {@link java.util.spi.LocaleServiceProvider#getAvailableLocales()
     *     getAvailableLocales()}.
     * @see java.text.BreakIterator#getSentenceInstance(java.util.Locale)
     */
    public abstract BreakIterator getSentenceInstance(Locale locale);
}
