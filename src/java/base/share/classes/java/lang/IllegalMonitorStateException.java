/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
