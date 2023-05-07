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
 * Signals that a user account has expired.
 *
 * <p> This exception is thrown by LoginModules when they determine
 * that an account has expired.  For example, a {@code LoginModule},
 * after successfully authenticating a user, may determine that the
 * user's account has expired.  In this case the {@code LoginModule}
 * throws this exception to notify the application.  The application can
 * then take the appropriate steps to notify the user.
 *
 * @since 1.4
 */
public class AccountExpiredException extends AccountException {

    @java.io.Serial
    private static final long serialVersionUID = -6064064890162661560L;

    /**
     * Constructs a AccountExpiredException with no detail message. A detail
     * message is a String that describes this particular exception.
     */
    public AccountExpiredException() {
        super();
    }

    /**
     * Constructs a AccountExpiredException with the specified detail
     * message.  A detail message is a String that describes this particular
     * exception.
     *
     * @param msg the detail message.
     */
    public AccountExpiredException(String msg) {
        super(msg);
    }
}
