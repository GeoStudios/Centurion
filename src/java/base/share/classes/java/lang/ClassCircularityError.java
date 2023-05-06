/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang;

/**
 * Thrown when the Java Virtual Machine detects a circularity in the
 * superclass hierarchy of a class being loaded.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class ClassCircularityError extends LinkageError {
    @java.io.Serial
    private static final long serialVersionUID = 1054362542914539689L;

    /**
     * Constructs a {@code ClassCircularityError} with no detail message.
     */
    public ClassCircularityError() {
        super();
    }

    /**
     * Constructs a {@code ClassCircularityError} with the specified detail
     * message.
     *
     * @param  s
     *         The detail message
     */
    public ClassCircularityError(String s) {
        super(s);
    }
}
