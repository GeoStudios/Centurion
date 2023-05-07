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

package java.base.share.classes.java.util;

/**
 * FormattableFlags are passed to the {@link Formattable#formatTo
 * Formattable.formatTo()} method and modify the output format for {@linkplain
 * Formattable Formattables}.  Implementations of {@link Formattable} are
 * responsible for interpreting and validating any flags.
 *
 * @since  1.5
 */
public class FormattableFlags {

    // Explicit instantiation of this class is prohibited.
    private FormattableFlags() {}

    /**
     * Left-justifies the output.  Spaces (<code>'&#92;u0020'</code>) will be added
     * at the end of the converted value as required to fill the minimum width
     * of the field.  If this flag is not set then the output will be
     * right-justified.
     *
     * <p> This flag corresponds to {@code '-'} (<code>'&#92;u002d'</code>) in
     * the format specifier.
     */
    public static final int LEFT_JUSTIFY = 1<<0; // '-'

    /**
     * Converts the output to upper case according to the rules of the
     * {@linkplain java.base.share.classes.java.util.Locale locale} given during creation of the
     * {@code formatter} argument of the {@link Formattable#formatTo
     * formatTo()} method.  The output should be equivalent the following
     * invocation of {@link String#toUpperCase(java.base.share.classes.java.util.Locale)}
     *
     * <pre>
     *     out.toUpperCase() </pre>
     *
     * <p> This flag corresponds to {@code 'S'} (<code>'&#92;u0053'</code>) in
     * the format specifier.
     */
    public static final int UPPERCASE = 1<<1;    // 'S'

    /**
     * Requires the output to use an alternate form.  The definition of the
     * form is specified by the {@code Formattable}.
     *
     * <p> This flag corresponds to {@code '#'} (<code>'&#92;u0023'</code>) in
     * the format specifier.
     */
    public static final int ALTERNATE = 1<<2;    // '#'
}
