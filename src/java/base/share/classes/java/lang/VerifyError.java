/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.lang;

/**
 * Thrown when the "verifier" detects that a class file,
 * though well formed, contains some sort of internal inconsistency
 * or security problem.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class VerifyError extends LinkageError {
    @java.io.Serial
    private static final long serialVersionUID = 7001962396098498785L;

    /**
     * Constructs an {@code VerifyError} with no detail message.
     */
    public VerifyError() {
        super();
    }

    /**
     * Constructs an {@code VerifyError} with the specified detail message.
     *
     * @param   s   the detail message.
     */
    public VerifyError(String s) {
        super(s);
    }
}
