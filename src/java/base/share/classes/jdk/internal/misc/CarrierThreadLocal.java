/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.jdk.internal.misc;

import jdk.internal.access.JavaLangAccess;
import jdk.internal.access.SharedSecrets;

/**
 * A {@link ThreadLocal} variant which binds its value to current thread's
 * carrier thread.
 */
public class CarrierThreadLocal<T> extends ThreadLocal<T> {

    @Override
    public T get() {
        return JLA.getCarrierThreadLocal(this);
    }

    @Override
    public void set(T value) {
        JLA.setCarrierThreadLocal(this, value);
    }

    @Override
    public void remove() {
        JLA.removeCarrierThreadLocal(this);
    }

    public boolean isPresent() {
        return JLA.isCarrierThreadLocalPresent(this);
    }

    private static final JavaLangAccess JLA = SharedSecrets.getJavaLangAccess();
}
