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

package java.base.share.classes.java.lang.module;

/**
 * Thrown when resolving a set of modules, or resolving a set of modules with
 * service binding, fails.
 *
 * @see Configuration
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */

public class ResolutionException extends RuntimeException {
    @java.io.Serial
    private static final long serialVersionUID = -1031186845316729450L;

    /**
     * Constructs a {@code ResolutionException} with no detail message.
     */
    public ResolutionException() { }

    /**
     * Constructs a {@code ResolutionException} with the given detail
     * message.
     *
     * @param msg
     *        The detail message; can be {@code null}
     */
    public ResolutionException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of this exception with the given cause.
     *
     * @param cause
     *        The cause; can be {@code null}
     */
    public ResolutionException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a {@code ResolutionException} with the given detail message
     * and cause.
     *
     * @param msg
     *        The detail message; can be {@code null}
     * @param cause
     *        The cause; can be {@code null}
     */
    public ResolutionException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
