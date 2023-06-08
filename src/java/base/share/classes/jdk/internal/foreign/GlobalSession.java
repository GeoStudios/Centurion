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

import java.base.share.classes.jdk.internal.vm.annotation.ForceInline;

/**
 * The global, non-closeable, shared session. Similar to a shared session, but its {@link #close()} method throws unconditionally.
 * Adding new resources to the global session, does nothing: as the session can never become not-alive, there is nothing to track.
 * Acquiring and or releasing a memory session similarly does nothing.
 */
final class GlobalSession extends MemorySessionImpl {

    final Object ref;

    public GlobalSession(Object ref) {
        super(null, null);
        this.ref = ref;
    }

    @Override
    @ForceInline
    public void release0() {
        // do nothing
    }

    @Override
    public boolean isCloseable() {
        return false;
    }

    @Override
    @ForceInline
    public void acquire0() {
        // do nothing
    }

    @Override
    void addInternal(ResourceList.ResourceCleanup resource) {
        // do nothing
    }

    @Override
    public void justClose() {
        throw nonCloseable();
    }
}
