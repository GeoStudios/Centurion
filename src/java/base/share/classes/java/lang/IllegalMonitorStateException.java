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

package java.base.share.classes.java.lang;

/**
 * Thrown to indicate that a thread has attempted to wait on an
 * object's monitor or to notify other threads waiting on an object's
 * monitor without owning the specified monitor.
 *
 * @see     java.base.share.classes.java.lang.Object#notify()
 * @see     java.base.share.classes.java.lang.Object#notifyAll()
 * @see     java.base.share.classes.java.lang.Object#wait()
 * @see     java.base.share.classes.java.lang.Object#wait(long)
 * @see     java.base.share.classes.java.lang.Object#wait(long, int)
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class IllegalMonitorStateException extends RuntimeException {
    @java.io.Serial
    private static final long serialVersionUID = 3713306369498869069L;

    /**
     * Constructs an {@code IllegalMonitorStateException} with no
     * detail message.
     */
    public IllegalMonitorStateException() {
        super();
    }

    /**
     * Constructs an {@code IllegalMonitorStateException} with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public IllegalMonitorStateException(String s) {
        super(s);
    }
}
