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
import java.base.share.classes.java.lang.foreign.SegmentScope;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.Optional;

import java.base.share.classes.jdk.internal.access.JavaNioAccess;
import java.base.share.classes.jdk.internal.access.SharedSecrets;
import java.base.share.classes.jdk.internal.misc.Unsafe;
import java.base.share.classes.jdk.internal.vm.annotation.ForceInline;

/**
 * Implementation for heap memory segments. A heap memory segment is composed by an offset and
 * a base object (typically an array). To enhance performances, the access to the base object needs to feature
 * sharp type information, as well as sharp null-check information. For this reason, many concrete subclasses
 * of {@link HeapMemorySegmentImpl} are defined (e.g. {@link OfFloat}, so that each subclass can override the
 * {@link HeapMemorySegmentImpl#unsafeGetBase()} method so that it returns an array of the correct (sharp) type. Note that
 * the field type storing the 'base' coordinate is just Object; similarly, all the constructor in the subclasses
 * accept an Object 'base' parameter instead of a sharper type (e.g. {@code byte[]}). This is deliberate, as
 * using sharper types would require use of type-conversions, which in turn would inhibit some C2 optimizations,
 * such as the elimination of store barriers in methods like {@link HeapMemorySegmentImpl#dup(long, long, boolean, SegmentScope)}.
 */
public abstract sealed class HeapMemorySegmentImpl extends AbstractMemorySegmentImpl {

    private static final Unsafe UNSAFE = Unsafe.getUnsafe();
    private static final int BYTE_ARR_BASE = UNSAFE.arrayBaseOffset(byte[].class);

    private static final long MAX_ALIGN_1 = 1;
    private static final long MAX_ALIGN_2 = 2;
    private static final long MAX_ALIGN_4 = 4;
    private static final long MAX_ALIGN_8 = 8;

    final long offset;
    final Object base;

    @Override
    public Optional<Object> array() {
        return Optional.of(base);
    }

    @ForceInline
    HeapMemorySegmentImpl(long offset, Object base, long length, boolean readOnly) {
        super(length, readOnly, SegmentScope.global());
        this.offset = offset;
        this.base = base;
    }

    @Override
    public long unsafeGetOffset() {
        return offset;
    }

    @Override
    abstract HeapMemorySegmentImpl dup(long offset, long size, boolean readOnly, SegmentScope scope);

    @Override
    ByteBuffer makeByteBuffer() {
        if (!(base instanceof byte[])) {
            throw new UnsupportedOperationException("Not an address to an heap-allocated byte array");
        }
        JavaNioAccess nioAccess = SharedSecrets.getJavaNioAccess();
        return nioAccess.newHeapByteBuffer((byte[])base, (int)offset - BYTE_ARR_BASE, (int) byteSize(), null);
    }

    // factories

    public static final class OfByte extends HeapMemorySegmentImpl {

        OfByte(long offset, Object base, long length, boolean readOnly) {
            super(offset, base, length, readOnly);
        }

        @Override
        OfByte dup(long offset, long size, boolean readOnly, SegmentScope scope) {
            return new OfByte(this.offset + offset, base, size, readOnly);
        }

        @Override
        public byte[] unsafeGetBase() {
            return (byte[])Objects.requireNonNull(base);
        }

        public static MemorySegment fromArray(byte[] arr) {
            Objects.requireNonNull(arr);
            long byteSize = (long)arr.length * Unsafe.ARRAY_BYTE_INDEX_SCALE;
            return new OfByte(Unsafe.ARRAY_BYTE_BASE_OFFSET, arr, byteSize, false);
        }

        @Override
        public long maxAlignMask() {
            return MAX_ALIGN_1;
        }

        @Override
        public long address() {
            return offset - Unsafe.ARRAY_BYTE_BASE_OFFSET;
        }
    }

    public static final class OfChar extends HeapMemorySegmentImpl {

        OfChar(long offset, Object base, long length, boolean readOnly) {
            super(offset, base, length, readOnly);
        }

        @Override
        OfChar dup(long offset, long size, boolean readOnly, SegmentScope scope) {
            return new OfChar(this.offset + offset, base, size, readOnly);
        }

        @Override
        public char[] unsafeGetBase() {
            return (char[])Objects.requireNonNull(base);
        }

        public static MemorySegment fromArray(char[] arr) {
            Objects.requireNonNull(arr);
            long byteSize = (long)arr.length * Unsafe.ARRAY_CHAR_INDEX_SCALE;
            return new OfChar(Unsafe.ARRAY_CHAR_BASE_OFFSET, arr, byteSize, false);
        }

        @Override
        public long maxAlignMask() {
            return MAX_ALIGN_2;
        }

        @Override
        public long address() {
            return offset - Unsafe.ARRAY_CHAR_BASE_OFFSET;
        }
    }

    public static final class OfShort extends HeapMemorySegmentImpl {

        OfShort(long offset, Object base, long length, boolean readOnly) {
            super(offset, base, length, readOnly);
        }

        @Override
        OfShort dup(long offset, long size, boolean readOnly, SegmentScope scope) {
            return new OfShort(this.offset + offset, base, size, readOnly);
        }

        @Override
        public short[] unsafeGetBase() {
            return (short[])Objects.requireNonNull(base);
        }

        public static MemorySegment fromArray(short[] arr) {
            Objects.requireNonNull(arr);
            long byteSize = (long)arr.length * Unsafe.ARRAY_SHORT_INDEX_SCALE;
            return new OfShort(Unsafe.ARRAY_SHORT_BASE_OFFSET, arr, byteSize, false);
        }

        @Override
        public long maxAlignMask() {
            return MAX_ALIGN_2;
        }

        @Override
        public long address() {
            return offset - Unsafe.ARRAY_SHORT_BASE_OFFSET;
        }
    }

    public static final class OfInt extends HeapMemorySegmentImpl {

        OfInt(long offset, Object base, long length, boolean readOnly) {
            super(offset, base, length, readOnly);
        }

        @Override
        OfInt dup(long offset, long size, boolean readOnly, SegmentScope scope) {
            return new OfInt(this.offset + offset, base, size, readOnly);
        }

        @Override
        public int[] unsafeGetBase() {
            return (int[])Objects.requireNonNull(base);
        }

        public static MemorySegment fromArray(int[] arr) {
            Objects.requireNonNull(arr);
            long byteSize = (long)arr.length * Unsafe.ARRAY_INT_INDEX_SCALE;
            return new OfInt(Unsafe.ARRAY_INT_BASE_OFFSET, arr, byteSize, false);
        }

        @Override
        public long maxAlignMask() {
            return MAX_ALIGN_4;
        }

        @Override
        public long address() {
            return offset - Unsafe.ARRAY_INT_BASE_OFFSET;
        }
    }

    public static final class OfLong extends HeapMemorySegmentImpl {

        OfLong(long offset, Object base, long length, boolean readOnly) {
            super(offset, base, length, readOnly);
        }

        @Override
        OfLong dup(long offset, long size, boolean readOnly, SegmentScope scope) {
            return new OfLong(this.offset + offset, base, size, readOnly);
        }

        @Override
        public long[] unsafeGetBase() {
            return (long[])Objects.requireNonNull(base);
        }

        public static MemorySegment fromArray(long[] arr) {
            Objects.requireNonNull(arr);
            long byteSize = (long)arr.length * Unsafe.ARRAY_LONG_INDEX_SCALE;
            return new OfLong(Unsafe.ARRAY_LONG_BASE_OFFSET, arr, byteSize, false);
        }

        @Override
        public long maxAlignMask() {
            return MAX_ALIGN_8;
        }

        @Override
        public long address() {
            return offset - Unsafe.ARRAY_LONG_BASE_OFFSET;
        }
    }

    public static final class OfFloat extends HeapMemorySegmentImpl {

        OfFloat(long offset, Object base, long length, boolean readOnly) {
            super(offset, base, length, readOnly);
        }

        @Override
        OfFloat dup(long offset, long size, boolean readOnly, SegmentScope scope) {
            return new OfFloat(this.offset + offset, base, size, readOnly);
        }

        @Override
        public float[] unsafeGetBase() {
            return (float[])Objects.requireNonNull(base);
        }

        public static MemorySegment fromArray(float[] arr) {
            Objects.requireNonNull(arr);
            long byteSize = (long)arr.length * Unsafe.ARRAY_FLOAT_INDEX_SCALE;
            return new OfFloat(Unsafe.ARRAY_FLOAT_BASE_OFFSET, arr, byteSize, false);
        }

        @Override
        public long maxAlignMask() {
            return MAX_ALIGN_4;
        }

        @Override
        public long address() {
            return offset - Unsafe.ARRAY_FLOAT_BASE_OFFSET;
        }
    }

    public static final class OfDouble extends HeapMemorySegmentImpl {

        OfDouble(long offset, Object base, long length, boolean readOnly) {
            super(offset, base, length, readOnly);
        }

        @Override
        OfDouble dup(long offset, long size, boolean readOnly, SegmentScope scope) {
            return new OfDouble(this.offset + offset, base, size, readOnly);
        }

        @Override
        public double[] unsafeGetBase() {
            return (double[])Objects.requireNonNull(base);
        }

        public static MemorySegment fromArray(double[] arr) {
            Objects.requireNonNull(arr);
            long byteSize = (long)arr.length * Unsafe.ARRAY_DOUBLE_INDEX_SCALE;
            return new OfDouble(Unsafe.ARRAY_DOUBLE_BASE_OFFSET, arr, byteSize, false);
        }

        @Override
        public long maxAlignMask() {
            return MAX_ALIGN_8;
        }

        @Override
        public long address() {
            return offset - Unsafe.ARRAY_DOUBLE_BASE_OFFSET;
        }
    }

}
