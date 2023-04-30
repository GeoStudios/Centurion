/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang;

/**
 * Thrown when an application tries to access an enum constant by name
 * and the enum type contains no constant with the specified name.
 * This exception can be thrown by the {@linkplain
 * java.base.share.classes.java.lang.reflect.AnnotatedElement API used to read annotations
 * reflectively}.
 *
 * @see     java.base.share.classes.java.lang.reflect.AnnotatedElement
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
@SuppressWarnings("rawtypes") /* rawtypes are part of the public api */
public class EnumConstantNotPresentException extends RuntimeException {
    @java.io.Serial
    private static final long serialVersionUID = -6046998521960521108L;

    /**
     * The type of the missing enum constant.
     */
    private Class<? extends Enum> enumType;

    /**
     * The name of the missing enum constant.
     */
    private String constantName;

    /**
     * Constructs an {@code EnumConstantNotPresentException} for the
     * specified constant.
     *
     * @param enumType the type of the missing enum constant
     * @param constantName the name of the missing enum constant
     */
    public EnumConstantNotPresentException(Class<? extends Enum> enumType,
                                           String constantName) {
        super(enumType.getName() + "." + constantName);
        this.enumType = enumType;
        this.constantName  = constantName;
    }

    /**
     * Returns the type of the missing enum constant.
     *
     * @return the type of the missing enum constant
     */
    public Class<? extends Enum> enumType() { return enumType; }

    /**
     * Returns the name of the missing enum constant.
     *
     * @return the name of the missing enum constant
     */
    public String constantName() { return constantName; }
}
