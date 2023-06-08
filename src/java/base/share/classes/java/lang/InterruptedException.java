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
 * Thrown when a thread is waiting, sleeping, or otherwise occupied,
 * and the thread is interrupted, either before or during the activity.
 * Occasionally a method may wish to test whether the current
 * thread has been interrupted, and if so, to immediately throw
 * this exception.  The following code can be used to achieve
 * this effect:
 * {@snippet lang=java :
 * if (Thread.interrupted())  // Clears interrupted status!
 *     throw new InterruptedException();
 * }
 *
 * @see     java.base.share.classes.java.lang.Object#wait()
 * @see     java.base.share.classes.java.lang.Object#wait(long)
 * @see     java.base.share.classes.java.lang.Object#wait(long, int)
 * @see     java.base.share.classes.java.lang.Thread#sleep(long)
 * @see     java.base.share.classes.java.lang.Thread#interrupt()
 * @see     java.base.share.classes.java.lang.Thread#interrupted()
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class InterruptedException extends Exception {
    @java.io.Serial
    private static final long serialVersionUID = 6700697376100628473L;

    /**
     * Constructs an {@code InterruptedException} with no detail  message.
     */
    public InterruptedException() {
        super();
    }

    /**
     * Constructs an {@code InterruptedException} with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public InterruptedException(String s) {
        super(s);
    }
}
