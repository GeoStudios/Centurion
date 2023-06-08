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
 * Signals that an I/O operation has been interrupted. An
 * {@code InterruptedIOException} is thrown to indicate that an
 * input or output transfer has been terminated because the thread
 * performing it was interrupted. The field {@link #bytesTransferred}
 * indicates how many bytes were successfully transferred before
 * the interruption occurred.
 *
 * @see     java.base.share.classes.java.io.InputStream
 * @see     java.base.share.classes.java.io.OutputStream
 * @see     java.lang.Thread#interrupt()
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class InterruptedIOException extends IOException {
    @java.base.share.classes.java.io.Serial
    private static final long serialVersionUID = 4020568460727500567L;

    /**
     * Constructs an {@code InterruptedIOException} with
     * {@code null} as its error detail message.
     */
    public InterruptedIOException() {
        super();
    }

    /**
     * Constructs an {@code InterruptedIOException} with the
     * specified detail message. The string {@code s} can be
     * retrieved later by the
     * {@link java.lang.Throwable#getMessage}
     * method of class {@code java.lang.Throwable}.
     *
     * @param   s   the detail message.
     */
    public InterruptedIOException(String s) {
        super(s);
    }

    /**
     * Reports how many bytes had been transferred as part of the I/O
     * operation before it was interrupted.
     *
     * @serial
     */
    public int bytesTransferred = 0;
}
