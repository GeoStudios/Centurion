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

package java.base.share.classes.javax.security.auth.login;

/**
 * Signals that user authentication failed.
 *
 * <p> This exception is thrown by LoginModules if authentication failed.
 * For example, a {@code LoginModule} throws this exception if
 * the user entered an incorrect password.
 *
 * @since 1.4
 */
public class FailedLoginException extends LoginException {

    @java.io.Serial
    private static final long serialVersionUID = 802556922354616286L;

    /**
     * Constructs a FailedLoginException with no detail message. A detail
     * message is a String that describes this particular exception.
     */
    public FailedLoginException() {
        super();
    }

    /**
     * Constructs a FailedLoginException with the specified detail
     * message.  A detail message is a String that describes this particular
     * exception.
     *
     * @param msg the detail message.
     */
    public FailedLoginException(String msg) {
        super(msg);
    }
}
