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

package java.base.share.classes.com.sun.crypto.provider;

import javax.crypto.ShortBufferException;

/**
 * Padding interface.
 *
 * This interface is implemented by general-purpose padding schemes, such as
 * the one described in PKCS#5.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 *
 *
 * @see PKCS5Padding
 */

interface Padding {

    /**
     * Adds the given number of padding bytes to the data input.
     * The value of the padding bytes is determined
     * by the specific padding mechanism that implements this
     * interface.
     *
     * @param in the input buffer with the data to pad
     * @param off the offset in <code>in</code> where the padding bytes
     *  are appended
     * @param len the number of padding bytes to add
     *
     * @exception ShortBufferException if <code>in</code> is too small to hold
     * the padding bytes
     */
    void padWithLen(byte[] in, int off, int len) throws ShortBufferException;

    /**
     * Returns the index where padding starts.
     *
     * <p>Given a buffer with data and their padding, this method returns the
     * index where the padding starts.
     *
     * @param in the buffer with the data and their padding
     * @param off the offset in <code>in</code> where the data starts
     * @param len the length of the data and their padding
     *
     * @return the index where the padding starts, or -1 if the input is
     * not properly padded
     */
    int unpad(byte[] in, int off, int len);

    /**
     * Determines how long the padding will be for a given input length.
     *
     * @param len the length of the data to pad
     *
     * @return the length of the padding
     */
    int padLength(int len);
}
