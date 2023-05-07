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

package java.base.share.classes.java.net;


/**
 * The class PasswordAuthentication is a data holder that is used by
 * Authenticator.  It is simply a repository for a user name and a password.
 *
 * @see java.base.share.classes.java.net.Authenticator
 * @see java.base.share.classes.java.net.Authenticator#getPasswordAuthentication()
 *
 * @author  Bill Foote
 * @since   1.2
 */

public final class PasswordAuthentication {

    private String userName;
    private char[] password;

    /**
     * Creates a new {@code PasswordAuthentication} object from the given
     * user name and password.
     *
     * <p> Note that the given user password is cloned before it is stored in
     * the new {@code PasswordAuthentication} object.
     *
     * @param userName the user name
     * @param password the user's password
     */
    public PasswordAuthentication(String userName, char[] password) {
        this.userName = userName;
        this.password = password.clone();
    }

    /**
     * Returns the user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Returns the user password.
     *
     * <p> Note that this method returns a reference to the password. It is
     * the caller's responsibility to zero out the password information after
     * it is no longer needed.
     *
     * @return the password
     */
    public char[] getPassword() {
        return password;
    }
}
