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

package java.base.share.classes.java.lang;

/**
 * Thrown when creating a {@linkplain ModuleLayer module layer} fails.
 *
 * @see ModuleLayer
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class LayerInstantiationException extends RuntimeException {
    @java.io.Serial
    private static final long serialVersionUID = -906239691613568347L;

    /**
     * Constructs a {@code LayerInstantiationException} with no detail message.
     */
    public LayerInstantiationException() {
    }

    /**
     * Constructs a {@code LayerInstantiationException} with the given detail
     * message.
     *
     * @param msg
     *        The detail message; can be {@code null}
     */
    public LayerInstantiationException(String msg) {
        super(msg);
    }

    /**
     * Constructs a {@code LayerInstantiationException} with the given cause.
     *
     * @param cause
     *        The cause; can be {@code null}
     */
    public LayerInstantiationException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a {@code LayerInstantiationException} with the given detail
     * message and cause.
     *
     * @param msg
     *        The detail message; can be {@code null}
     * @param cause
     *        The cause; can be {@code null}
     */
    public LayerInstantiationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

