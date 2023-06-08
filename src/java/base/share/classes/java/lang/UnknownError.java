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
 * Thrown when an unknown but serious exception has occurred in the
 * Java Virtual Machine.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */

public class UnknownError extends VirtualMachineError {
    @java.io.Serial
    private static final long serialVersionUID = 2524784860676771849L;

    /**
     * Constructs an {@code UnknownError} with no detail message.
     */
    public UnknownError() {
        super();
    }

    /**
     * Constructs an {@code UnknownError} with the specified detail
     * message.
     *
     * @param   s   the detail message.
     */
    public UnknownError(String s) {
        super(s);
    }
}
