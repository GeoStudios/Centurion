/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.jdk.internal.foreign;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;

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
