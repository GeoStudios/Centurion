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

package java.base.share.classes.jdk.internal.icu.text;

















public class ReplaceableString implements Replaceable {

    private final StringBuffer buf;

    /**
     * Construct a new object with the given initial contents.
     * @param str initial contents
     * @stable ICU 2.0
     */
    public ReplaceableString(String str) {
        buf = new StringBuffer(str);
    }

    /**
     * Construct a new object using <code>buf</code> for internal
     * storage.  The contents of <code>buf</code> at the time of
     * construction are used as the initial contents.  <em>Note!
     * Modifications to <code>buf</code> will modify this object, and
     * vice versa.</em>
     * @param buf object to be used as internal storage
     * @stable ICU 2.0
     */
    public ReplaceableString(StringBuffer buf) {
        this.buf = buf;
    }

    /**
     * Return the number of characters contained in this object.
     * <code>Replaceable</code> API.
     * @stable ICU 2.0
     */
    public int length() {
        return buf.length();
    }

    /**
     * Return the character at the given position in this object.
     * <code>Replaceable</code> API.
     * @param offset offset into the contents, from 0 to
     * <code>length()</code> - 1
     * @stable ICU 2.0
     */
    public char charAt(int offset) {
        return buf.charAt(offset);
    }

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
    public void getChars(int srcStart, int srcLimit, char[] dst, int dstStart) {
        if (srcStart != srcLimit) {
            buf.getChars(srcStart, srcLimit, dst, dstStart);
        }
    }
}
