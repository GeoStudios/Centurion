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
 * @since Alpha cdk-1.1
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
