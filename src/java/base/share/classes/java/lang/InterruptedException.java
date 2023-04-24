/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.lang;

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
 * @see     java.lang.Object#wait()
 * @see     java.lang.Object#wait(long)
 * @see     java.lang.Object#wait(long, int)
 * @see     java.lang.Thread#sleep(long)
 * @see     java.lang.Thread#interrupt()
 * @see     java.lang.Thread#interrupted()
 * @since Pre Java 1
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
