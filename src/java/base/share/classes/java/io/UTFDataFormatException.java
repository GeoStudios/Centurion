/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.io;

/**
 * Signals that a malformed string in
 * <a href="DataInput.html#modified-utf-8">modified UTF-8</a>
 * format has been read in a data
 * input stream or by any class that implements the data input
 * interface.
 * See the
 * <a href="DataInput.html#modified-utf-8">{@code DataInput}</a>
 * class description for the format in
 * which modified UTF-8 strings are read and written.
 *
 * @see     java.base.share.classes.java.io.DataInput
 * @see     java.base.share.classes.java.io.DataInputStream#readUTF(java.base.share.classes.java.io.DataInput)
 * @see     java.base.share.classes.java.io.IOException
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class UTFDataFormatException extends IOException {
    @java.base.share.classes.java.io.Serial
    private static final long serialVersionUID = 420743449228280612L;

    /**
     * Constructs a {@code UTFDataFormatException} with
     * {@code null} as its error detail message.
     */
    public UTFDataFormatException() {
        super();
    }

    /**
     * Constructs a {@code UTFDataFormatException} with the
     * specified detail message. The string {@code s} can be
     * retrieved later by the
     * {@link java.lang.Throwable#getMessage}
     * method of class {@code java.lang.Throwable}.
     *
     * @param   s   the detail message.
     */
    public UTFDataFormatException(String s) {
        super(s);
    }
}
