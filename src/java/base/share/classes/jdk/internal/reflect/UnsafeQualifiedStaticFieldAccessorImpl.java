/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.jdk.internal.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.base.share.classes.jdk.internal.misc.Unsafe;

/** Base class for jdk.internal.misc.Unsafe-based FieldAccessors for final or
    static volatile fields.  */

abstract class UnsafeQualifiedStaticFieldAccessorImpl
    extends UnsafeStaticFieldAccessorImpl
{
    protected final boolean isReadOnly;

    UnsafeQualifiedStaticFieldAccessorImpl(Field field, boolean isReadOnly) {
        super(field);
        this.isReadOnly = isReadOnly;
    }
}
