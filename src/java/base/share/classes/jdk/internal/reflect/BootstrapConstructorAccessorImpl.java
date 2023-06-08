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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;

/** Uses Unsafe.allocateObject() to instantiate classes; only used for
    bootstrapping. */

class BootstrapConstructorAccessorImpl extends ConstructorAccessorImpl {
    private final Constructor<?> constructor;

    BootstrapConstructorAccessorImpl(Constructor<?> c) {
        this.constructor = c;
    }

    public Object newInstance(Object[] args)
        throws IllegalArgumentException, InvocationTargetException
    {
        try {
            return UnsafeFieldAccessorImpl.unsafe.
                allocateInstance(constructor.getDeclaringClass());
        } catch (InstantiationException e) {
            throw new InvocationTargetException(e);
        }
    }
}
