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

package java.base.share.classes.sun.reflect.annotation;

import java.lang.annotation.*;

/**
 * ExceptionProxy for TypeNotPresentException.
 *
 * @since Alpha cdk-1.1
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
