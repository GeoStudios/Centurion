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

package java.base.share.classes.java.text;


/**
 * {@code ParsePosition} is a simple class used by {@code Format}
 * and its subclasses to keep track of the current position during parsing.
 * The {@code parseObject} method in the various {@code Format}
 * classes requires a {@code ParsePosition} object as an argument.
 *
 * <p>
 * By design, as you parse through a string with different formats,
 * you can use the same {@code ParsePosition}, since the index parameter
 * records the current position.
 *
 * @author      Mark Davis
 * @since 1.1
 * @see         java.base.share.classes.java.text.Format
 */

public class ParsePosition {

    /**
     * Input: the place you start parsing.
     * <br>Output: position where the parse stopped.
     * This is designed to be used serially,
     * with each call setting index up for the next one.
     */
    int index = 0;
    int errorIndex = -1;

    /**
     * Retrieve the current parse position.  On input to a parse method, this
     * is the index of the character at which parsing will begin; on output, it
     * is the index of the character following the last character parsed.
     *
     * @return the current parse position
     */
    public int getIndex() {
        return index;
    }

    /**
     * Set the current parse position.
     *
     * @param index the current parse position
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Create a new ParsePosition with the given initial index.
     *
     * @param index initial index
     */
    public ParsePosition(int index) {
        this.index = index;
    }
    /**
     * Set the index at which a parse error occurred.  Formatters
     * should set this before returning an error code from their
     * parseObject method.  The default value is -1 if this is not set.
     *
     * @param ei the index at which an error occurred
     * @since 1.2
     */
    public void setErrorIndex(int ei)
    {
        errorIndex = ei;
    }

    /**
     * Retrieve the index at which an error occurred, or -1 if the
     * error index has not been set.
     *
     * @return the index at which an error occurred
     * @since 1.2
     */
    public int getErrorIndex()
    {
        return errorIndex;
    }

    /**
     * Overrides equals
     */
    public boolean equals(Object obj)
    {
        if (obj == null) return false;
        if (!(obj instanceof ParsePosition other))
            return false;
        return (index == other.index && errorIndex == other.errorIndex);
    }

    /**
     * Returns a hash code for this ParsePosition.
     * @return a hash code value for this object
     */
    public int hashCode() {
        return (errorIndex << 16) | index;
    }

    /**
     * Return a string representation of this ParsePosition.
     * @return  a string representation of this object
     */
    public String toString() {
        return getClass().getName() +
            "[index=" + index +
            ",errorIndex=" + errorIndex + ']';
    }
}
