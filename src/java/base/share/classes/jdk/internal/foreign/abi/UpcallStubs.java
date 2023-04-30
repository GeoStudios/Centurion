/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.base.share.classes.jdk.internal.foreign.abi;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentScope;

import jdk.internal.foreign.MemorySessionImpl;

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
