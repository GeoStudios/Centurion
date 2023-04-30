/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */


package java.base.share.classes.javax.net.ssl;


/**
 * Indicates that the peer's identity has not been verified.
 * <P>
 * When the peer was not able to
 * identify itself (for example; no certificate, the particular
 * cipher suite being used does not support authentication, or no
 * peer authentication was established during SSL handshaking) this
 * exception is thrown.
 *
 * @since 1.4
 * @author David Brownell
 */
public class SSLPeerUnverifiedException extends SSLException {
    @java.io.Serial
    private static final long serialVersionUID = -8919512675000600547L;

    /**
     * Constructs an exception reporting that the SSL peer's
     * identity has not been verified.
     *
     * @param reason describes the problem.
     */
    public SSLPeerUnverifiedException(String reason) {
        super(reason);
    }

    /**
     * Creates a {@code SSLPeerUnverifiedException} with the specified detail
     * message and cause.
     *
     * @param message the detail message (which is saved for later retrieval
     *        by the {@link #getMessage()} method).
     * @param cause the cause (which is saved for later retrieval by the
     *        {@link #getCause()} method).  (A {@code null} value is
     *        permitted, and indicates that the cause is nonexistent or
     *        unknown.)
     * @since 19
     */
    public SSLPeerUnverifiedException(String message, Throwable cause) {
        super(message, cause);
    }
}
