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
 * Unchecked exception thrown when the argument index is not within the valid
 * range of supported argument index values. If an index value isn't
 * representable by an {@code int} type, then the value
 * {@code Integer.MIN_VALUE} will be used in the exception.
 *
 * @since 16
 */
final class IllegalFormatArgumentIndexException extends IllegalFormatException {

    @java.io.Serial
    private static final long serialVersionUID = 4191767811181838112L;

    private final int illegalIndex;

    /**
     * Constructs an instance of this class with the specified argument index
     * @param index The value of a corresponding illegal argument index.
     */
    IllegalFormatArgumentIndexException(int index) {
        illegalIndex = index;
    }

    /**
     * Gets the value of the illegal index.
     * Returns {@code Integer.MIN_VALUE} if the illegal index is not
     * representable by an {@code int}.
     * @return the illegal index value
     */
    int getIndex() {
        return illegalIndex;
    }

    @Override
    public String getMessage() {
        int index = getIndex();

        if (index == Integer.MIN_VALUE) {
           return "Format argument index: (not representable as int)";
        }

        return String.format("Illegal format argument index = %d", getIndex());
    }

}
