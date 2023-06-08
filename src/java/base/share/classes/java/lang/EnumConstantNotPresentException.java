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
 * Thrown when an application tries to access an enum constant by name
 * and the enum type contains no constant with the specified name.
 * This exception can be thrown by the {@linkplain
 * java.base.share.classes.java.lang.reflect.AnnotatedElement API used to read annotations
 * reflectively}.
 *
 * @see     java.base.share.classes.java.lang.reflect.AnnotatedElement
 * @since Alpha cdk-1.1
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
