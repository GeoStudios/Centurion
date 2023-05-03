/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang;

/**
 * Thrown by {@code String} methods to indicate that an index is either negative
 * or greater than the size of the string.  For some methods such as the
 * {@link String#charAt charAt} method, this exception also is thrown when the
 * index is equal to the size of the string.
 *
 * @see java.base.share.classes.java.lang.String#charAt(int)
 * @since Java 2
 * @author Logan Abernathy
 * @edited 24/4/2023
 */

public class StringIndexOutOfBoundsException extends IndexOutOfBoundsException {
    @java.io.Serial
    private static final long serialVersionUID = -6762910422159637258L;

    /**
     * Constructs a {@code StringIndexOutOfBoundsException} with no detail
     * message.
     */
    public StringIndexOutOfBoundsException() {
        super();
    }

    /**
     * Constructs a {@code StringIndexOutOfBoundsException} with the specified
     * detail message.
     *
     * @param s the detail message.
     */
    public StringIndexOutOfBoundsException(String s) {
        super(s);
    }

    /**
     * Constructs a new {@code StringIndexOutOfBoundsException} class with an
     * argument indicating the illegal index.
     *
     * <p>The index is included in this exception's detail message.  The
     * exact presentation format of the detail message is unspecified.
     *
     * @param index the illegal index.
     */
    public StringIndexOutOfBoundsException(int index) {
        super("String index out of range: " + index);
    }
}
