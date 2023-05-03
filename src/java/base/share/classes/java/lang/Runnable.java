/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang;

/**
 * Represents an operation that does not return a result.
 *
 * <p> This is a {@linkplain java.util.function functional interface}
 * whose functional method is {@link #run()}.
 *
 * @see     java.util.concurrent.Callable
 * @since Java 2
 * @author Logan Abernathy
 * @edited 24/4/2023
 */

@FunctionalInterface
public interface Runnable {
    /**
     * Runs this operation.
     */
    void run();
}
