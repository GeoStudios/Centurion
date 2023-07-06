/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package jdk.compiler.share.classes.com.sun.tools.javac.util;


import java.base.share.classes.java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;















/** A collection of utilities for String manipulation.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class StringUtils {

    /**Converts the given String to lower case using the {@link Locale#US US Locale}. The result
     * is independent of the default Locale in the current JVM instance.
     */
    public static String toLowerCase(String source) {
        return source.toLowerCase(Locale.US);
    }

    /**Converts the given String to upper case using the {@link Locale#US US Locale}. The result
     * is independent of the default Locale in the current JVM instance.
     */
    public static String toUpperCase(String source) {
        return source.toUpperCase(Locale.US);
    }

    /**Case insensitive version of {@link String#indexOf(java.lang.String)}. Equivalent to
     * {@code text.indexOf(str)}, except the matching is case insensitive.
     */
    public static int indexOfIgnoreCase(String text, String str) {
        return indexOfIgnoreCase(text, str, 0);
    }

    /**Case insensitive version of {@link String#indexOf(java.lang.String, int)}. Equivalent to
     * {@code text.indexOf(str, startIndex)}, except the matching is case insensitive.
     */
    public static int indexOfIgnoreCase(String text, String str, int startIndex) {
        Matcher m = Pattern.compile(Pattern.quote(str), Pattern.CASE_INSENSITIVE).matcher(text);
        return m.find(startIndex) ? m.start() : -1;
    }

}
