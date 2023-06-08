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

package java.base.share.classes.javax.security.auth.callback;

/**
 * <p> Underlying security services instantiate and pass a
 * {@code NameCallback} to the {@code handle}
 * method of a {@code CallbackHandler} to retrieve name information.
 *
 * @since 1.4
 * @see java.base.share.classes.javax.security.auth.callback.CallbackHandler
 */
public class NameCallback implements Callback, java.io.Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 3770938795909392253L;

    /**
     * @serial
     * @since 1.4
     */
    private final String prompt;
    /**
     * @serial
     * @since 1.4
     */
    private final String defaultName;
    /**
     * @serial
     * @since 1.4
     */
    private String inputName;

    /**
     * Construct a {@code NameCallback} with a prompt.
     *
     * @param prompt the prompt used to request the name.
     *
     * @exception IllegalArgumentException if {@code prompt} is null
     *                  or if {@code prompt} has a length of 0.
     */
    public NameCallback(String prompt) {
        if (prompt == null || prompt.isEmpty())
            throw new IllegalArgumentException();
        this.prompt = prompt;
        this.defaultName = null;
    }

    /**
     * Construct a {@code NameCallback} with a prompt
     * and default name.
     *
     * @param prompt the prompt used to request the information.
     *
     * @param defaultName the name to be used as the default name displayed
     *                  with the prompt.
     *
     * @exception IllegalArgumentException if {@code prompt} is null,
     *                  if {@code prompt} has a length of 0,
     *                  if {@code defaultName} is null,
     *                  or if {@code defaultName} has a length of 0.
     */
    public NameCallback(String prompt, String defaultName) {
        if (prompt == null || prompt.isEmpty() ||
            defaultName == null || defaultName.isEmpty())
            throw new IllegalArgumentException();

        this.prompt = prompt;
        this.defaultName = defaultName;
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
     * Get the default name.
     *
     * @return the default name, or null if this {@code NameCallback}
     *          was not instantiated with a {@code defaultName}.
     */
    public String getDefaultName() {
        return defaultName;
    }

    /**
     * Set the retrieved name.
     *
     * @param name the retrieved name (which may be null).
     *
     * @see #getName
     */
    public void setName(String name) {
        this.inputName = name;
    }

    /**
     * Get the retrieved name.
     *
     * @return the retrieved name (which may be null)
     *
     * @see #setName
     */
    public String getName() {
        return inputName;
    }
}
