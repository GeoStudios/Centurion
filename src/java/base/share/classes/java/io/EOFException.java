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
 * Signals that an end of file or end of stream has been reached
 * unexpectedly during input.
 * <p>
 * This exception is mainly used by data input streams to signal end of
 * stream. Note that many other input operations return a special value on
 * end of stream rather than throwing an exception.
 *
 * @see     java.base.share.classes.java.io.DataInputStream
 * @see     java.base.share.classes.java.io.IOException
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */
public class EOFException extends IOException {
    @java.base.share.classes.java.io.Serial
    private static final long serialVersionUID = 6433858223774886977L;

    /**
     * Constructs an {@code EOFException} with {@code null}
     * as its error detail message.
     */
    public EOFException() {
        super();
    }

    /**
     * Constructs an {@code EOFException} with the specified detail
     * message. The string {@code s} may later be retrieved by the
     * {@link java.lang.Throwable#getMessage} method of class
     * {@code java.lang.Throwable}.
     *
     * @param   s   the detail message.
     */
    public EOFException(String s) {
        super(s);
    }
}
