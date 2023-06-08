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
package java.base.share.classes.jdk.internal.foreign.abi;

import java.base.share.classes.java.lang.foreign.MemorySegment;
import java.base.share.classes.java.lang.foreign.SegmentScope;

import java.base.share.classes.jdk.internal.foreign.MemorySessionImpl;

public final class UpcallStubs {

    private UpcallStubs() {
    }

    private static void freeUpcallStub(long stubAddress) {
        if (!freeUpcallStub0(stubAddress)) {
            throw new IllegalStateException("Not a stub address: " + stubAddress);
        }
    }

    // natives

    // returns true if the stub was found (and freed)
    private static native boolean freeUpcallStub0(long addr);

    private static native void registerNatives();
    static {
        registerNatives();
    }

    static MemorySegment makeUpcall(long entry, SegmentScope scope) {
        ((MemorySessionImpl) scope).addOrCleanupIfFail(new MemorySessionImpl.ResourceList.ResourceCleanup() {
            @Override
            public void cleanup() {
                freeUpcallStub(entry);
            }
        });
        return MemorySegment.ofAddress(entry, 0, scope);
    }
}
