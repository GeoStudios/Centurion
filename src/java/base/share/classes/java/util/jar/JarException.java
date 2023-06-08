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

package java.base.share.classes.java.util.jar;

/**
 * Signals that an error of some sort has occurred while reading from
 * or writing to a JAR file.
 *
 * @author  David Connelly
 * @since   1.2
 */
public class JarException extends java.util.zip.ZipException {
    @java.io.Serial
    private static final long serialVersionUID = 7159778400963954473L;

    /**
     * Constructs a JarException with no detail message.
     */
    public JarException() {
    }

    /**
     * Constructs a JarException with the specified detail message.
     * @param s the detail message
     */
    public JarException(String s) {
        super(s);
    }
}
