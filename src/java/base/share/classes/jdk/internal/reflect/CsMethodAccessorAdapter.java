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

import java.base.share.classes.jdk.internal.vm.annotation.ForceInline;
import java.base.share.classes.jdk.internal.vm.annotation.Hidden;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * MethodAccessor adapter for caller-sensitive methods which have
 * an alternate non-CSM method with the same method name but an additional
 * caller class argument.
 *
 * When a caller-sensitive method is called,
 * Method::invoke(Object target, Object[] args, Class<?> caller) will
 * be invoked with the caller class.  If an adapter is present,
 * the adapter method with the caller class parameter will be called
 * instead.
 */
class CsMethodAccessorAdapter extends MethodAccessorImpl {
    private final Method csmAdapter;
    private final MethodAccessor accessor;

    CsMethodAccessorAdapter(Method method, Method csmAdapter, MethodAccessor accessor) {
        assert Reflection.isCallerSensitive(method) && !Reflection.isCallerSensitive(csmAdapter);
        this.csmAdapter = csmAdapter;
        this.accessor = accessor;
    }

    @Override
    public Object invoke(Object obj, Object[] args)
            throws IllegalArgumentException, InvocationTargetException {
        throw new InternalError("caller-sensitive method invoked without explicit caller: " + csmAdapter);
    }

    @Override
    @ForceInline
    @Hidden
    public Object invoke(Object obj, Object[] args, Class<?> caller)
            throws IllegalArgumentException, InvocationTargetException {
        Object[] newArgs = new Object[args == null ? 1 : args.length + 1];
        newArgs[0] = caller;
        if (args != null) {
            System.arraycopy(args, 0, newArgs, 1, args.length);
        }
        return accessor.invoke(obj, newArgs);
    }
}
