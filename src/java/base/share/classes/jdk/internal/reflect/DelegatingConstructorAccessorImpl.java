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

import java.base.share.classes.jdk.internal.vm.annotation.Stable;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/** Delegates its invocation to another ConstructorAccessorImpl and can
    change its delegate at run time. */

class DelegatingConstructorAccessorImpl extends ConstructorAccessorImpl {
    // initial non-null delegate
    private final ConstructorAccessorImpl initialDelegate;
    // alternative delegate: starts as null;
    // only single change from null -> non-null is guaranteed
    @Stable
    private ConstructorAccessorImpl altDelegate;

    DelegatingConstructorAccessorImpl(ConstructorAccessorImpl delegate) {
        initialDelegate = Objects.requireNonNull(delegate);
    }

    public Object newInstance(Object[] args)
      throws InstantiationException,
             IllegalArgumentException,
             InvocationTargetException
    {
        return delegate().newInstance(args);
    }

    private ConstructorAccessorImpl delegate() {
        var d = altDelegate;
        return  d != null ? d : initialDelegate;
    }

    void setDelegate(ConstructorAccessorImpl delegate) {
        altDelegate = Objects.requireNonNull(delegate);
    }
}
