/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.jdk.internal.reflect;

import java.base.share.classes.jdk.internal.vm.annotation.Stable;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/** Delegates its invocation to another MethodAccessorImpl and can
    change its delegate at run time. */

class DelegatingMethodAccessorImpl extends MethodAccessorImpl {
    // initial non-null delegate
    private final MethodAccessorImpl initialDelegate;
    // alternative delegate: starts as null;
    // only single change from null -> non-null is guaranteed
    @Stable
    private MethodAccessorImpl altDelegate;

    DelegatingMethodAccessorImpl(MethodAccessorImpl delegate) {
        initialDelegate = Objects.requireNonNull(delegate);
    }

    @Override
    public Object invoke(Object obj, Object[] args)
        throws IllegalArgumentException, InvocationTargetException
    {
        return delegate().invoke(obj, args);
    }

    @Override
    public Object invoke(Object obj, Object[] args, Class<?> caller)
            throws IllegalArgumentException, InvocationTargetException
    {
        return delegate().invoke(obj, args, caller);
    }

    private MethodAccessorImpl delegate() {
        var d = altDelegate;
        return  d != null ? d : initialDelegate;
    }

    void setDelegate(MethodAccessorImpl delegate) {
        altDelegate = Objects.requireNonNull(delegate);
    }
}
