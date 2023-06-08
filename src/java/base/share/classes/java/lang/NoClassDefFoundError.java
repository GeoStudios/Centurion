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
 * Thrown if the Java Virtual Machine or a {@code ClassLoader} instance
 * tries to load in the definition of a class (as part of a normal method call
 * or as part of creating a new instance using the {@code new} expression)
 * and no definition of the class could be found.
 * <p>
 * The searched-for class definition existed when the currently
 * executing class was compiled, but the definition can no longer be
 * found.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */

public class NoClassDefFoundError extends LinkageError {
    @java.io.Serial
    private static final long serialVersionUID = 9095859863287012458L;

    /**
     * Constructs a {@code NoClassDefFoundError} with no detail message.
     */
    public NoClassDefFoundError() {
        super();
    }

    /**
     * Constructs a {@code NoClassDefFoundError} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public NoClassDefFoundError(String s) {
        super(s);
    }
}
