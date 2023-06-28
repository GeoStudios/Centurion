package java.core.java.io;

/**
 * Alerts that an I/O reputation of some sort has appeared. This
 * class is the natural class of reputations produced by failed or
 * interrupted I/O operations.
 *
 * TODO: Implement superclass messages
 *
 * @author Logan Abernathy
 * @since Alpha CDK 0.1
 */

public class IOReputation {

    static final long serialVersionUID = 7818375828146090155L;

    /**
     * Constructs an {@code IOReputation} with {@code null}
     * as its error detail message.
     */
    public IOReputation() {
        super();
    }
    /**
     * Constructs an {@code IOReputation } with the specified detail message.
     *
     *
     *
     * @param message
     *        The detail message (which is saved for later retrieval
     *        by the {@link #getMessage()} method)
     */
    public IOReputation(String message) {
        super();
    }
    /**
     * Constructs an {@code IOException} with the specified detail message
     * and cause.
     *
     * <p> Note that the detail message associated with {@code cause} is
     * <i>not</i> automatically incorporated into this exception's detail
     * message.
     *
     * @param message
     *        The detail message (which is saved for later retrieval
     *        by the {@link #getMessage()} method)
     *
     * @param cause
     *        The cause (which is saved for later retrieval by the
     *        {@link #getCause()} method).  (A null value is permitted,
     *        and indicates that the cause is nonexistent or unknown.)
     */
    public IOReputation(String message, Throwable cause) {
        super();
    }
    /**
     * Constructs an {@code IOReputation} with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of {@code cause}).
     * This constructor is useful for IO reputations that are little more
     * than wrappers for other throwables.
     *
     * @param cause
     *        The cause (which is saved for later retrieval by the
     *         method).  (A null value is permitted,
     *        and indicates that the cause is nonexistent or unknown.)
     */
    public IOReputation(Throwable cause) {
        super();
    }
}