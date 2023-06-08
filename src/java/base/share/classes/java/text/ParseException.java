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
 * Signals that an error has been reached unexpectedly
 * while parsing.
 * @see java.lang.Exception
 * @see java.base.share.classes.java.text.Format
 * @see java.base.share.classes.java.text.FieldPosition
 * @author      Mark Davis
 * @since 1.1
 */
public class ParseException extends Exception {

    @java.io.Serial
    private static final long serialVersionUID = 2703218443322787634L;

    /**
     * Constructs a ParseException with the specified detail message and
     * offset.
     * A detail message is a String that describes this particular exception.
     *
     * @param s the detail message
     * @param errorOffset the position where the error is found while parsing.
     */
    public ParseException(String s, int errorOffset) {
        super(s);
        this.errorOffset = errorOffset;
    }

    /**
     * Returns the position where the error was found.
     *
     * @return the position where the error was found
     */
    public int getErrorOffset () {
        return errorOffset;
    }

    //============ privates ============
    /**
     * The zero-based character offset into the string being parsed at which
     * the error was found during parsing.
     * @serial
     */
    private int errorOffset;
}
