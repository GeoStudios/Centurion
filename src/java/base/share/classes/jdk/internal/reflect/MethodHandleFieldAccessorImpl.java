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

package java.base.share.classes.jdk.internal.reflect;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

abstract class MethodHandleFieldAccessorImpl extends FieldAccessorImpl {
    private static final int IS_READ_ONLY_BIT = 0x0001;
    private static final int IS_STATIC_BIT = 0x0002;
    private static final int NONZERO_BIT = 0x8000;

    private final int fieldFlags;
    protected final MethodHandle getter;
    protected final MethodHandle setter;

    protected MethodHandleFieldAccessorImpl(Field field, MethodHandle getter, MethodHandle setter, boolean isReadOnly, boolean isStatic) {
        super(field);
        this.fieldFlags = (isReadOnly ? IS_READ_ONLY_BIT : 0) |
                          (isStatic ? IS_STATIC_BIT : 0) |
                          NONZERO_BIT;
        this.getter = getter;
        this.setter = setter;
    }

    protected final boolean isReadOnly() {
        return (fieldFlags & IS_READ_ONLY_BIT) == IS_READ_ONLY_BIT;
    }

    protected final boolean isStatic() {
        return (fieldFlags & IS_STATIC_BIT) == IS_STATIC_BIT;
    }

    protected final void ensureObj(Object o) {
        if (!isStatic()) {
            // for compatibility, check the receiver object first
            // throw NullPointerException if o is null
            if (!field.getDeclaringClass().isAssignableFrom(o.getClass())) {
                throwSetIllegalArgumentException(o);
            }
        }
    }

    private String getMessage(boolean getter, Class<?> type) {
        String err = "Can not " + (getter ? "get" : "set");
        if (Modifier.isStatic(field.getModifiers()))
            err += " static";
        if (Modifier.isFinal(field.getModifiers()))
            err += " final";
        err += " " + field.getType().getName() + " field " + getQualifiedFieldName();
        if (type != null) {
            err += " on " + type.getName();
        }
        return err;
    }

    /**
     * IllegalArgumentException because Field::get on the specified object, which
     * is not an instance of the class or interface declaring the underlying method
     */
    protected IllegalArgumentException newGetIllegalArgumentException(Object o) {
        return new IllegalArgumentException(getMessage(true, o != null ? o.getClass() : null));
    }

    /**
     * IllegalArgumentException because Field::set on the specified object, which
     * is not an instance of the class or interface declaring the underlying method
     */
    protected IllegalArgumentException newSetIllegalArgumentException(Object o) {
        return new IllegalArgumentException(getMessage(false, o != null ? o.getClass() : null));
    }
}
