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

package java.base.share.classes.jdk.internal.icu.text;

public interface Replaceable {
    /**
     * Returns the number of 16-bit code units in the text.
     * @return number of 16-bit code units in text
     * @stable ICU 2.0
     */
    int length();

    /**
     * Returns the 16-bit code unit at the given offset into the text.
     * @param offset an integer between 0 and <code>length()</code>-1
     * inclusive
     * @return 16-bit code unit of text at given offset
     * @stable ICU 2.0
     */
    char charAt(int offset);

    /**
     * Copies characters from this object into the destination
     * character array.  The first character to be copied is at index
     * <code>srcStart</code>; the last character to be copied is at
     * index <code>srcLimit-1</code> (thus the total number of
     * characters to be copied is <code>srcLimit-srcStart</code>). The
     * characters are copied into the subarray of <code>dst</code>
     * starting at index <code>dstStart</code> and ending at index
     * <code>dstStart + (srcLimit-srcStart) - 1</code>.
     *
     * @param srcStart the beginning index to copy, inclusive;
     *        {@code 0 <= start <= limit}.
     * @param srcLimit the ending index to copy, exclusive;
     *        {@code start <= limit <= length()}.
     * @param dst the destination array.
     * @param dstStart the start offset in the destination array.
     * @stable ICU 2.0
     */
    void getChars(int srcStart, int srcLimit, char dst[], int dstStart);
}
