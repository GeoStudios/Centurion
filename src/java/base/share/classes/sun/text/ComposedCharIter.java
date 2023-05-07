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

package java.base.share.classes.sun.text;

import java.base.share.classes.jdk.internal.icu.impl.NormalizerImpl;
import java.base.share.classes.jdk.internal.icu.text.NormalizerBase;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
public final class ComposedCharIter {
    /**
     * Constant that indicates the iteration has completed.
     * {@link #next} returns this value when there are no more composed characters
     * over which to iterate.
     */
    public static final int DONE = NormalizerBase.DONE;

    //cache the decomps mapping, so the seconde composedcharIter does
    //not need to get the data again.
    private static int chars[];
    private static String decomps[];
    private static int decompNum;

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
