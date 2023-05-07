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

package java.base.share.classes.jdk.internal.util.xml;

public class XMLStreamException extends Exception {
    @java.io.Serial
    private static final long serialVersionUID = 1L;


    protected Throwable nested;

    /**
     * Default constructor
     */
    public XMLStreamException() {
        super();
    }

    /**
     * Construct an exception with the associated message.
     *
     * @param msg the message to report
     */
    public XMLStreamException(String msg) {
        super(msg);
    }

    /**
     * Construct an exception with the associated exception
     *
     * @param th a nested exception
     */
    public XMLStreamException(Throwable th) {
        super(th);
        nested = th;
    }

    /**
     * Construct an exception with the associated message and exception
     *
     * @param th a nested exception
     * @param msg the message to report
     */
    public XMLStreamException(String msg, Throwable th) {
        super(msg, th);
        nested = th;
    }

    /**
     * Gets the nested exception.
     *
     * @return Nested exception
     */
    public Throwable getNestedException() {
        return nested;
    }
}
