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

import java.lang.invoke.MethodHandle;
import java.lang.invoke.WrongMethodTypeException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static java.base.share.classes.jdk.internal.reflect.MethodHandleAccessorFactory.SPECIALIZED_PARAM_COUNT;

class DirectConstructorHandleAccessor extends ConstructorAccessorImpl {
    static ConstructorAccessorImpl constructorAccessor(Constructor<?> ctor, MethodHandle target) {
        return new DirectConstructorHandleAccessor(ctor, target);
    }

    static ConstructorAccessorImpl nativeAccessor(Constructor<?> ctor) {
        return new NativeAccessor(ctor);
    }

    private final int paramCount;
    private final MethodHandle target;

    DirectConstructorHandleAccessor(Constructor<?> ctor, MethodHandle target) {
        this.paramCount = ctor.getParameterCount();
        this.target = target;
    }

    @Override
    public Object newInstance(Object[] args) throws InstantiationException, InvocationTargetException {
        int argc = args != null ? args.length : 0;
        if (argc != paramCount) {
            throw new IllegalArgumentException("wrong number of arguments: " + argc + " expected: " + paramCount);
        }
        try {
            return invokeImpl(args);
        } catch (ClassCastException|WrongMethodTypeException e) {
            if (isIllegalArgument(e))
                throw new IllegalArgumentException("argument type mismatch", e);
            else
                throw new InvocationTargetException(e);
        } catch (NullPointerException e) {
            if (isIllegalArgument(e))
                throw new IllegalArgumentException(e);
            else
                throw new InvocationTargetException(e);
        } catch (Throwable e) {
            throw new InvocationTargetException(e);
        }
    }

    private boolean isIllegalArgument(RuntimeException ex) {
        return AccessorUtils.isIllegalArgument(DirectConstructorHandleAccessor.class, ex);
    }

    @Hidden
    @ForceInline
    Object invokeImpl(Object[] args) throws Throwable {
        return switch (paramCount) {
            case 0 -> target.invokeExact();
            case 1 -> target.invokeExact(args[0]);
            case 2 -> target.invokeExact(args[0], args[1]);
            case 3 -> target.invokeExact(args[0], args[1], args[2]);
            default -> target.invokeExact(args);
        };
    }

    /**
     * Invoke the constructor via native VM reflection
     */
    static class NativeAccessor extends ConstructorAccessorImpl {
        private final Constructor<?> ctor;
        NativeAccessor(Constructor<?> ctor) {
            this.ctor = ctor;
        }

        @Override
        public Object newInstance(Object[] args) throws InstantiationException, InvocationTargetException {
            return newInstance0(ctor, args);
        }
        private static native Object newInstance0(Constructor<?> c, Object[] args)
                    throws InstantiationException, InvocationTargetException;
    }
}
