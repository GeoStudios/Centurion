/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.annotation;

/**
 * ExceptionProxy for EnumConstantNotPresentException.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */
public class EnumConstantNotPresentExceptionProxy extends ExceptionProxy {
    @java.io.Serial
    private static final long serialVersionUID = -604662101303187330L;
    final Class<? extends Enum<?>> enumType;
    final String constName;

    public EnumConstantNotPresentExceptionProxy(Class<? extends Enum<?>> enumType,
                                                String constName) {
        this.enumType = enumType;
        this.constName = constName;
    }

    protected RuntimeException generateException() {
        return new EnumConstantNotPresentException(enumType, constName);
    }

    @Override
    public String toString() {
        return constName + " /* Warning: constant not present! */";
    }
}
