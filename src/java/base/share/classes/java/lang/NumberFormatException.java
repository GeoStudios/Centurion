/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang;

/**
 * Thrown to indicate that the application has attempted to convert
 * a string to one of the numeric types, but that the string does not
 * have the appropriate format.
 *
 * @see     java.base.share.classes.java.lang.Integer#parseInt(String)
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class NumberFormatException extends IllegalArgumentException {
    @java.io.Serial
    static final long serialVersionUID = -2848938806368998894L;

    /**
     * Constructs a {@code NumberFormatException} with no detail message.
     */
    public NumberFormatException () {
        super();
    }

    /**
     * Constructs a {@code NumberFormatException} with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public NumberFormatException (String s) {
        super (s);
    }

    /**
     * Factory method for making a {@code NumberFormatException}
     * given the specified input which caused the error.
     *
     * @param   s   the input causing the error
     */
    static NumberFormatException forInputString(String s, int radix) {
        return new NumberFormatException("For input string: \"" + s + "\"" +
                                         (radix == 10 ?
                                          "" :
                                          " under radix " + radix));
    }

    /**
     * Factory method for making a {@code NumberFormatException}
     * given the specified input which caused the error.
     *
     * @param   s   the input causing the error
     * @param   beginIndex   the beginning index, inclusive.
     * @param   endIndex     the ending index, exclusive.
     * @param   errorIndex   the index of the first error in s
     */
    static NumberFormatException forCharSequence(CharSequence s,
            int beginIndex, int endIndex, int errorIndex) {
        return new NumberFormatException("Error at index "
                + (errorIndex - beginIndex) + " in: \""
                + s.subSequence(beginIndex, endIndex) + "\"");
    }
}
