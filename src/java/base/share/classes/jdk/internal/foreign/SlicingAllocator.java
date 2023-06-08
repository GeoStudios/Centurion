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

import java.base.share.classes.java.lang.foreign.MemorySegment;
import java.base.share.classes.java.lang.foreign.SegmentAllocator;

public final class SlicingAllocator implements SegmentAllocator {

    private final MemorySegment segment;
    private final long maxAlign;

    private long sp = 0L;

    public SlicingAllocator(MemorySegment segment) {
        this.segment = segment;
        this.maxAlign = ((AbstractMemorySegmentImpl)segment).maxAlignMask();
    }

    MemorySegment trySlice(long byteSize, long byteAlignment) {
        long min = segment.address();
        long start = Utils.alignUp(min + sp, byteAlignment) - min;
        MemorySegment slice = segment.asSlice(start, byteSize);
        sp = start + byteSize;
        return slice;
    }

    @Override
    public MemorySegment allocate(long byteSize, long byteAlignment) {
        Utils.checkAllocationSizeAndAlign(byteSize, byteAlignment, maxAlign);
        // try to slice from current segment first...
        return trySlice(byteSize, byteAlignment);
    }
}
