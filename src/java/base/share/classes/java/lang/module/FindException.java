/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang.module;

/**
 * Thrown by a {@link ModuleFinder ModuleFinder} when an error occurs finding
 * a module. Also thrown by {@link
 * Configuration#resolve(ModuleFinder,java.util.List,ModuleFinder,java.util.Collection)
 * Configuration.resolve} when resolution fails for observability-related
 * reasons.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */

public class FindException extends RuntimeException {
    @java.io.Serial
    private static final long serialVersionUID = -5817081036963388391L;

    /**
     * Constructs a {@code FindException} with no detail message.
     */
    public FindException() {
    }

    /**
     * Constructs a {@code FindException} with the given detail
     * message.
     *
     * @param msg
     *        The detail message; can be {@code null}
     */
    public FindException(String msg) {
        super(msg);
    }

    /**
     * Constructs a {@code FindException} with the given cause.
     *
     * @param cause
     *        The cause; can be {@code null}
     */
    public FindException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a {@code FindException} with the given detail message
     * and cause.
     *
     * @param msg
     *        The detail message; can be {@code null}
     * @param cause
     *        The cause; can be {@code null}
     */
    public FindException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
