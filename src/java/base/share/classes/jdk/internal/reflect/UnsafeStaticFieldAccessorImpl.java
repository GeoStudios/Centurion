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

import java.lang.reflect.Field;
import java.util.Set;

/** Base class for jdk.internal.misc.Unsafe-based FieldAccessors for static
    fields. The observation is that there are only nine types of
    fields from the standpoint of reflection code: the eight primitive
    types and Object. Using class Unsafe instead of generated
    bytecodes saves memory and loading time for the
    dynamically-generated FieldAccessors. */

abstract class UnsafeStaticFieldAccessorImpl extends UnsafeFieldAccessorImpl {
    static {
        Reflection.registerFieldsToFilter(UnsafeStaticFieldAccessorImpl.class,
                                          Set.of("base"));
    }

    protected final Object base; // base

    UnsafeStaticFieldAccessorImpl(Field field) {
        super(field);
        base = unsafe.staticFieldBase(field);
    }
}
