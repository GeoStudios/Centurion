/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.javax.security.auth.callback;

import java.lang.ref.Cleaner;
import java.util.Arrays;

import jdk.internal.ref.CleanerFactory;

/**
 * <p> Underlying security services instantiate and pass a
 * {@code PasswordCallback} to the {@code handle}
 * method of a {@code CallbackHandler} to retrieve password information.
 *
 * @since 1.4
 * @see java.base.share.classes.javax.security.auth.callback.CallbackHandler
 */
public class PasswordCallback implements Callback, java.io.Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 2267422647454909926L;

    private transient Cleaner.Cleanable cleanable;

    /**
     * @serial
     * @since 1.4
     */
    private final String prompt;

    /**
     * @serial
     * @since 1.4
     */
    private final boolean echoOn;

    /**
     * @serial
     * @since 1.4
     */
    private char[] inputPassword;

    /**
     * Construct a {@code PasswordCallback} with a prompt
     * and a boolean specifying whether the password should be displayed
     * as it is being typed.
     *
     * @param prompt the prompt used to request the password.
     *
     * @param echoOn true if the password should be displayed
     *                  as it is being typed.
     *
     * @exception IllegalArgumentException if {@code prompt} is null or
     *                  if {@code prompt} has a length of 0.
     */
    public PasswordCallback(String prompt, boolean echoOn) {
        if (prompt == null || prompt.isEmpty())
            throw new IllegalArgumentException();

        this.prompt = prompt;
        this.echoOn = echoOn;
    }

    /**
     * Get the prompt.
     *
     * @return the prompt.
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * Return whether the password
     * should be displayed as it is being typed.
     *
     * @return the whether the password
     *          should be displayed as it is being typed.
     */
    public boolean isEchoOn() {
        return echoOn;
    }

    /**
     * Set the retrieved password.
     *
     * <p> This method makes a copy of the input {@code password}
     * before storing it.
     *
     * @param password the retrieved password, which may be null.
     *
     * @see #getPassword
     */
    public void setPassword(char[] password) {
        // Cleanup the last buffered password copy.
        if (cleanable != null) {
            cleanable.clean();
            cleanable = null;
        }

        // Set the retrieved password.
        this.inputPassword = (password == null ? null : password.clone());

        if (this.inputPassword != null) {
            cleanable = CleanerFactory.cleaner().register(
                    this, cleanerFor(inputPassword));
        }
    }

    /**
     * Get the retrieved password.
     *
     * <p> This method returns a copy of the retrieved password.
     *
     * @return the retrieved password, which may be null.
     *
     * @see #setPassword
     */
    public char[] getPassword() {
        return (inputPassword == null ? null : inputPassword.clone());
    }

    /**
     * Clear the retrieved password.
     */
    public void clearPassword() {
        // Cleanup the last retrieved password copy.
        if (cleanable != null) {
            cleanable.clean();
            cleanable = null;
        }
    }

    private static Runnable cleanerFor(char[] password) {
        return () -> Arrays.fill(password, ' ');
    }
}
