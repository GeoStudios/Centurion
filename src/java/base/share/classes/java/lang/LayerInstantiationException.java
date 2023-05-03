/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang;

/**
 * Thrown when creating a {@linkplain ModuleLayer module layer} fails.
 *
 * @see ModuleLayer
 * @since Java 2
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

