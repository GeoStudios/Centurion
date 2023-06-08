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

package java.base.share.classes.java.lang.annotation;

/**
 * Thrown when the annotation parser attempts to read an annotation
 * from a class file and determines that the annotation is malformed.
 * This error can be thrown by the {@linkplain
 * java.lang.reflect.AnnotatedElement API used to read annotations
 * reflectively}.
 *
 * @see     java.lang.reflect.AnnotatedElement
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */

public class AnnotationFormatError extends Error {
    @java.io.Serial
    private static final long serialVersionUID = -4256701562333669892L;

    /**
     * Constructs a new {@code AnnotationFormatError} with the specified
     * detail message.
     *
     * @param   message   the detail message.
     */
    public AnnotationFormatError(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code AnnotationFormatError} with the specified
     * detail message and cause.  Note that the detail message associated
     * with {@code cause} is <i>not</i> automatically incorporated in
     * this error's detail message.
     *
     * @param  message the detail message
     * @param  cause the cause (A {@code null} value is permitted, and
     *     indicates that the cause is nonexistent or unknown.)
     */
    public AnnotationFormatError(String message, Throwable cause) {
        super(message, cause);
    }


    /**
     * Constructs a new {@code AnnotationFormatError} with the specified
     * cause and a detail message of
     * {@code (cause == null ? null : cause.toString())} (which
     * typically contains the class and detail message of {@code cause}).
     *
     * @param  cause the cause (A {@code null} value is permitted, and
     *     indicates that the cause is nonexistent or unknown.)
     */
    public AnnotationFormatError(Throwable cause) {
        super(cause);
    }
}
