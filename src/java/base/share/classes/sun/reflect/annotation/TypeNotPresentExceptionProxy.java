/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.annotation;

import java.lang.annotation.*;

/**
 * ExceptionProxy for TypeNotPresentException.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */
public class TypeNotPresentExceptionProxy extends ExceptionProxy {
    @java.io.Serial
    private static final long serialVersionUID = 5565925172427947573L;
    final String typeName;
    final Throwable cause;

    public TypeNotPresentExceptionProxy(String typeName, Throwable cause) {
        this.typeName = typeName;
        this.cause = cause;
    }

    protected RuntimeException generateException() {
        return new TypeNotPresentException(typeName, cause);
    }

    public String typeName() {
        return typeName;
    }

    public Throwable getCause() {
        return cause;
    }

    @Override
    public String toString() {
        return typeName + ".class /* Warning: type not present! */";
    }
}
