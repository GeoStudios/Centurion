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
 * Thrown when the Java Virtual Machine attempts to read a class
 * file and determines that the file is malformed or otherwise cannot
 * be interpreted as a class file.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class ClassFormatError extends LinkageError {
    @java.io.Serial
    private static final long serialVersionUID = -8420114879011949195L;

    /**
     * Constructs a {@code ClassFormatError} with no detail message.
     */
    public ClassFormatError() {
        super();
    }

    /**
     * Constructs a {@code ClassFormatError} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public ClassFormatError(String s) {
        super(s);
    }
}
