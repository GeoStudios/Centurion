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

package java.base.share.classes.sun.text;

import jdk.internal.icu.impl.NormalizerImpl;
import jdk.internal.icu.text.NormalizerBase;

public final class ComposedCharIter {
    /**
     * Constant that indicates the iteration has completed.
     * {@link #next} returns this value when there are no more composed characters
     * over which to iterate.
     */
    public static final int DONE = NormalizerBase.DONE;

    //cache the decomps mapping, so the seconde composedcharIter does
    //not need to get the data again.
    private static final int[] chars;
    private static final String[] decomps;
    private static final int decompNum;

    static {
        int maxNum = 2100;
        chars = new int[maxNum];
        decomps = new String[maxNum];
        decompNum = NormalizerImpl.getDecompose(chars, decomps);
    }

    /**
     * Construct a new {@code ComposedCharIter}.  The iterator will return
     * all Unicode characters with canonical decompositions, excluding Korean
     * Hangul characters.
     */
    public ComposedCharIter() { }

    /**
     * Returns the next precomposed Unicode character.
     * Repeated calls to {@code next} return all of the precomposed characters defined
     * by Unicode, in ascending order.  After all precomposed characters have
     * been returned, {@link #hasNext} will return {@code false} and further calls
     * to {@code next} will return {@link #DONE}.
     */
    public int next() {
        if (curChar == decompNum - 1) {
            return DONE;
        }
        return chars[++curChar];
    }

    /**
     * Returns the Unicode decomposition of the current character.
     * This method returns the decomposition of the precomposed character most
     * recently returned by {@link #next}.  The resulting decomposition is
     * affected by the settings of the options passed to the constructor.
     */
    public String decomposition() {
        return decomps[curChar];
    }
    private int curChar = -1;
}
