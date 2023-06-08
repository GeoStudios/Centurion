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

package java.base.share.classes.java.security.cert;

import java.security.GeneralSecurityException;

/**
 * An exception indicating one of a variety of problems retrieving
 * certificates and CRLs from a {@code CertStore}.
 * <p>
 * A {@code CertStoreException} provides support for wrapping
 * exceptions. The {@link #getCause getCause} method returns the throwable,
 * if any, that caused this exception to be thrown.
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * Unless otherwise specified, the methods defined in this class are not
 * thread-safe. Multiple threads that need to access a single
 * object concurrently should synchronize amongst themselves and
 * provide the necessary locking. Multiple threads each manipulating
 * separate objects need not synchronize.
 *
 * @see CertStore
 *
 * @since       1.4
 * @author      Sean Mullan
 */
public class CertStoreException extends GeneralSecurityException {

    @java.io.Serial
    private static final long serialVersionUID = 2395296107471573245L;

    /**
     * Creates a {@code CertStoreException} with {@code null} as
     * its detail message.
     */
    public CertStoreException() {
        super();
    }

    /**
     * Creates a {@code CertStoreException} with the given detail
     * message. A detail message is a {@code String} that describes this
     * particular exception.
     *
     * @param msg the detail message
     */
    public CertStoreException(String msg) {
        super(msg);
    }

    /**
     * Creates a {@code CertStoreException} that wraps the specified
     * throwable. This allows any exception to be converted into a
     * {@code CertStoreException}, while retaining information about the
     * cause, which may be useful for debugging. The detail message is
     * set to ({@code cause==null ? null : cause.toString()}) (which
     * typically contains the class and detail message of cause).
     *
     * @param cause the cause (which is saved for later retrieval by the
     * {@link #getCause getCause()} method). (A {@code null} value is
     * permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public CertStoreException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a {@code CertStoreException} with the specified detail
     * message and cause.
     *
     * @param msg the detail message
     * @param cause the cause (which is saved for later retrieval by the
     * {@link #getCause getCause()} method). (A {@code null} value is
     * permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public CertStoreException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
