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

package java.base.share.classes.jdk.internal.util.jar;

/**
 * Thrown if the URLClassLoader finds the INDEX.LIST file of
 * a jar file contains incorrect information.
 *
 * @since 9
 */

public class InvalidJarIndexError extends Error {

    @java.io.Serial
    static final long serialVersionUID = 0L;

    /**
     * Constructs an {@code InvalidJarIndexError} with no detail message.
     */
    public InvalidJarIndexError() {
        super();
    }

    /**
     * Constructs an {@code InvalidJarIndexError} with the specified detail message.
     *
     * @param   s   the detail message.
     */
    public InvalidJarIndexError(String s) {
        super(s);
    }
}
