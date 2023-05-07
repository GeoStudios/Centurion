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

import java.base.share.classes.java.lang.foreign.GroupLayout;
import java.base.share.classes.java.lang.foreign.MemoryLayout;
import java.base.share.classes.java.lang.foreign.MemorySegment;
import java.base.share.classes.java.lang.foreign.SequenceLayout;
import java.base.share.classes.java.lang.foreign.StructLayout;
import java.base.share.classes.java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * This class provide support for constructing layout paths; that is, starting from a root path (see {@link #rootPath(MemoryLayout)},
 * a path can be constructed by selecting layout elements using the selector methods provided by this class
 * (see {@link #sequenceElement()}, {@link #sequenceElement(long)}, {@link #sequenceElement(long, long)}, {@link #groupElement(String)}).
 * Once a path has been fully constructed, clients can ask for the offset associated with the layout element selected
 * by the path (see {@link #offset}), or obtain var handle to access the selected layout element
 * given an address pointing to a segment associated with the root layout (see {@link #dereferenceHandle()}).
 */
public class LayoutPath {

    private static final long[] EMPTY_STRIDES = new long[0];
    private static final long[] EMPTY_BOUNDS = new long[0];

    private static final MethodHandle MH_ADD_SCALED_OFFSET;
    private static final MethodHandle MH_SLICE;

    static {
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            MH_ADD_SCALED_OFFSET = lookup.findStatic(LayoutPath.class, "addScaledOffset",
                    MethodType.methodType(long.class, long.class, long.class, long.class, long.class));
            MH_SLICE = lookup.findVirtual(MemorySegment.class, "asSlice",
                    MethodType.methodType(MemorySegment.class, long.class, long.class));
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private final MemoryLayout layout;
    private final long offset;
    private final LayoutPath enclosing;
    private final long[] strides;

    private final long[] bounds;

    private LayoutPath(MemoryLayout layout, long offset, long[] strides, long[] bounds, LayoutPath enclosing) {
        this.layout = layout;
        this.offset = offset;
        this.strides = strides;
        this.bounds = bounds;
        this.enclosing = enclosing;
    }

    // Layout path selector methods

    public LayoutPath sequenceElement() {
        check(SequenceLayout.class, "attempting to select a sequence element from a non-sequence layout");
        SequenceLayout seq = (SequenceLayout)layout;
        MemoryLayout elem = seq.elementLayout();
        return LayoutPath.nestedPath(elem, offset, addStride(elem.bitSize()), addBound(seq.elementCount()), this);
    }

    public LayoutPath sequenceElement(long start, long step) {
        check(SequenceLayout.class, "attempting to select a sequence element from a non-sequence layout");
        SequenceLayout seq = (SequenceLayout)layout;
        checkSequenceBounds(seq, start);
        MemoryLayout elem = seq.elementLayout();
        long elemSize = elem.bitSize();
        long nelems = step > 0 ?
                seq.elementCount() - start :
                start + 1;
        long maxIndex = Math.ceilDiv(nelems, Math.abs(step));
        return LayoutPath.nestedPath(elem, offset + (start * elemSize),
                                     addStride(elemSize * step), addBound(maxIndex), this);
    }

    public LayoutPath sequenceElement(long index) {
        check(SequenceLayout.class, "attempting to select a sequence element from a non-sequence layout");
        SequenceLayout seq = (SequenceLayout)layout;
        checkSequenceBounds(seq, index);
        long elemSize = seq.elementLayout().bitSize();
        long elemOffset = elemSize * index;
        return LayoutPath.nestedPath(seq.elementLayout(), offset + elemOffset, strides, bounds, this);
    }

    public LayoutPath groupElement(String name) {
        check(GroupLayout.class, "attempting to select a group element from a non-group layout");
        GroupLayout g = (GroupLayout)layout;
        long offset = 0;
        MemoryLayout elem = null;
        for (int i = 0; i < g.memberLayouts().size(); i++) {
            MemoryLayout l = g.memberLayouts().get(i);
            if (l.name().isPresent() &&
                l.name().get().equals(name)) {
                elem = l;
                break;
            } else if (g instanceof StructLayout) {
                offset += l.bitSize();
            }
        }
        if (elem == null) {
            throw badLayoutPath("cannot resolve '" + name + "' in layout " + layout);
        }
        return LayoutPath.nestedPath(elem, this.offset + offset, strides, bounds, this);
    }

    // Layout path projections

    public long offset() {
        return offset;
    }

    public VarHandle dereferenceHandle() {
        if (!(layout instanceof ValueLayout valueLayout)) {
            throw new IllegalArgumentException("Path does not select a value layout");
        }
        checkAlignment(this);

        VarHandle handle = Utils.makeSegmentViewVarHandle(valueLayout);
        for (int i = strides.length - 1; i >= 0; i--) {
            MethodHandle collector = MethodHandles.insertArguments(MH_ADD_SCALED_OFFSET, 2,
                    Utils.bitsToBytesOrThrow(strides[i], IllegalArgumentException::new),
                    bounds[i]);
            // (J, ...) -> J to (J, J, ...) -> J
            // i.e. new coord is prefixed. Last coord will correspond to innermost layout
            handle = MethodHandles.collectCoordinates(handle, 1, collector);
        }
        handle = MethodHandles.insertCoordinates(handle, 1,
                Utils.bitsToBytesOrThrow(offset, IllegalArgumentException::new));
        return handle;
    }

    @ForceInline
    private static long addScaledOffset(long base, long index, long stride, long bound) {
        Objects.checkIndex(index, bound);
        return base + (stride * index);
    }

    public MethodHandle offsetHandle() {
        MethodHandle mh = MethodHandles.identity(long.class);
        for (int i = strides.length - 1; i >=0; i--) {
            MethodHandle collector = MethodHandles.insertArguments(MH_ADD_SCALED_OFFSET, 2, strides[i], bounds[i]);
            // (J, ...) -> J to (J, J, ...) -> J
            // i.e. new coord is prefixed. Last coord will correspond to innermost layout
            mh = MethodHandles.collectArguments(mh, 0, collector);
        }
        mh = MethodHandles.insertArguments(mh, 0, offset);
        return mh;
    }

    public MethodHandle sliceHandle() {
        if (strides.length == 0) {
            // trigger checks eagerly
            Utils.bitsToBytesOrThrow(offset, Utils.BITS_TO_BYTES_THROW_OFFSET);
        }

        MethodHandle offsetHandle = offsetHandle(); // bit offset
        offsetHandle = MethodHandles.filterReturnValue(offsetHandle, Utils.MH_BITS_TO_BYTES_OR_THROW_FOR_OFFSET); // byte offset

        MethodHandle sliceHandle = MH_SLICE; // (MS, long, long) -> MS
        sliceHandle = MethodHandles.insertArguments(sliceHandle, 2, layout.byteSize()); // (MS, long) -> MS
        sliceHandle = MethodHandles.collectArguments(sliceHandle, 1, offsetHandle); // (MS, ...) -> MS

        return sliceHandle;
    }

    public MemoryLayout layout() {
        return layout;
    }

    // Layout path construction

    public static LayoutPath rootPath(MemoryLayout layout) {
        return new LayoutPath(layout, 0L, EMPTY_STRIDES, EMPTY_BOUNDS, null);
    }

    private static LayoutPath nestedPath(MemoryLayout layout, long offset, long[] strides, long[] bounds, LayoutPath encl) {
        return new LayoutPath(layout, offset, strides, bounds, encl);
    }

    // Helper methods

    private void check(Class<?> layoutClass, String msg) {
        if (!layoutClass.isAssignableFrom(layout.getClass())) {
            throw badLayoutPath(msg);
        }
    }

    private void checkSequenceBounds(SequenceLayout seq, long index) {
        if (index >= seq.elementCount()) {
            throw badLayoutPath(String.format("Sequence index out of bound; found: %d, size: %d", index, seq.elementCount()));
        }
    }

    private static IllegalArgumentException badLayoutPath(String cause) {
        return new IllegalArgumentException("Bad layout path: " + cause);
    }

    private static void checkAlignment(LayoutPath path) {
        MemoryLayout layout = path.layout;
        long alignment = layout.bitAlignment();
        if (!Utils.isAligned(path.offset, alignment)) {
            throw new UnsupportedOperationException("Invalid alignment requirements for layout " + layout);
        }
        for (long stride : path.strides) {
            if (!Utils.isAligned(stride, alignment)) {
                throw new UnsupportedOperationException("Alignment requirements for layout " + layout + " do not match stride " + stride);
            }
        }
        LayoutPath encl = path.enclosing;
        if (encl != null) {
            if (encl.layout.bitAlignment() < alignment) {
                throw new UnsupportedOperationException("Alignment requirements for layout " + layout + " do not match those for enclosing layout " + encl.layout);
            }
            checkAlignment(encl);
        }
    }

    private long[] addStride(long stride) {
        long[] newStrides = Arrays.copyOf(strides, strides.length + 1);
        newStrides[strides.length] = stride;
        return newStrides;
    }

    private long[] addBound(long maxIndex) {
        long[] newBounds = Arrays.copyOf(bounds, bounds.length + 1);
        newBounds[bounds.length] = maxIndex;
        return newBounds;
    }

    /**
     * This class provides an immutable implementation for the {@code PathElement} interface. A path element implementation
     * is simply a pointer to one of the selector methods provided by the {@code LayoutPath} class.
     */
    public static final class PathElementImpl implements MemoryLayout.PathElement, UnaryOperator<LayoutPath> {

        public enum PathKind {
            SEQUENCE_ELEMENT("unbound sequence element"),
            SEQUENCE_ELEMENT_INDEX("bound sequence element"),
            SEQUENCE_RANGE("sequence range"),
            GROUP_ELEMENT("group element");

            final String description;

            PathKind(String description) {
                this.description = description;
            }

            public String description() {
                return description;
            }
        }

        final PathKind kind;
        final UnaryOperator<LayoutPath> pathOp;

        public PathElementImpl(PathKind kind, UnaryOperator<LayoutPath> pathOp) {
            this.kind = kind;
            this.pathOp = pathOp;
        }

        @Override
        public LayoutPath apply(LayoutPath layoutPath) {
            return pathOp.apply(layoutPath);
        }

        public PathKind kind() {
            return kind;
        }
    }
}
