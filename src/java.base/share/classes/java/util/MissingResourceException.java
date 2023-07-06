/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package java.base.share.classes.java.util;

/**
 * Signals that a resource is missing.
 * @see java.lang.Exception
 * @see ResourceBundle
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
     *        {@link Throwable.getCause()} method). (A null value is
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
    private final String className;

    /**
     * The name of the specific resource requested by the user.
     * @serial
     */
    private final String key;
}
