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

package java.base.share.classes.java.util;

/**
 * Signals that a resource is missing.
 * @see java.lang.Exception
 * @see ResourceBundle
 * @author      Mark Davis
 * @since       1.1
 */
public class MissingResourceException extends RuntimeException {

    /**
     * Constructs a MissingResourceException with the specified information.
     * A detail message is a String that describes this particular exception.
     * @param s the detail message
     * @param className the name of the resource class
     * @param key the key for the missing resource.
     */
    public MissingResourceException(String s, String className, String key) {
        super(s);
        this.className = className;
        this.key = key;
    }

    /**
     * Constructs a {@code MissingResourceException} with
     * {@code message}, {@code className}, {@code key},
     * and {@code cause}. This constructor is package private for
     * use by {@code ResourceBundle.getBundle}.
     *
     * @param message
     *        the detail message
     * @param className
     *        the name of the resource class
     * @param key
     *        the key for the missing resource.
     * @param cause
     *        the cause (which is saved for later retrieval by the
     *        {@link Throwable#getCause()} method). (A null value is
     *        permitted, and indicates that the cause is nonexistent
     *        or unknown.)
     */
    MissingResourceException(String message, String className, String key, Throwable cause) {
        super(message, cause);
        this.className = className;
        this.key = key;
    }

    /**
     * Gets parameter passed by constructor.
     *
     * @return the name of the resource class
     */
    public String getClassName() {
        return className;
    }

    /**
     * Gets parameter passed by constructor.
     *
     * @return the key for the missing resource
     */
    public String getKey() {
        return key;
    }

    //============ privates ============

    // serialization compatibility with JDK1.1
    @java.io.Serial
    private static final long serialVersionUID = -4876345176062000401L;

    /**
     * The class name of the resource bundle requested by the user.
     * @serial
     */
    private String className;

    /**
     * The name of the specific resource requested by the user.
     * @serial
     */
    private String key;
}
