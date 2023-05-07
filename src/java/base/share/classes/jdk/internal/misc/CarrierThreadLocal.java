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
