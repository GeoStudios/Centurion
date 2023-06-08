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

package java.base.share.classes.jdk.internal.foreign;

import java.base.share.classes.sun.nio.ch.DirectBuffer;

import java.lang.ref.Cleaner;
import java.lang.ref.Reference;

/**
 * This is an implicit, GC-backed memory session. Implicit sessions cannot be closed explicitly.
 * While it would be possible to model an implicit session as a non-closeable view of a shared
 * session, it is better to capture the fact that an implicit session is not just a non-closeable
 * view of some session which might be closeable. This is useful e.g. in the implementations of
 * {@link DirectBuffer#address()}, where obtaining an address of a buffer instance associated
 * with a potentially closeable session is forbidden.
 */
final class ImplicitSession extends SharedSession {

    public ImplicitSession(Cleaner cleaner) {
        super();
        cleaner.register(this, resourceList);
    }

    @Override
    public void release0() {
        Reference.reachabilityFence(this);
    }

    @Override
    public void acquire0() {
        // do nothing
    }

    @Override
    public boolean isCloseable() {
        return false;
    }

    @Override
    public void justClose() {
        throw nonCloseable();
    }
}
