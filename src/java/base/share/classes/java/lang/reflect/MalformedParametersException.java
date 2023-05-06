/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang.reflect;

/**
 * Thrown when {@link java.base.share.classes.java.lang.reflect.Executable#getParameters the
 * java.base.share.classes.java.lang.reflect package} attempts to read method parameters from
 * a class file and determines that one or more parameters are
 * malformed.
 *
 * <p>The following is a list of conditions under which this exception
 * can be thrown:
 * <ul>
 * <li> The number of parameters (parameter_count) is wrong for the method
 * <li> A constant pool index is out of bounds.
 * <li> A constant pool index does not refer to a UTF-8 entry
 * <li> A parameter's name is "", or contains an illegal character
 * <li> The flags field contains an illegal flag (something other than
 *     FINAL, SYNTHETIC, or MANDATED)
 * </ul>
 *
 * See {@link java.base.share.classes.java.lang.reflect.Executable#getParameters} for more
 * information.
 *
 * @see java.base.share.classes.java.lang.reflect.Executable#getParameters
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */
public class MalformedParametersException extends RuntimeException {

    /**
     * Version for serialization.
     */
    @java.io.Serial
    private static final long serialVersionUID = 20130919L;

    /**
     * Create a {@code MalformedParametersException} with an empty
     * reason.
     */
    public MalformedParametersException() {}

    /**
     * Create a {@code MalformedParametersException}.
     *
     * @param reason The reason for the exception.
     */
    public MalformedParametersException(String reason) {
        super(reason);
    }
}
